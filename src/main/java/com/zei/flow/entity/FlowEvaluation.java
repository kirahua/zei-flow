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
@TableName("flow_evaluation")
public class FlowEvaluation implements Serializable {


	@TableId(
			value = "id",
			type = IdType.AUTO
	)
	private Long id;
	/**
	 * 流程实例id
	 */
	@TableField("instance_id")
	private String instanceId;
	/**
	 * 节点实例id
	 */
	@TableField("task_id")
	private String taskId;
	/**
	 * 评论人id
	 */
	@TableField("evaluate_user_id")
	private Long evaluateUserId;
	/**
	 * 评论时间
	 */
	@TableField("evaluate_time")
	private Date evaluateTime;
	/**
	 * 评论内容
	 */
	@TableField("evaluate_content")
	private String evaluateContent;
	/**
	 * 评论文件url
	 */
	@TableField("evaluate_file_url")
	private String evaluateFileUrl;
	/**
	 * 是否节点评论（1：是 2：否）
	 */
	@TableField("evaluate_type")
	private Integer evaluateType;

	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Long createUser;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	@TableField(value = "update_user", fill = FieldFill.INSERT)
	private Long updateUser;

	@TableField(value = "update_time", fill = FieldFill.INSERT)
	private Date updateTime;

	@TableField("version")
	private Integer version;

	@TableField("is_delete")
	private Integer isDelete;
}
