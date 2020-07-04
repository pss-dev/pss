package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.entity.DepotItem;
import com.pssdev.pss.entity.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
  public void putInProducts(List<DepotItem> items) {
    assert items != null;
    Session session = getSession();
    System.out.println("=========== putInProducts  items " + items.size());
    for (DepotItem item : items) {
      DepotItem oldItem = getItem(item);

      if (oldItem != null) {
        oldItem.setProductCount(oldItem.getProductCount() + item.getProductCount());
        session.update(oldItem);
      } else {
        System.out.println("=========== putInProducts " + item.toString());
        session.save(item);
      }
    }

    session.flush();
  }

  private DepotItem getItem(DepotItem item) {
    DepotItem oldItem = null;

    Query query = getSession().createQuery("from DepotItem di where di.depot.id = " + item.getDepot().getId()
        + " and di.product.id = " + item.getProduct().getId());

    if (query.getResultList().size() != 0) {
      oldItem = (DepotItem) query.getSingleResult();
    }

    return oldItem;
  }

  @Override
  public void removeProducts(List<DepotItem> items) {
    assert items != null;
    Session session = getSession();

    for (DepotItem item : items) {
      DepotItem oldItem = getItem(item);
      assert oldItem != null;
      oldItem.setProductCount(oldItem.getProductCount() - item.getProductCount());
      session.update(oldItem);
    }

    session.flush();
  }

  @Override
  public List<DepotItem> getInventoryProducts(Depot depot) {
    depot = get(depot.getId());

    if (depot == null) {
      return new ArrayList<>();
    }

    return depot.getDepotItems();
  }

  @Override
  public DepotItem inventoryProduct(Depot depot, Product product) {
    String hql = "from DepotItem di where di.depot.id =" + depot.getId() + " and " + "di.product.id = "
        + product.getId();
    List<DepotItem> findItems = getSession().createQuery(hql).list();

    return findItems == null || findItems.size() == 0 ? null : findItems.get(0);
  }

  @Override
  protected Class<Depot> getClazz() {
    return Depot.class;
  }
}
