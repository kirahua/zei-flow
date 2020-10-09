package com.zei.flow.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:12
 */
@Getter
@Setter
public class FlowSubmitDTO implements Serializable {

    private static final long serialVersionUID = -2096878744113127185L;

    @ApiModelProperty("流程实例id")
    private String instanceId;

    @ApiModelProperty("流程类型key")
    @NotBlank(message = "流程类型key不能为空")
    private String flowKey;

    @ApiModelProperty("流程名称")
    private String flowName;

    @ApiModelProperty("任务节点key")
    @NotBlank(message = "任务节点key不能为空")
    private String taskKey;

    @ApiModelProperty("业务数据")
    private Object data;

    @ApiModelProperty("1:审批通过 2：审批不通过")
    private Integer auditState;

    @ApiModelProperty("评论内容")
    private FlowEvaluationDTO evaluation;

}
