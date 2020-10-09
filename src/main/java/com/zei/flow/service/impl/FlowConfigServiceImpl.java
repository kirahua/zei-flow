package com.zei.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zei.flow.api.dto.FlowConfigDTO;
import com.zei.flow.api.dto.FlowInstanceDTO;
import com.zei.flow.api.dto.FlowModelDTO;
import com.zei.flow.api.vo.FlowModelVO;
import com.zei.flow.dao.FlowInstanceMapper;
import com.zei.flow.dao.FlowModelMapper;
import com.zei.flow.entity.FlowInstance;
import com.zei.flow.entity.FlowModel;
import com.zei.flow.service.IFlowConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:16
 */
@Service
public class FlowConfigServiceImpl implements IFlowConfigService {

    @Autowired
    FlowModelMapper flowModelMapper;

    @Autowired
    FlowInstanceMapper flowInstanceMapper;

    @Override
    public List<FlowModelVO> queryFlowModel() {
        List<FlowModel> flowModelList = flowModelMapper.selectList(new QueryWrapper<FlowModel>().eq("is_delete", 0));
        List<FlowModelVO> flowModelVOS = new ArrayList<>();
        for (FlowModel flowModel : flowModelList) {
            FlowModelVO flowModelVO = new FlowModelVO();
            BeanUtils.copyProperties(flowModel, flowModelVO);
            flowModelVOS.add(flowModelVO);
        }
        return flowModelVOS;
    }

    @Override
    public FlowModelVO getFlowModel(Long id) {
        FlowModel flowModel = flowModelMapper.selectById(id);
        FlowModelVO flowModelVO = new FlowModelVO();
        BeanUtils.copyProperties(flowModel, flowModelVO);
        List<FlowInstance> flowInstances = flowInstanceMapper.selectList(new QueryWrapper<FlowInstance>()
            .eq("flow_key", flowModel.getFlowKey())
            .eq("is_delete", 0)
            .orderByAsc("id"));

        if (!CollectionUtils.isEmpty(flowInstances)) {
            flowModelVO.setFirstTaskKey(flowInstances.get(0).getTaskKey());
        }
        return flowModelVO;
    }

    @Override
    public FlowModel getFlowModelByKey(String flowKey) {
        return flowModelMapper.selectOne(new QueryWrapper<FlowModel>().eq("flow_key", flowKey));
    }

    @Override
    public boolean addFlowModel(FlowModelDTO flowModelDTO) {
        FlowModel flowModel = new FlowModel();
        BeanUtils.copyProperties(flowModelDTO, flowModel);
        return flowModelMapper.insert(flowModel) > 0;
    }

    @Override
    public boolean updateFlowModel(FlowModelDTO flowModelDTO) {
        FlowModel flowModel = new FlowModel();
        BeanUtils.copyProperties(flowModelDTO, flowModel);
        return flowModelMapper.updateById(flowModel) > 0;
    }

    @Override
    public boolean deleteFlowModel(Long id) {
        FlowModel flowModel = new FlowModel();
        flowModel.setId(id);
        flowModel.setIsDelete(-1);
        return flowModelMapper.updateById(flowModel) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFlowInstance(FlowConfigDTO flowConfigDTO) {
        //先删除原有审批数据
        flowInstanceMapper.deleteByKey(flowConfigDTO.getFlowKey());

        for (FlowInstanceDTO flowInstanceDTO : flowConfigDTO.getFlowInstanceDTOS()) {
            FlowInstance flowInstance = new FlowInstance();
            BeanUtils.copyProperties(flowInstanceDTO, flowInstance);
            flowInstance.setFlowKey(flowConfigDTO.getFlowKey());
            flowInstanceMapper.insert(flowInstance);
        }
        return true;
    }
}
