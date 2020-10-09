package com.zei.flow.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("flow_audit")
public class FlowAudit implements Serializable {

	@TableId(
			value = "id",
			type = IdType.AUTO
	)
	private Long id;
	/**
	 * 流程编号
	 */
	@TableField("flow_code")
	private String flowCode;
	/**
	 * 流程key
	 */
	@TableField("flow_key")
	private String flowKey;
	/**
	 * 流程名
	 */
	@TableField("flow_name")
	private String flowName;
	/**
	 * 流程实例id
	 */
	@TableField("instance_id")
	private String instanceId;
	/**
	 * 业务id
	 */
	@TableField("service_id")
	private Long serviceId;
	/**
	 * 路线参数
	 */
	@TableField("variables")
	private String variables;
	/**
	 * 摘要
	 */
	@TableField("remark")
	private String remark;
	/**
	 * 流程是否结束（0:未结束 1:已结束）
	 */
	@TableField("flow_state")
	private Integer flowState;

	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Long createUser;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	@TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
	private Long updateUser;

	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableField("version")
	private Integer version;

	@TableField("is_delete")
	private Integer isDelete;
}
