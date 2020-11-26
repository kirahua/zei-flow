package com.zei.flow.service.impl;

import com.zei.flow.api.base.CurrentUser;
import com.zei.flow.api.base.PageInfo;
import com.zei.flow.api.search.FlowDataSearch;
import com.zei.flow.api.vo.FlowDataListVO;
import com.zei.flow.dao.FlowAuditMapper;
import com.zei.flow.service.IFlowDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 17:21
 */
@Service
public class FlowDataServiceImpl implements IFlowDataService {

    @Autowired
    FlowAuditMapper flowAuditMapper;

    @Override
    public PageInfo<FlowDataListVO> queryMySubmitProcess(FlowDataSearch search) {
        //设置分页参数
        PageInfo<FlowDataListVO> pageInfo = new PageInfo<>();
        pageInfo.setCurrent(search.getPageNo());
        pageInfo.setSize(search.getPageSize());

        //设置查询条件
        search.setCreateUserId(CurrentUser.userId);
        List<FlowDataListVO> flowDataListVOS = flowAuditMapper.queryMySubmitProcess(pageInfo, search);
        //设置审批状态
        setFlowState(flowDataListVOS);

        pageInfo.setRecords(flowDataListVOS);
        return pageInfo;
    }

    @Override
    public PageInfo<FlowDataListVO> queryMyAuditProcess(FlowDataSearch search) {

        //设置分页参数
        PageInfo<FlowDataListVO> pageInfo = new PageInfo<>();
        pageInfo.setCurrent(search.getPageNo());
        pageInfo.setSize(search.getPageSize());

        //设置查询条件
        search.setAuditUserId(CurrentUser.userId);
        //先查询是否有参与审批
        List<String> doAuditInstanceIds = flowAuditMapper.queryDoAudit(search);
        if (CollectionUtils.isEmpty(doAuditInstanceIds)) {
            pageInfo.setRecords(null);
            return pageInfo;
        }
        search.setInstanceIds(doAuditInstanceIds);
        List<FlowDataListVO> flowDataListVOS = flowAuditMapper.queryMyAuditProcess(pageInfo, search);
        //设置审批状态
        setFlowState(flowDataListVOS);

        pageInfo.setRecords(flowDataListVOS);
        return pageInfo;
    }

    @Override
    public PageInfo<FlowDataListVO> queryMyWaitProcess(FlowDataSearch search) {
        //设置分页参数
        PageInfo<FlowDataListVO> pageInfo = new PageInfo<>();
        pageInfo.setCurrent(search.getPageNo());
        pageInfo.setSize(search.getPageSize());
        List<Long> roles = CurrentUser.roles;
        String role = "";
        for (Long roleId : roles) {
            role += "|" + roleId;
        }
        search.setRole(role.substring(1));

        List<FlowDataListVO> flowDataListVOS = flowAuditMapper.queryMyWaitProcess(pageInfo, search);

        //设置审批状态
        setFlowState(flowDataListVOS);

        pageInfo.setRecords(flowDataListVOS);
        return pageInfo;
    }


    /**
     * 设置流程状态
     * @param flowDataListVOS
     */
    private void setFlowState(List<FlowDataListVO> flowDataListVOS) {
        for (FlowDataListVO flowData : flowDataListVOS) {
            if (flowData.getFlowState() == 1) {
                flowData.setFlowStateValue("审批通过");
            } else if (flowData.getFlowState() == 2) {
                flowData.setFlowStateValue("审批不通过");
            } else {
                flowData.setFlowStateValue(flowData.getNextTaskName() + "中");
            }
        }
    }
}
