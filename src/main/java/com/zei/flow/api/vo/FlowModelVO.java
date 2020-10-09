package com.zei.flow.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-06-23 14:37
 */
@Data
public class FlowModelVO implements Serializable {

    private static final long serialVersionUID = -7003989821730045277L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("流程key")
    private String flowKey;

    @ApiModelProperty("流程名称")
    private String flowName;

    @ApiModelProperty("第一节点key")
    private String firstTaskKey;

}
