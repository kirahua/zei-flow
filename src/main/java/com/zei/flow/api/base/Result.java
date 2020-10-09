package com.zei.flow.api.base;

import java.io.Serializable;

/**
 * <p>
 * 基类
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:44
 */
public class Result<T> implements Serializable {

    private T datas;
    private Integer resp_code;
    private String resp_msg;

    public static <T> Result<T> succeed(String msg) {
        return (Result<T>) succeedWith((Object) null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> succeedWith(T datas, Integer code, String msg) {
        return new Result(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return (Result<T>) failedWith((Object) null, (Integer) CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, String code, String msg) {
        return new Result(datas, Integer.parseInt(code), msg);
    }

    public static <T> Result<T> failedWith(T datas, Integer code, String msg) {
        return new Result(datas, code, msg);
    }

    public static Result returnFlag(boolean flag) {
        return flag ? succeed("操作成功") : failed("操作失败");
    }

    public T getDatas() {
        return this.datas;
    }

    public Integer getResp_code() {
        return this.resp_code;
    }

    public String getResp_msg() {
        return this.resp_msg;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public void setResp_code(Integer resp_code) {
        this.resp_code = resp_code;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$datas = this.getDatas();
                    Object other$datas = other.getDatas();
                    if (this$datas == null) {
                        if (other$datas == null) {
                            break label47;
                        }
                    } else if (this$datas.equals(other$datas)) {
                        break label47;
                    }

                    return false;
                }

                Object this$resp_code = this.getResp_code();
                Object other$resp_code = other.getResp_code();
                if (this$resp_code == null) {
                    if (other$resp_code != null) {
                        return false;
                    }
                } else if (!this$resp_code.equals(other$resp_code)) {
                    return false;
                }

                Object this$resp_msg = this.getResp_msg();
                Object other$resp_msg = other.getResp_msg();
                if (this$resp_msg == null) {
                    if (other$resp_msg != null) {
                        return false;
                    }
                } else if (!this$resp_msg.equals(other$resp_msg)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Result;
    }

    public String toString() {
        return "Result(datas=" + this.getDatas() + ", resp_code=" + this.getResp_code() + ", resp_msg=" + this.getResp_msg() + ")";
    }

    public Result() {
    }

    public Result(T datas, Integer resp_code, String resp_msg) {
        this.datas = datas;
        this.resp_code = resp_code;
        this.resp_msg = resp_msg;
    }
}
