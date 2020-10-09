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
 * @since 2020-06-23 15:04
 */
@Data
public class FlowInstanceDTO implements Serializable {

    private static final long serialVersionUID = 5549341945211683153L;

    @ApiModelProperty("节点key")
    private String taskKey;

    @ApiModelProperty("节点名称")
    private String taskName;

    @ApiModelProperty("节点审批角色")
    private String taskRoles;

    @ApiModelProperty("节点审批条件")
    private String taskCondition;

    @ApiModelProperty("下一审批节点key(逗号隔开)")
    private String nextTaskKey;

    @ApiModelProperty("执行url")
    private String doUrl;
}
