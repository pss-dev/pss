package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.ProductUnitDao;
import com.pssdev.pss.entity.ProductUnit;
import com.pssdev.pss.service.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductUnitServiceImpl implements ProductUnitService {
  @Autowired
  private ProductUnitDao productUnitDao;

  @Override
  public void insertProductUnit(ProductUnit productUnit) throws Exception {
    this.productUnitDao.insertProductUnit(productUnit);
  }

  @Override
  public void deleteProductUnit(ProductUnit productUnit) {
    this.productUnitDao.deleteProductUnit(productUnit);
  }

  @Override
  public void modifyProductUnit(ProductUnit productUnit) throws Exception {
    ProductUnit old = this.productUnitDao.getProductUnit(productUnit.getId());

    if (old != null) {
      old.setNote(productUnit.getNote());
      old.setName(productUnit.getName());
      this.productUnitDao.modifyProductUnit(old);
    } else {
      throw new Exception("商品单位不存在");
    }
  }

  @Override
  public List<ProductUnit> getProductUnits(Integer fatherId) {
    if(fatherId != null) {
      ProductUnit father = this.productUnitDao.getProductUnit(fatherId);

      if(father != null) {
        return this.getProductUnits(fatherId);
      }
      else {
        return new ArrayList<>();
      }
    }
    else {
      return getProductUnits();
    }

  }

  @Override
  public List<ProductUnit> getProductUnits() {
    return this.productUnitDao.getProductUnits();
  }
}
