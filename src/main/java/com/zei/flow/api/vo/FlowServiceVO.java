package com.zei.flow.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public class FlowServiceVO implements Serializable {

    private static final long serialVersionUID = 3872977299157539259L;

    @ApiModelProperty("业务id")
    private Long serviceId;

    @ApiModelProperty("摘要")
    private String remark;

    @ApiModelProperty("路线参数")
    private Map<String, Object> variables;

}
