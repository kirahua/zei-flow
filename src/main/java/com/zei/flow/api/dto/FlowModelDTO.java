package com.zei.flow.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-06-23 14:48
 */
@Data
public class FlowModelDTO implements Serializable {

    private static final long serialVersionUID = -3351211128662927613L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("流程key")
    private String flowKey;

    @ApiModelProperty("流程名称")
    private String flowName;

    @ApiModelProperty("相同角色是否自动审批（0：否 1：是）(预留)")
    private Integer autoAudit;

}
