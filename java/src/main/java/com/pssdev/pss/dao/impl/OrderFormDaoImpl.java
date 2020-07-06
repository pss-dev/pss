package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.OrderFormDao;
import com.pssdev.pss.entity.OrderForm;
import com.pssdev.pss.model.OrderFormSearchModel;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderFormDaoImpl extends BaseDao<OrderForm, Long> implements OrderFormDao {
  @Override
  protected Class<OrderForm> getClazz() {
    return OrderForm.class;
  }

  @Override
  public List<OrderForm> search(OrderFormSearchModel model) {
    Session session = getSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<OrderForm> criteriaQuery = criteriaBuilder.createQuery(OrderForm.class);
    Root<OrderForm> root = criteriaQuery.from(OrderForm.class);
    List<Predicate> conditons = new ArrayList<>();

    if (model.getOrderFormType() != null) {
      conditons.add(criteriaBuilder.equal(root.get("type"), model.getOrderFormType()));
    }

    if (model.getOrderFormStatus() != null) {
      conditons.add(criteriaBuilder.equal(root.get("status"), model.getOrderFormStatus()));
    }

    if (model.getStartDate() != null) {
      conditons.add(criteriaBuilder.greaterThan(root.get("createDate"), model.getStartDate()));
    }

    if (model.getEndDate() != null) {
      conditons.add(criteriaBuilder.lessThan(root.get("createDate"), model.getEndDate()));
    }

    if (model.getBranchID() != null) {
      conditons.add(criteriaBuilder.equal(root.get("branch.id"), model.getBranchID()));
    }

    if (model.getCompanyID() != null) {
      conditons.add(criteriaBuilder.equal(root.get("company.id"), model.getCompanyID()));
    }

    if (model.getEmployeeID() != null) {
      conditons.add(criteriaBuilder.equal(root.get("employee.id"), model.getEmployeeID()));
    }

    if (model.getDepartmentID() != null) {
      conditons.add(criteriaBuilder.equal(root.get("department.id"), model.getDepartmentID()));
    }

    if (model.getDepotID() != null) {
      conditons.add(criteriaBuilder.equal(root.get("depot.id"), model.getDepotID()));
    }

    if (model.getAccountID() != null) {
      conditons.add(criteriaBuilder.equal(root.get("account.id"), model.getAccountID()));
    }

    criteriaQuery.where(criteriaBuilder.and(conditons.toArray(new Predicate[0])));

    return session.createQuery(criteriaQuery).getResultList();
  }
}
