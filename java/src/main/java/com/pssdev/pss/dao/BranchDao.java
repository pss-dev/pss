package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Branch;

public interface BranchDao extends TreeStructureDao<Branch, Integer> {
  Branch getByName(String name);
}
