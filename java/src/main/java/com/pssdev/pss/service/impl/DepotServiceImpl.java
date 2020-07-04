package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.entity.DepotItem;
import com.pssdev.pss.entity.Product;
import com.pssdev.pss.service.DepotService;
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
  public List<DepotItem> inventoryProducts(int depotId) {
    Depot depot = depotDao.get(depotId);
    assert depot != null;

    return depotDao.getInventoryProducts(depot);
  }

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
  public List<Depot> getDepots(Integer fatherId) {
    return fatherId == null ? this.getDepots() : depotDao.getDepots(fatherId);
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
}
