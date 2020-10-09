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
@TableName("flow_audit_detail")
public class FlowAuditDetail implements Serializable {

	@TableId(
			value = "id",
			type = IdType.AUTO
	)
	private Long id;
	/**
	 * 任务实例id
	 */
	@TableField("task_id")
	private String taskId;
	/**
	 * 流程实例id
	 */
	@TableField("instance_id")
	private String instanceId;
	/**
	 * 当前待审批角色
	 */
	@TableField("current_role")
	private String currentRole;
	/**
	 * 下一节点待审批角色
	 */
	@TableField("next_role")
	private String nextRole;
	/**
	 * 下一节点待审批人
	 */
	@TableField("next_user")
	private String nextUser;
	/**
	 * 当前节点key
	 */
	@TableField("current_task_key")
	private String currentTaskKey;
	/**
	 * 当前节点名
	 */
	@TableField("current_task_name")
	private String currentTaskName;
	/**
	 * 下一节点key
	 */
	@TableField("next_task_key")
	private String nextTaskKey;
	/**
	 * 下一节点名
	 */
	@TableField("next_task_name")
	private String nextTaskName;
	/**
	 * 审批人id
	 */
	@TableField("audit_user_id")
	private Long auditUserId;
	/**
	 * 审批时间
	 */
	@TableField("audit_time")
	private Date auditTime;
	/**
	 * 审批状态0：待审批 1：审批通过 2：审批不通过
	 */
	@TableField("audit_state")
	private Integer auditState;

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
