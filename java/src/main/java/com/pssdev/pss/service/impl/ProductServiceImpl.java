package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.PriceValue;
import com.pssdev.pss.entity.Product;
import com.pssdev.pss.entity.ProductUnitPrice;
import com.pssdev.pss.service.ProductService;
import com.pssdev.pss.util.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
   @Autowired
   private ProductDao productDao;

   @Override
   public void insertProduct(Product product) throws Exception {
      this.productDao.insert(product);
   }

   @Override
   public void deleteProduct(Product product) {
      this.productDao.delete(product);
   }

   @Override
   public void modifyProduct(Product product) throws Exception {
      Product old = this.productDao.get(product.getId());

      if(old == null) {
         throw new Exception("商品不存在");
      }
      else {
         modifyProduct(old, product);
         Set<ProductUnitPrice> unitPrices = product.getUnits();
         Set<ProductUnitPrice> oldUnitPrices = product.getUnits();

         if(oldUnitPrices == null) {
            oldUnitPrices = new HashSet<>();
         }

         if(unitPrices != null && unitPrices.size() > 0) {
            for(ProductUnitPrice unitPrice : unitPrices) {
               if(ActionType.ADD == unitPrice.getActionType()) {
                  oldUnitPrices.add(unitPrice);
               }
               else if(ActionType.MODIFY == unitPrice.getActionType()) {
                  modifyUnitPrice(unitPrice, oldUnitPrices);
               }
               else if(ActionType.DELETE == unitPrice.getActionType()) {
                  oldUnitPrices.remove(unitPrice);
               }
            }
         }

         this.productDao.update(old);
      }
   }

   private void modifyProduct(Product oldProduct, Product newProduct) {
      oldProduct.setName(newProduct.getName());
      oldProduct.setInitials(newProduct.getInitials());
      oldProduct.setSpecification(newProduct.getSpecification());
      oldProduct.setType(newProduct.getType());
      oldProduct.setAddress(newProduct.getAddress());
      oldProduct.setUsed(newProduct.isUsed());
      oldProduct.setStopPurchase(newProduct.isStopPurchase());
      oldProduct.setStop(newProduct.isStop());
      oldProduct.setParent(newProduct.getParent());
   }

   private void modifyUnitPrice(ProductUnitPrice unitPrice,
                                Set<ProductUnitPrice> oldUnitPrices)
   {
      ProductUnitPrice modify = findUnitPrice(unitPrice.getId(), oldUnitPrices);
      this.modifyUnitPrice(modify, unitPrice);

   }

   private void modifyUnitPrice(ProductUnitPrice oldUnitPrice, ProductUnitPrice newUnitPrice) {
      Set<PriceValue> oldPrices = oldUnitPrice.getPrices();
      Set<PriceValue> newPrices = newUnitPrice.getPrices();

      if(oldPrices == null || newPrices == null) {
         return;
      }

      for(PriceValue priceValue : oldPrices) {
         PriceValue newPrice = this.findPriceValue(priceValue, newPrices);

         if(newPrice != null) {
            priceValue.setValue(newPrice.getValue());
         }
      }
   }

   private PriceValue findPriceValue(PriceValue priceValue, Set<PriceValue> prices) {
      for(PriceValue price : prices) {
         if(priceValue.equals(price)) {
            return price;
         }
      }

      return null;
   }

   private ProductUnitPrice findUnitPrice(int id, Set<ProductUnitPrice> unitPrices) {
      if(unitPrices == null) {
         return null;
      }

      for(ProductUnitPrice unitPrice : unitPrices) {
         if(unitPrice.getId() == id) {
            return unitPrice;
         }
      }

      return null;
   }

   @Override
   public List<Product> getProducts(Integer fatherId) {
      if(!StringUtils.isEmpty(fatherId)) {
         Product father = this.productDao.get(fatherId);

         if(father == null) {
            return this.productDao.getProducts(fatherId);
         }
      }

      return this.productDao.getAll();
   }

   @Override
   public List<Product> getProducts() {
      return this.productDao.getAll();
   }
}
