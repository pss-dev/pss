package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.DepotService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepotServiceImpl implements DepotService {
  @Autowired
  private DepotDao depotDao;
  @Autowired
  private ProductDao productDao;

  @Audit(value = ResourceEnum.DEPOT)
  @Override
  @Transactional
  public void insertDepot(Depot Depot) throws Exception {
    if (!StringUtils.isEmpty(Depot.getParent())) {
      Depot father = depotDao.get(Depot.getParent().getId());

      if (father == null) {
        throw new Exception("找不所属仓库");
      }
    }

    if (Depot.getId() != null && depotDao.get(Depot.getId()) != null) {
      throw new Exception("仓库已经存在");
    }

    depotDao.insert(Depot);
  }

  @Audit(value = ResourceEnum.DEPOT, actionType = ActionType.DELETE)
  @Override
  @Transactional
  public void deleteDepot(Depot depot) {
    depotDao.delete(depot);
  }

  @Override
  public DepotItem inventoryProduct(int depotId, int productId) {
    Depot depot = this.depotDao.get(depotId);
    assert depot != null;
    Product product = this.productDao.get(productId);
    assert product != null;

    return depotDao.inventoryProduct(depot, product);
  }

  @Override
  @Transactional
  public List<DepotItem> inventoryProducts(int depotId, Integer productFatherID) {
    Depot depot = depotDao.get(depotId);
    assert depot != null;

    return depotDao.getInventoryProducts(depot, productFatherID);
  }

  @Audit(value = ResourceEnum.DEPOT, actionType = ActionType.MODIFY)
  @Override
  @Transactional
  public void updateDepot(Depot depot) throws Exception {
    Depot oldDepot = depotDao.get(depot.getId());

    if (oldDepot == null) {
      throw new Exception("仓库不存在");
    } else {
      oldDepot.setName(depot.getName());
      oldDepot.setBranch(depot.getBranch());
      oldDepot.setInitials(depot.getInitials());
      depotDao.update(oldDepot);
    }
  }

  @Override
  @Transactional
  public List<Depot> getDepots(Integer fatherId, Integer branchId) {
    return fatherId == null && branchId == null ? this.getDepots() : depotDao.getDepots(fatherId, branchId);
  }

  @Override
  @Transactional
  public List<Depot> getDepots() {
    return depotDao.getAll();
  }

  @Override
  @Transactional
  public void putInProducts(List<DepotItem> items) {
    depotDao.putInProducts(items);
  }

  @Override
  @Transactional
  public void putOutProducts(List<DepotItem> items) {
    depotDao.putOutProducts(items);
  }

  @Override
  @Transactional
  public List<DepotItem> getItemsByOrderForm(OrderForm orderForm) {
    return depotDao.getItemsByOrderForm(orderForm);
  }

  @Transactional
  @Override
  public Depot getDepotByName(String name) {
    return depotDao.getByName(name);
  }
}
