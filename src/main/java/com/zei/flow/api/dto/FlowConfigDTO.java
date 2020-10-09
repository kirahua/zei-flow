package com.zei.flow.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-06-23 15:04
 */
@Data
public class FlowConfigDTO implements Serializable {

    private static final long serialVersionUID = 5549341945211683153L;

    @ApiModelProperty("流程key")
    private String flowKey;

    @ApiModelProperty("流程节点")
    private List<FlowInstanceDTO> flowInstanceDTOS;
}
