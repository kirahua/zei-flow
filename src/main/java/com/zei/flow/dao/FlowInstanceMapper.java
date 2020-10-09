package com.zei.flow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zei.flow.entity.FlowInstance;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:30
 */
public interface FlowInstanceMapper extends BaseMapper<FlowInstance> {

    @Update("update flow_instance set is_delete = -1 where flow_key = #{flowKey}")
    int deleteByKey(String flowKey);
}
