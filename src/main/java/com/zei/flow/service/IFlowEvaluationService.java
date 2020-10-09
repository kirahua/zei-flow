package com.zei.flow.service;

import com.zei.flow.api.dto.FlowEvaluationDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 17:10
 */
public interface IFlowEvaluationService {

    boolean addEvaluation(FlowEvaluationDTO flowEvaluationDTO, String instanceId, String taskKey, Integer evaluationType);
}
