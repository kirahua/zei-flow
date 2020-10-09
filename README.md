# zei-flow
工作流-用于审批流程


## 流程配置
##### flow_model 存储流程类型数据
##### flow_instantce 根据flow_key关联flow_model表，作为流程的节点配置表，do_url为节点处理业务的接口地址

## 流程工作数据
##### flow_audit 流程工作数据 每一次发起流程请求都会新增且仅有一条记录，service_id为业务表的主id（可根据自身业务定义类型），instance_id为流程的id标识
##### flow_audit_detail 流程工作节点数据 每一次节点审批都会新增一条记录，instance_id关联流程，保存着节点的审批人、待审批角色等信息
##### flow_evaluation 流程工作评论数据 存储审批评论，支持审批时评论和对节点的留言


## 流程审批
##### 所有的流程提交、审批都统一调用 /workflow/submit 接口，若为流程发起则instanceId传空，反之传值
```java
//发起示例
{
    "auditState": 1,
    "data": {
      "test": "hello"
    },
    "evaluation": {
        "evaluateContent": "ok",
        "evaluateFileUrl": ""
    },
    "flowKey": "test-flow",
    "instanceId": "",
    "taskKey": "test-task-1"
}
```
```java
//审批示例
{
    "auditState": 1,
    "data": {
      "serviceId": 1
    },
    "evaluation": {
        "evaluateContent": "ok",
        "evaluateFileUrl": ""
    },
    "flowKey": "test-flow",
    "instanceId": "xxxxxxxxxxx",
    "taskKey": "test-task-2"
}
```
##### 提交时调用的业务url 需要封装FlowServiceVO返回，字段为业务主id（serviceId），摘要信息（remark），若存在分支传路线参数map（variables）
