package com.zei.flow.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 11:12
 */
@Data
@Accessors(chain = true)
public class FlowDoServiceDTO implements Serializable {

    private static final long serialVersionUID = -2859567993943932964L;

    @ApiModelProperty("1:审批通过 2：审批不通过 3:审批打回")
    private Integer auditState;

    @ApiModelProperty("业务数据")
    private Object data;

}
