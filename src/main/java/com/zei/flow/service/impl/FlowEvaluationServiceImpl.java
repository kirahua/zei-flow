package com.zei.flow.service.impl;

import com.zei.flow.api.base.CurrentUser;
import com.zei.flow.api.dto.FlowEvaluationDTO;
import com.zei.flow.dao.FlowEvaluationMapper;
import com.zei.flow.entity.FlowEvaluation;
import com.zei.flow.service.IFlowEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 17:11
 */
@Service
public class FlowEvaluationServiceImpl implements IFlowEvaluationService {

    @Autowired
    FlowEvaluationMapper flowEvaluationMapper;

    @Override
    public boolean addEvaluation(FlowEvaluationDTO evaluation, String instanceId, String taskKey, Integer evaluationType) {
        FlowEvaluation flowEvaluation = new FlowEvaluation();
        if (evaluation != null) {
            flowEvaluation.setEvaluateContent(evaluation.getEvaluateContent());
            flowEvaluation.setEvaluateFileUrl(evaluation.getEvaluateFileUrl());
        }
        flowEvaluation.setEvaluateType(evaluationType);
        flowEvaluation.setInstanceId(instanceId);
        flowEvaluation.setTaskId(taskKey);
        flowEvaluation.setEvaluateTime(new Date());
        flowEvaluation.setEvaluateUserId(CurrentUser.userId);
        return flowEvaluationMapper.insert(flowEvaluation) > 0;
    }
}
