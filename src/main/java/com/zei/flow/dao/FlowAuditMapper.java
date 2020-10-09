package com.zei.flow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zei.flow.api.base.PageInfo;
import com.zei.flow.api.search.FlowDataSearch;
import com.zei.flow.api.vo.FlowDataListVO;
import com.zei.flow.entity.FlowAudit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 13:45
 */
public interface FlowAuditMapper extends BaseMapper<FlowAudit> {

    @Update("update flow_audit set flow_state = #{auditState} where instance_id = #{instanceId}")
    int endAudit(Integer auditState, String instanceId);

    List<FlowDataListVO> queryMySubmitProcess(PageInfo<FlowDataListVO> pageInfo, @Param("search") FlowDataSearch search);

    List<FlowDataListVO> queryMyAuditProcess(PageInfo<FlowDataListVO> pageInfo, @Param("search") FlowDataSearch search);

    List<FlowDataListVO> queryMyWaitProcess(PageInfo<FlowDataListVO> pageInfo, @Param("search") FlowDataSearch search);
}
