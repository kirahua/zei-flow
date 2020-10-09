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
public class FlowDataListVO implements Serializable {

    private static final long serialVersionUID = -7825412059015900535L;

    @ApiModelProperty("流程实例id")
    private String instanceId;

    @ApiModelProperty("流程类型key")
    private String flowKey;

    @ApiModelProperty("流程发起人名")
    private String createUserName;

    @ApiModelProperty("流程发起时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("审批状态")
    private Long flowState;

    @ApiModelProperty("审批状态值")
    private String flowStateValue;

    @ApiModelProperty("当前节点key")
    private String currentTaskKey;

    @ApiModelProperty("当前节点名")
    private String currentTaskName;

    @ApiModelProperty("下一节点key")
    private String nextTaskKey;

    @ApiModelProperty("下一节点名")
    private String nextTaskName;

    @ApiModelProperty("下一审批角色")
    private String nextRole;

    @ApiModelProperty("审批人id")
    private Long auditUserId;

    @ApiModelProperty("审批人名")
    private String auditUserName;

    @ApiModelProperty("审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date auditTime;

    @ApiModelProperty("业务id")
    private Long serviceId;

    @ApiModelProperty("摘要")
    private String remark;

}
