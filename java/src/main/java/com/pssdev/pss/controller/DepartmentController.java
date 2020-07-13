package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Department;
import com.pssdev.pss.service.DepartmentService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pssdev.pss.service.TreeStructureService.TOP_FLAG1;
import static com.pssdev.pss.service.TreeStructureService.TOP_FLAG2;

@RestController
@RequestMapping("/api/1.0")
@Api("部门信息控制器")
public class DepartmentController {

  @Autowired
  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @GetMapping("/department")
  @RequiresPermissions("*:64:r")
  @ApiOperation("获取指定部门集合, 如果父部门未指定则获取所有部门信息")
  public List<Department> getDepartment(
      @ApiParam("上级部门 ID") @RequestParam(name = "fatherID", required = false) Integer fatherID) {
    if (TOP_FLAG1.equals(fatherID) || TOP_FLAG2.equals(fatherID)) {
      return departmentService.getTop();
    }

    List<Department> departments = departmentService.getDepartments(fatherID);

    return departments;
  }

  @PostMapping("/department/duplicate")
  @RequiresPermissions("*:64:r")
  public boolean checkDepartmentDuplicate(@RequestBody Department department) throws Exception {
    Department dept = departmentService.getDepartmentByName(department.getName());

    return dept != null && dept.getId() != department.getId();
  }

  @PostMapping("/department")
  @RequiresPermissions("*:64:w")
  @ApiOperation("添加部门信息")
  public Integer insertDepartment(@ApiParam("部门信息") @RequestBody Department department) {
    return departmentService.insertDepartment(department);
  }

  @PutMapping("/department")
  @RequiresPermissions("*:64:w")
  @ApiOperation("修改部门信息")
  public void updateDepartment(@ApiParam("部门信息") @RequestBody Department department) {
    departmentService.updateDepartment(department);
  }

  @DeleteMapping("/department")
  @RequiresPermissions("*:64:d")
  @ApiOperation("删除部门信息")
  public void deleteDepartment(@ApiParam("将要删除的部门信息") @RequestBody Department department) {
    departmentService.deleteDepartment(department);
  }

  private final DepartmentService departmentService;
}
