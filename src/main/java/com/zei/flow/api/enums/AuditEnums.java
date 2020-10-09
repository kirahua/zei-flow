package com.zei.flow.api.enums;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-06-23 11:48
 */
@Getter
public enum AuditEnums {
    AUDITING(0, "审批中"),
    AUDIT_PASS(1, "审批通过"),
    AUDIT_FAIL(2, "审核不通过"),
    ;

    private Integer code;

    private String remark;

    AuditEnums(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }
}
