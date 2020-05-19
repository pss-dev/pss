package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.entity.Depot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepotDaoImpl extends BaseDao implements DepotDao {
  @Override
  public void insertDepot(Depot Depot) {
    getSession().save(Depot);
  }

  @Override
  public void deleteDepot(Integer id) {
    getSession().createQuery("delete from Depot c where c.id =" + id).executeUpdate();
  }

  @Override
  public void deleteDepot(Depot Depot) {
    getSession().delete(Depot);
  }

  @Override
  public void modifyDepot(Depot Depot) {
    getSession().update(Depot);
  }

  @Override
  public Depot getDepot(Integer id) {
    return getSession().get(Depot.class, id);
  }

  @Override
  public List<Depot> getDepots() {
    return getSession().createQuery("from Depot").list();
  }

  @Override
  public List<Depot> getDepots(Integer fatherId) {
    return getSession().createQuery("from Depot c where c.fatherId = " + fatherId).list();
  }
}
