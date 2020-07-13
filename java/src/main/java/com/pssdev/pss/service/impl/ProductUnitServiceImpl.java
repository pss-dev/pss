package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.ProductUnitDao;
import com.pssdev.pss.entity.ProductUnit;
import com.pssdev.pss.service.ProductUnitService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductUnitServiceImpl implements ProductUnitService {
  @Autowired
  private ProductUnitDao productUnitDao;

  @Audit(value = ResourceEnum.UNIT)
  @Override
  public void insertProductUnit(ProductUnit productUnit) throws Exception {
    this.productUnitDao.insert(productUnit);
  }

  @Audit(value = ResourceEnum.UNIT, actionType = ActionType.DELETE)
  @Override
  public void deleteProductUnit(ProductUnit productUnit) {
    this.productUnitDao.delete(productUnit);
  }

  @Audit(value = ResourceEnum.UNIT, actionType = ActionType.MODIFY)
  @Override
  public void modifyProductUnit(ProductUnit productUnit) throws Exception {
    ProductUnit old = this.productUnitDao.get(productUnit.getId());

    if (old != null) {
      old.setNote(productUnit.getNote());
      old.setName(productUnit.getName());
      this.productUnitDao.update(old);
    } else {
      throw new Exception("商品单位不存在");
    }
  }

  @Override
  public List<ProductUnit> getProductUnits(Integer fatherId) {
    if (fatherId != null) {
      return productUnitDao.getProductUnits(fatherId);
    } else {
      return getProductUnits();
    }

  }

  @Override
  public List<ProductUnit> getProductUnits() {
    return this.productUnitDao.getAll();
  }

  @Transactional
  @Override
  public ProductUnit getUnitByName(String name) {
    return productUnitDao.getByName(name);
  }
}
