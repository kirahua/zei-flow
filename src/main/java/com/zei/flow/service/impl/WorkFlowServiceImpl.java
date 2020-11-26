package com.zei.flow.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zei.flow.api.base.CodeEnum;
import com.zei.flow.api.base.CurrentUser;
import com.zei.flow.api.base.Result;
import com.zei.flow.api.dto.FlowDoServiceDTO;
import com.zei.flow.api.dto.FlowServiceDTO;
import com.zei.flow.api.dto.FlowSubmitDTO;
import com.zei.flow.api.enums.AuditEnums;
import com.zei.flow.api.vo.FlowServiceVO;
import com.zei.flow.config.SpringContextUtil;
import com.zei.flow.dao.*;
import com.zei.flow.entity.*;
import com.zei.flow.service.IFlowEvaluationService;
import com.zei.flow.service.IWorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:50
 */
@Slf4j
@Service
public class WorkFlowServiceImpl implements IWorkFlowService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FlowModelMapper flowModelMapper;

    @Autowired
    FlowInstanceMapper flowInstanceMapper;

    @Autowired
    FlowAuditMapper flowAuditMapper;

    @Autowired
    FlowAuditDetailMapper flowAuditDetailMapper;

    @Autowired
    IFlowEvaluationService flowEvaluationService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Long> submit(FlowSubmitDTO flowSubmitDTO, HttpServletRequest request) {
        //获取当前审批节点信息
        FlowInstance currentTask = flowInstanceMapper.selectOne(new QueryWrapper<FlowInstance>()
                .eq("flow_key", flowSubmitDTO.getFlowKey())
                .eq("task_key", flowSubmitDTO.getTaskKey())
                .eq("is_delete", 0));

        //执行业务方法
        Result result = doTaskService(flowSubmitDTO, currentTask.getDoMethod(), request);
        if (result.getResp_code().equals(CodeEnum.ERROR.getCode())) {
            log.error(result.getResp_msg());
            return result;
        }
        //获取业务返回的实体
        FlowServiceVO flowServiceVO = JSON.parseObject(JSON.toJSONString(result.getDatas()), FlowServiceVO.class);
        if (flowServiceVO == null) {
            return result;
        }

        String instanceId = "";
        FlowAudit flowAudit = null;
        //判断是否为第一节点
        if (StringUtils.isEmpty(flowSubmitDTO.getInstanceId())) {
            log.info("=============流程启动，flowKey:{}==========", flowSubmitDTO.getFlowKey());
            instanceId = UUID.randomUUID().toString();
            //保存流程业务数据
            flowAudit = saveFlowData(flowSubmitDTO, flowServiceVO, instanceId);
        } else {
            instanceId = flowSubmitDTO.getInstanceId();
            flowAudit = getFlowAudit(instanceId);
            if (flowServiceVO.getVariables() != null) {
                flowAudit.setVariables(JSON.toJSONString(flowServiceVO.getVariables()));
                flowAuditMapper.updateById(flowAudit);
            }
        }

        FlowInstance nextTask = getNextTask(currentTask.getFlowKey(), currentTask.getNextTaskKey(), flowAudit.getVariables());

        //保存审批节点数据
        saveFlowDetailData(flowSubmitDTO, currentTask, nextTask, instanceId, flowServiceVO);

        //添加审批评论
        flowEvaluationService.addEvaluation(flowSubmitDTO.getEvaluation(), instanceId, currentTask.getTaskKey(), 1);

        //若审批不通过或者不存在下一节点 则流程结束
        if (flowSubmitDTO.getAuditState().equals(AuditEnums.AUDIT_FAIL.getCode())
                || nextTask == null) {
            flowAuditMapper.endAudit(flowSubmitDTO.getAuditState(), instanceId);
            return Result.succeed(flowAudit.getServiceId());
        }

        //处理自动通过
        FlowModel flowModel = flowModelMapper.selectOne(new QueryWrapper<FlowModel>()
                .eq("flow_key", flowSubmitDTO.getFlowKey())
                .eq("is_delete", 0));
        if (flowModel.getAutoAudit() == 1) {
            List<String> auditRoles = Arrays.asList(nextTask.getTaskRoles().split(","));
            for (String auditRole : auditRoles) {
                if (CurrentUser.roles.contains(Long.parseLong(auditRole))) {
                    flowSubmitDTO.setTaskKey(nextTask.getTaskKey());
                    flowSubmitDTO.setData(new FlowDoServiceDTO().setAuditState(AuditEnums.AUDIT_PASS.getCode()).setData(new FlowServiceDTO().setServiceId(flowAudit.getServiceId())));
                    submit(flowSubmitDTO, request);
                    break;
                }
            }
        }

        return Result.succeed(flowServiceVO.getServiceId());
    }


    /**
     * 保存流程节点信息
     * @param flowSubmitDTO
     * @param currentTask
     * @param nextTask
     * @param instanceId
     * @param flowServiceVO
     */
    private void saveFlowDetailData(FlowSubmitDTO flowSubmitDTO, FlowInstance currentTask, FlowInstance nextTask, String instanceId, FlowServiceVO flowServiceVO) {
        FlowAuditDetail flowAuditDetail = new FlowAuditDetail();
        flowAuditDetail.setTaskId(currentTask.getTaskKey());
        flowAuditDetail.setInstanceId(instanceId);
        flowAuditDetail.setCurrentRole(CurrentUser.currentRole.toString());
        if (nextTask != null) {
            flowAuditDetail.setNextRole(nextTask.getTaskRoles());
            flowAuditDetail.setNextTaskKey(nextTask.getTaskKey());
            flowAuditDetail.setNextTaskName(nextTask.getTaskName());
        }
        flowAuditDetail.setCurrentTaskKey(currentTask.getTaskKey());
        flowAuditDetail.setCurrentTaskName(currentTask.getTaskName());
        flowAuditDetail.setAuditUserId(CurrentUser.currentRole);
        flowAuditDetail.setAuditTime(new Date());
        flowAuditDetail.setAuditState(flowSubmitDTO.getAuditState());
        flowAuditDetailMapper.insert(flowAuditDetail);
    }

    /**
     * 获取下一节点数据
     *
     * @param flowKey
     * @param nextTaskKey
     * @param variables
     * @return
     */
    private FlowInstance getNextTask(String flowKey, String nextTaskKey, String variables) {
        if (StringUtils.isEmpty(nextTaskKey)) {
            return null;
        }
        List<String> nextKeyList = Arrays.asList(nextTaskKey.split(","));
        if (nextKeyList.size() == 1) {
            return flowInstanceMapper.
                    selectOne(new QueryWrapper<FlowInstance>().eq("flow_key", flowKey)
                            .eq("task_key", nextKeyList.get(0)).eq("is_delete", 0));
        }

        List<FlowInstance> flowInstances = flowInstanceMapper.
                selectList(new QueryWrapper<FlowInstance>().eq("flow_key", flowKey)
                        .in("task_key", nextKeyList).eq("is_delete", 0));
        FlowInstance result = null;
        for (FlowInstance flowInstance : flowInstances) {
            if (!StringUtils.isEmpty(variables) && !StringUtils.isEmpty(flowInstance.getTaskCondition())) {
                Map<String, Object> variablesMap = JSON.parseObject(variables, Map.class);
                Map<String, Object> conditionMap = JSON.parseObject(flowInstance.getTaskCondition(), Map.class);
                Iterator<String> iterator = variablesMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key1 = iterator.next();
                    if (!variablesMap.get(key1).equals(conditionMap.get(key1))) {
                        result = null;
                        break;
                    }
                    result = flowInstance;
                }
            } else {
                result = flowInstance;
            }
        }
        return result;
    }

    /**
     * 获取流程数据
     *
     * @param instanceId
     * @return
     */
    private FlowAudit getFlowAudit(String instanceId) {
        return flowAuditMapper.selectOne(new QueryWrapper<FlowAudit>()
                .eq("instance_id", instanceId)
                .eq("is_delete", 0));
    }

    /**
     * 保存流程主数据
     *
     * @param flowSubmitDTO
     * @param flowServiceVO
     * @param instanceId
     * @return
     */
    private FlowAudit saveFlowData(FlowSubmitDTO flowSubmitDTO, FlowServiceVO flowServiceVO, String instanceId) {
        //保存流程信息
        FlowAudit flowAudit = new FlowAudit();
        flowAudit.setFlowKey(flowSubmitDTO.getFlowKey());
        flowAudit.setFlowName(flowSubmitDTO.getFlowName());
        flowAudit.setInstanceId(instanceId);
        flowAudit.setServiceId(flowServiceVO.getServiceId());
        if (!StringUtils.isEmpty(flowServiceVO.getVariables())) {
            flowAudit.setVariables(JSON.toJSONString(flowServiceVO.getVariables()));
        }
        flowAudit.setRemark(flowServiceVO.getRemark());
        flowAudit.setFlowState(AuditEnums.AUDITING.getCode());
        flowAuditMapper.insert(flowAudit);
        return flowAudit;
    }


    /**
     * 远程调用业务方法
     *
     * @param flowSubmitDTO
     * @param doMethod
     * @param request
     * @return
     */
    private Result doTaskService(FlowSubmitDTO flowSubmitDTO, String doMethod, HttpServletRequest request) {
        Result result = Result.succeed(null);

        if (!StringUtils.isEmpty(doMethod)) {
            try {
                FlowDoServiceDTO flowDoServiceDTO = new FlowDoServiceDTO();
                BeanUtils.copyProperties(flowSubmitDTO, flowDoServiceDTO);

                String[] split = doMethod.split("/");

                String serviceName = split[0];
                String methodName = split[1];
                Class cls = SpringContextUtil.getBean(serviceName).getClass();
                Method m = cls.getDeclaredMethod(methodName, String.class);
                Object invoke = null;
                invoke = m.invoke(SpringContextUtil.getBean(serviceName), JSON.toJSONString(flowDoServiceDTO));
                result = (Result) invoke;

            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return Result.failed(e.getTargetException().getMessage());
            }catch (Exception e) {
                e.printStackTrace();
                log.error("流程url获取失败, e:{}", e.getMessage());
                return Result.failed("系统异常:" + e.getMessage());
            }
        } else {
            result.setDatas(new FlowServiceVO());
        }
        return result;
    }
}
