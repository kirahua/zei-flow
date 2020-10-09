package com.zei.flow.service;

import com.zei.flow.api.dto.FlowConfigDTO;
import com.zei.flow.api.dto.FlowModelDTO;
import com.zei.flow.api.vo.FlowModelVO;
import com.zei.flow.entity.FlowModel;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:12
 */
public interface IFlowConfigService {

    /**
     * 查询所有流程列表
     * @return
     */
    List<FlowModelVO> queryFlowModel();

    /**
     * 获取流程信息
     * @param id
     * @return
     */
    FlowModelVO getFlowModel(Long id);

    /**
     * 根据流程key获取流程信息
     * @param flowKey
     * @return
     */
    FlowModel getFlowModelByKey(String flowKey);

    /**
     * 新增流程
     * @param flowModelDTO
     * @return
     */
    boolean addFlowModel(FlowModelDTO flowModelDTO);

    /**
     * 更新流程
     * @param flowModelDTO
     * @return
     */
    boolean updateFlowModel(FlowModelDTO flowModelDTO);

    /**
     * 删除流程
     * @param id
     * @return
     */
    boolean deleteFlowModel(Long id);

    /**
     * 修改流程配置信息
     * @param flowConfigDTO
     * @return
     */
    boolean addFlowInstance(FlowConfigDTO flowConfigDTO);
}
