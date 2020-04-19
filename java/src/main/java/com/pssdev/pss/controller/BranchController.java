package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Branch;
import com.pssdev.pss.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class BranchController {
    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/branch")
    @ResponseBody
    public List<Branch> getBranch(@RequestParam Integer fatherID) {
        return branchService.getBranch(fatherID);
    }

    private BranchService branchService;
}
