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
@TableName("flow_model")
public class FlowModel implements Serializable {

	@TableId(
			value = "id",
			type = IdType.AUTO
	)
	private Long id;
	/**
	 * 流程key
	 */
	@TableField("flow_key")
	private String flowKey;
	/**
	 * 流程名称
	 */
	@TableField("flow_name")
	private String flowName;
	/**
	 * 相同角色是否自动审批（0：否 1：是）
	 */
	@TableField("auto_audit")
	private Integer autoAudit;

	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Long createUser;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	@TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
	private Long updateUser;

	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 * 版本号
	 */
	@TableField(value = "version", fill = FieldFill.INSERT)
	private Integer version;
	/**
	 * 是否移除（0：否 -1：是）
	 */
	@TableField(value = "is_delete", fill = FieldFill.INSERT)
	private Integer isDelete;

}
