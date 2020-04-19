package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Department;
import com.pssdev.pss.service.DepartmentService;
import io.swagger.annotations.*;
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
   @ApiOperation("获取指定部门下的子部门集合, 如果父部门未指定则获取所有部门信息")
   public List<Department> getDepartment(@ApiParam("上级部门 ID") @RequestParam(name = "parentId", required = false) Integer parentId) {
      return departmentService.getDepartments(parentId);
   }

   private final DepartmentService departmentService;
}