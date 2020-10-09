package com.zei.flow.service;

import com.zei.flow.api.base.Result;
import com.zei.flow.api.dto.FlowSubmitDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:44
 */
public interface IWorkFlowService {

    Result<Long> submit(FlowSubmitDTO flowSubmitDTO, HttpServletRequest request);
}
