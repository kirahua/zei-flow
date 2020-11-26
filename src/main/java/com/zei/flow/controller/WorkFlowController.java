package com.zei.flow.controller;

import com.zei.flow.api.base.PageInfo;
import com.zei.flow.api.base.Result;
import com.zei.flow.api.dto.FlowEvaluationDTO;
import com.zei.flow.api.dto.FlowSubmitDTO;
import com.zei.flow.api.search.FlowDataSearch;
import com.zei.flow.api.vo.AuditDetailVO;
import com.zei.flow.api.vo.FlowDataListVO;
import com.zei.flow.service.IFlowDataService;
import com.zei.flow.service.IFlowEvaluationService;
import com.zei.flow.service.IWorkFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 流程控制接口
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:45
 */
@Api(tags = "流程审批")
@RestController
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    IWorkFlowService workFlowService;

    @Autowired
    IFlowDataService flowDataService;

    @Autowired
    IFlowEvaluationService flowEvaluationService;


    @ApiOperation(value = "流程控制接口-审批提交")
    @PostMapping("/submit")
    public Result<Long> submit(@RequestBody FlowSubmitDTO flowSubmitDTO, HttpServletRequest request) {
        return workFlowService.submit(flowSubmitDTO, request);
    }

    @ApiOperation(value = "流程控制接口-查看我发起的")
    @PostMapping("/queryMySubmitProcess")
    public Result<PageInfo<FlowDataListVO>> queryMySubmitProcess(@RequestBody FlowDataSearch search) {
        PageInfo<FlowDataListVO> listPageInfo = flowDataService.queryMySubmitProcess(search);
        return Result.succeed(listPageInfo);
    }

    @ApiOperation(value = "流程控制接口-查看我审批的")
    @PostMapping("/queryMyAuditProcess")
    public Result<PageInfo<FlowDataListVO>> queryMyAuditProcess(@RequestBody FlowDataSearch search) {
        PageInfo<FlowDataListVO> listPageInfo = flowDataService.queryMyAuditProcess(search);
        return Result.succeed(listPageInfo);
    }

    @ApiOperation(value = "流程控制接口-查看待我审批的")
    @PostMapping("/queryMyWaitProcess")
    public Result<PageInfo<FlowDataListVO>> queryMyWaitProcess(@RequestBody FlowDataSearch search) {
        PageInfo<FlowDataListVO> listVOPageInfo = flowDataService.queryMyWaitProcess(search);
        return Result.succeed(listVOPageInfo);
    }


//    @ApiOperation(value = "获取待审批数")
//    @GetMapping("/queryWaitAuditNum")
//    public Result<Map<String, Integer>> queryWaitAuditNum() {
//        return Result.succeed(flowDataService.queryWaitAuditNum());
//    }

    @ApiOperation(value = "流程控制接口-添加评论")
    @PostMapping("/addEvaluation")
    public Result addEvaluation(@RequestBody FlowEvaluationDTO flowEvaluationDTO) {
        if (flowEvaluationService.addEvaluation(flowEvaluationDTO, flowEvaluationDTO.getInstanceId(), flowEvaluationDTO.getTaskKey(), 2)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }

}
