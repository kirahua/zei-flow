package com.zei.flow.service;

import com.zei.flow.api.base.PageInfo;
import com.zei.flow.api.search.FlowDataSearch;
import com.zei.flow.api.vo.AuditDetailVO;
import com.zei.flow.api.vo.FlowDataListVO;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:54
 */
public interface IFlowDataService {

    PageInfo<FlowDataListVO> queryMySubmitProcess(FlowDataSearch search);

    PageInfo<FlowDataListVO> queryMyAuditProcess(FlowDataSearch search);

    PageInfo<FlowDataListVO> queryMyWaitProcess(FlowDataSearch search);

}
