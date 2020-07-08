package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.Product;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import javax.persistence.criteria.*;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends BaseDao<Product, Integer> implements ProductDao {

  @Override
  public List<Product> getProducts(Integer fatherId) {
    String hql = "from Product p ";
    if (fatherId == -1) {
      hql += " where p.parent is null";
    } else {
      hql += " where p.parent.id = " + fatherId;
    }

    return getSession().createQuery(hql).list();
  }

  @Override
  protected Class<Product> getClazz() {
    return Product.class;
  }

  @Override
  public Product getByName(String name) {
    Session session = getSession();

    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Product> query = cb.createQuery(getClazz());
    Root<Product> root = query.from(getClazz());
    query.where(cb.equal(root.get("identifier"), name));

    return session.createQuery(query).uniqueResultOptional().orElse(null);
  }
}
