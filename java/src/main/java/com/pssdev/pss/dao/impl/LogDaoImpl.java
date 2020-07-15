package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.LogDao;
import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.*;

@Repository("LogDao")
public class LogDaoImpl extends BaseDao<Log, Integer> implements LogDao {
  @Override
  protected Class<Log> getClazz() {
    return Log.class;
  }

  @Override
  public List<Log> search(LogSearchModel model) {
    Session session = getSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);
    Root<Log> root = criteriaQuery.from(Log.class);
    List<Predicate> conditons = new ArrayList<>();

    if(model != null) {
      // search query result: [start, end)
      if(model.getStartDate() > 0) {
        conditons.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), new Date(model.getStartDate())));
      }

      if (model.getEndDate() > 0) {
        conditons.add(criteriaBuilder.lessThan(root.get("date"), new Date(model.getEndDate())));
      }
    }

    criteriaQuery.where(criteriaBuilder.and(conditons.toArray(new Predicate[0])));

    return session.createQuery(criteriaQuery).getResultList();
  }
}
