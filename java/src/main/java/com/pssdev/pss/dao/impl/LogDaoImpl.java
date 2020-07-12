package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.LogDao;
import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
      if(model.getStartDate() > 0) {
        conditons.add(criteriaBuilder.greaterThan(root.get("date"), new Date(model.getStartDate())));
      }

      if (model.getEndDate() > 0) {
        conditons.add(criteriaBuilder.lessThan(root.get("date"), new Date(model.getEndDate())));
      }
    }

    criteriaQuery.where(criteriaBuilder.and(conditons.toArray(new Predicate[0])));

    return session.createQuery(criteriaQuery).getResultList();
  }
}
