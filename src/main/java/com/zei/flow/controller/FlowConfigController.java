package com.zei.flow.controller;

import com.zei.flow.api.base.Result;
import com.zei.flow.api.dto.FlowConfigDTO;
import com.zei.flow.api.dto.FlowModelDTO;
import com.zei.flow.api.vo.FlowModelVO;
import com.zei.flow.service.IFlowConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:45
 */
@Api(tags = "审批配置接口")
@RestController
@RequestMapping("/flowConfig")
public class FlowConfigController {

    @Autowired
    IFlowConfigService flowConfigService;

    @ApiOperation(value = "查询所有流程")
    @GetMapping("/queryFlowModel")
    public Result<List<FlowModelVO>> queryFlowModel() {
        return Result.succeed(flowConfigService.queryFlowModel());
    }

    @ApiOperation(value = "获取流程信息")
    @GetMapping("/getFlowModel/{id}")
    public Result<FlowModelVO> getFlowModel(@PathVariable(value = "id") Long id) {
        return Result.succeed(flowConfigService.getFlowModel(id));
    }

    @ApiOperation(value = "添加流程")
    @PostMapping("/addFlowModel")
    public Result addFlowModel(@Validated @RequestBody FlowModelDTO flowModelDTO) {
        if (flowConfigService.addFlowModel(flowModelDTO)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }

    @ApiOperation(value = "更新流程")
    @PostMapping("/updateFlowModel")
    public Result updateFlowModel(@Validated @RequestBody FlowModelDTO flowModelDTO) {
        if (flowConfigService.updateFlowModel(flowModelDTO)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }

    @ApiOperation(value = "删除流程")
    @GetMapping("/deleteFlowModel/{id}")
    public Result deleteFlowModel(@PathVariable(value = "id") Long id) {
        if (flowConfigService.deleteFlowModel(id)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }

    @ApiOperation(value = "新增流程配置")
    @PostMapping("/addFlowInstance")
    public Result addFlowInstance(@Validated @RequestBody FlowConfigDTO flowConfigDTO) {
        if (flowConfigService.addFlowInstance(flowConfigDTO)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }
}
