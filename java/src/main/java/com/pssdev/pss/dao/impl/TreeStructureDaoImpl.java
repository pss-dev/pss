package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.TreeStructureDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

public abstract class TreeStructureDaoImpl<T, R extends Serializable> extends BaseDao<T, R> implements TreeStructureDao<T, R> {

   public List<T> getTop() {
      Session session = getSession();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<T> query = builder.createQuery(getClazz());
      Root<T> root = query.from(getClazz());
      query.select(root);

      query.where(builder.isNull(root.get("parent")));

      Query<T> resultQuery = session.createQuery(query);

      return resultQuery != null ? resultQuery.getResultList() : null;
   }

}
