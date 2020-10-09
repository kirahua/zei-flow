package com.zei.flow.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-06-24 10:09
 */
@Data
@Accessors(chain = true)
public class FlowServiceDTO implements Serializable {

    private static final long serialVersionUID = -4591423228651673320L;

    @ApiModelProperty("业务id")
    private Long serviceId;
}
