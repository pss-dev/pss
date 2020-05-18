package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Branch;
import com.pssdev.pss.service.BranchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
@Api("分支控制器")
public class BranchController {
    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/branch")
    @ApiOperation("获取指定分支集合")
    public Branch getBranch(@ApiParam("上级分支 ID") @RequestParam Integer fatherID) {
        return branchService.getBranch(fatherID);
    }

    @GetMapping("/branches")
    @ApiOperation("获取所有分支信息")
    public List<Branch> getBranches() {
        return branchService.getBranches();
    }

    @PutMapping("/branch")
    @ApiOperation("添加分支信息")
    public Integer insertBranch(@ApiParam("分支信息") @RequestBody Branch branch) {
        return branchService.insertBranch(branch);
    }

    @PostMapping("/branch")
    @ApiOperation("修改分支信息")
    public void updateBranch(@ApiParam("分支信息") @RequestBody Branch branch) {
        branchService.updateBranch(branch);
    }

    @DeleteMapping("/branch")
    @ApiOperation("删除分支信息")
    public void deleteBranch(@ApiParam("分支 ID") @RequestParam Integer id) {
        branchService.deleteBranch(id);
    }

    private BranchService branchService;
}