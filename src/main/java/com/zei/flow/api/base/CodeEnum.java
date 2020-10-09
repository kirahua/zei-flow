package com.zei.flow.api.base;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:45
 */
public enum CodeEnum {

    SUCCESS(200),
    DEFAULT_ASSWORD(800),
    ERROR(99999);

    private Integer code;

    private CodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

}
