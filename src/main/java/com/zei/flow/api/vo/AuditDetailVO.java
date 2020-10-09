package com.zei.flow.api.vo;

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
 * @since 2020-09-28 16:45
 */
@Data
public class AuditDetailVO implements Serializable {

    private static final long serialVersionUID = 7437053924490197979L;

    @ApiModelProperty("审批是否结束  true则不再显示审批按钮")
    private Boolean auditEnd;

    @ApiModelProperty("审批流程图")
    private List<AuditEvaluationVO> auditEvaluationVOS;
}
