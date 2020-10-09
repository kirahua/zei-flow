package com.zei.flow.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:45
 */
@Data
public class AuditEvaluationVO implements Serializable {

    private static final long serialVersionUID = -2951887379688814020L;

    @ApiModelProperty("节点实例id")
    private String taskId;

    @ApiModelProperty("1:审批通过 2:审批不通过")
    private Long auditState;

    @ApiModelProperty("审批节点名")
    private String currentTaskName;

    @ApiModelProperty("评论人名")
    private String evaluateUserName;

    @ApiModelProperty("评论描述")
    private String evaluateDescription;

    @ApiModelProperty("评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date evaluateTime;

    @ApiModelProperty("评论内容")
    private String evaluateContent;

    @ApiModelProperty("评论附件url")
    private String evaluateFileUrl;

    @ApiModelProperty("是否节点评论（1：是 2：否）")
    private Long evaluateType;



}
