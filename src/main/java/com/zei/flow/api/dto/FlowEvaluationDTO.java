package com.zei.flow.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
public class FlowEvaluationDTO implements Serializable {

    private static final long serialVersionUID = -1596673453197156883L;

    @ApiModelProperty("流程实例id")
    private String instanceId;

    @ApiModelProperty("节点实例key")
    private String taskKey;

    @ApiModelProperty("评论内容")
    private String evaluateContent;

    @ApiModelProperty("评论文件url")
    private String evaluateFileUrl;

    @ApiModelProperty("是否审批评论（1：是 2：否）")
    private Integer evaluateType;

}
