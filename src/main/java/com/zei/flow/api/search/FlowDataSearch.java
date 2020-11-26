package com.zei.flow.api.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:57
 */
@Data
public class FlowDataSearch implements Serializable {

    private static final long serialVersionUID = 5240195337458130867L;

    @ApiModelProperty("流程类型key")
    private String flowKey;

    @ApiModelProperty("流程发起人id")
    private Long createUserId;

    @ApiModelProperty("流程审批人id")
    private Long auditUserId;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("输入框查询")
    private String searchInput;

    @ApiModelProperty("审核状态")
    private Long auditState;

    @ApiModelProperty("创建时间起始（时分秒传00:00:00）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间截止（时分秒传23:59:59）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    @ApiModelProperty("审批时间起始（时分秒传00:00:00）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTimeBegin;

    @ApiModelProperty("审批时间截止（时分秒传23:59:59）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTimeEnd;

    private List<String> instanceIds;

    private String orderByField;

    private int pageNo;

    private int pageSize;

}
