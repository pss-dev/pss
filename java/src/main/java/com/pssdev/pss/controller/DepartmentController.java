package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Department;
import com.pssdev.pss.service.DepartmentService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/")
@Api("部门信息控制器")
public class DepartmentController {

  @Autowired
  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @GetMapping("/department")
  @RequiresPermissions("user:64:r")
  @ApiOperation("获取指定部门集合, 如果父部门未指定则获取所有部门信息")
  public List<Department> getDepartment(
      @ApiParam("上级部门 ID") @RequestParam(name = "fatherID", required = false) Integer fatherID) {
    List<Department> departments = departmentService.getDepartments(fatherID);

    return departments;
  }

  @PostMapping("/department")
  @RequiresPermissions("user:64:w")
  @ApiOperation("添加部门信息")
  public Integer insertDepartment(@ApiParam("部门信息") @RequestBody Department department) {
    return departmentService.insertDepartment(department);
  }

  @PutMapping("/department")
  @RequiresPermissions("user:64:w")
  @ApiOperation("修改部门信息")
  public void updateDepartment(@ApiParam("部门信息") @RequestBody Department department) {
    departmentService.updateDepartment(department);
  }

  @DeleteMapping("/department")
  @RequiresPermissions("user:64:d")
  @ApiOperation("删除部门信息")
  public void deleteDepartment(@ApiParam("部门 ID") @RequestBody Department department) {
    departmentService.deleteDepartment(department);
  }

  private final DepartmentService departmentService;
}
