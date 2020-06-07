package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.entity.Depot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("depotDao")
public class DepotDaoImpl extends BaseDao<Depot, Integer> implements DepotDao {

  @Override
  public List<Depot> getDepots(Integer fatherId) {
    return getSession().createQuery("from Depot c where c.parent.id = " + fatherId).list();
  }

  @Override
  protected Class<Depot> getClazz() {
    return Depot.class;
  }
}
