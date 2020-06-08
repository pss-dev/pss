package com.pssdev.pss.service;

import java.io.Serializable;
import java.util.List;

/**
 * tree structure service.
 * @param <T> entity
 */
public interface TreeStructureService <T extends Serializable> {

   /**
    * Get top level entity.
    */
   List<T> getTop();

   static final Integer TOP_FLAG1 = -1;
   static final Long TOP_FLAG2 = -1L;
}
