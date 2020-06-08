package com.pssdev.pss.dao;

import java.io.Serializable;
import java.util.List;

public interface TreeStructureDao<T, R extends Serializable> extends CURDDao<T, R> {
   List<T> getTop();
}
