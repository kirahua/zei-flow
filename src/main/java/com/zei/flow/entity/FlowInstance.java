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
@TableName("flow_instance")
public class FlowInstance implements Serializable {

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
	 * 节点key
	 */
	@TableField("task_key")
	private String taskKey;
	/**
	 * 节点名称
	 */
	@TableField("task_name")
	private String taskName;
	/**
	 * 可审批角色
	 */
	@TableField("task_roles")
	private String taskRoles;
	/**
	 * 条件参数
	 */
	@TableField("task_condition")
	private String taskCondition;
	/**
	 * 下一节点key
	 */
	@TableField("next_task_key")
	private String nextTaskKey;
	/**
	 * 特殊执行url
	 */
	@TableField("do_url")
	private String doUrl;

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
