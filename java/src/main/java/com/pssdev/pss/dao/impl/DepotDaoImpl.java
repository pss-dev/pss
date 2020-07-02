package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.entity.Depot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("depotDao")
public class DepotDaoImpl extends BaseDao<Depot, Integer> implements DepotDao {

  @Override
  public List<Depot> getDepots(Integer fatherId) {
    String hql = "from Depot d ";

    if (fatherId == -1) {
      hql += "where d.parent is null";
    } else {
      hql += "where d.parent.id= " + fatherId;
    }

    return getSession().createQuery(hql).list();
  }

  @Override
  protected Class<Depot> getClazz() {
    return Depot.class;
  }
}
