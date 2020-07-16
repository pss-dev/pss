package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.PriceService;
import com.pssdev.pss.service.ProductService;
import com.pssdev.pss.service.ProductUnitService;
import com.pssdev.pss.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import static com.pssdev.pss.util.ExportUtil.DEFAULT_COL_WIDTH;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductDao productDao;
  @Autowired
  private ProductUnitService unitService;
  @Autowired
  private PriceService priceService;

  @Audit(value = ResourceEnum.PRODUCT)
  @Override
  public Integer insertProduct(Product product) throws Exception {
    return this.productDao.insert(product);
  }

  @Audit(value = ResourceEnum.PRODUCT, actionType = ActionType.DELETE)
  @Override
  public void deleteProduct(Product product) {
    this.productDao.delete(product);
  }

  @Audit(value = ResourceEnum.PRODUCT, actionType = ActionType.MODIFY)
  @Override
  public void modifyProduct(Product product) throws Exception {
    Product old = this.productDao.get(product.getId());

    if (old == null) {
      throw new Exception("商品不存在");
    } else {
      modifyProduct(old, product);
      List<ProductUnitPrice> unitPrices = product.getUnits();
      List<ProductUnitPrice> oldUnitPrices = old.getUnits();

      if (oldUnitPrices == null) {
        oldUnitPrices = new ArrayList<>();
      }

      if (unitPrices != null && unitPrices.size() > 0) {
        for (ProductUnitPrice unitPrice : unitPrices) {
          if (ActionType.ADD.getType() == unitPrice.getActionType()) {
            oldUnitPrices.add(unitPrice);
          } else if (ActionType.MODIFY.getType() == unitPrice.getActionType()) {
            modifyUnitPrice(unitPrice, oldUnitPrices);
          } else if (ActionType.DELETE.getType() == unitPrice.getActionType()) {
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
    oldProduct.setStopPurchase(newProduct.isStopPurchase());
    oldProduct.setParent(newProduct.getParent());
    oldProduct.setIdentifier(newProduct.getIdentifier());
    oldProduct.setSellDefaultUnit(newProduct.getSellDefaultUnit());
    oldProduct.setPurchaseDefaultUnit(newProduct.getPurchaseDefaultUnit());
  }

  private void modifyUnitPrice(ProductUnitPrice unitPrice, List<ProductUnitPrice> oldUnitPrices) {
    ProductUnitPrice modify = findUnitPrice(unitPrice.getId(), oldUnitPrices);
    this.modifyUnitPrice(modify, unitPrice);

  }

  private void modifyUnitPrice(ProductUnitPrice oldUnitPrice, ProductUnitPrice newUnitPrice) {
    List<PriceValue> oldPrices = oldUnitPrice.getPrices();
    List<PriceValue> newPrices = newUnitPrice.getPrices();

    if (oldPrices == null || newPrices == null) {
      return;
    }

    for (PriceValue priceValue : oldPrices) {
      PriceValue newPrice = this.findPriceValue(priceValue, newPrices);

      if (newPrice != null) {
        priceValue.setValue(newPrice.getValue());
      }
    }
  }

  private PriceValue findPriceValue(PriceValue priceValue, List<PriceValue> prices) {
    for (PriceValue price : prices) {
      if (priceValue.equals(price)) {
        return price;
      }
    }

    return null;
  }

  private ProductUnitPrice findUnitPrice(int id, List<ProductUnitPrice> unitPrices) {
    if (unitPrices == null) {
      return null;
    }

    for (ProductUnitPrice unitPrice : unitPrices) {
      if (unitPrice.getId() == id) {
        return unitPrice;
      }
    }

    return null;
  }

  @Override
  public List<Product> getProducts(Integer fatherId) {
    if (!StringUtils.isEmpty(fatherId)) {
      return this.productDao.getProducts(fatherId);
    }

    return this.productDao.getAll();
  }

  @Override
  public List<Product> getProducts() {
    return this.productDao.getAll();
  }

  @Override
  public Product getProduct(Integer productId) {
    return this.productDao.get(productId);
  }

  @Transactional
  @Override
  public Product getProductByName(String name) {
    return productDao.getByName(name);
  }

  @Transactional
  @Override
  public void getDataTemplate(OutputStream out) throws Exception {
    int columnCount = Product.getColumnCount();

    HSSFWorkbook workbook = HSSFWorkbookFactory.createWorkbook();
    HSSFSheet sheet = workbook.createSheet("Pss MS product");

    for (int i = 0; i < columnCount; i++) {
      sheet.setColumnWidth(i, DEFAULT_COL_WIDTH);
    }

    HSSFCell cell;
    int rowIndex = 0; // title row
    HSSFRow header = ExportUtil.createRow(workbook, sheet, rowIndex);

    for (int i = 0; i < columnCount; i++) {
      cell = ExportUtil.createHeaderCell(workbook, header, i);
      cell.setCellValue(Product.templatetLabel(i));

      if (i == 0 || i == 1 || i == 6 || i == 7) {
        HSSFCellStyle cellStyle = cell.getCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        cellStyle.setFont(font);
      }
    }

    workbook.write(out);
  }

  @Transactional
  @Override
  public void importData(MultipartFile file, String parentId) throws Exception {
    InputStream in = file.getInputStream();
    String fileName = file.getOriginalFilename();

    Product parent = null;

    if (parentId != null && !"null".equals(parentId)) {
      parent = getProduct(Integer.parseInt(parentId));
    }

    Workbook workbook = ImportUitl.getWorkBook(in, fileName);
    Sheet sheet = workbook.getSheetAt(0);
    List<Product> productList = new ArrayList<>();

    Row rowHeader = sheet.getRow(0);
    int colCount = rowHeader.getPhysicalNumberOfCells();
    int rowCount = sheet.getPhysicalNumberOfRows();

    if (Product.getColumnCount() != colCount) {
      // data error
    }

    List<Price> prices = priceService.getPrices();

    if (prices.size() != 10) {
      // price error
    }

    Map<String, Price> priceMap = new HashMap<>();

    prices.forEach((price) -> {
      priceMap.put(price.getName(), price);
    });

    Map<String, ProductUnit> unitset = new HashMap<>();

    for (int i = 1; i < rowCount; i++) {
      Row row = sheet.getRow(i);

      Product product = new Product();

      String identifier = row.getCell(0).getStringCellValue();
      product.setIdentifier(identifier);

      String name = row.getCell(1).getStringCellValue();
      product.setName(name);

      String initials = row.getCell(2).getStringCellValue();
      product.setInitials(initials);

      String specification = row.getCell(3).getStringCellValue();
      product.setSpecification(specification);

      String type = row.getCell(4).getStringCellValue();
      product.setType(type);

      String address = row.getCell(5).getStringCellValue();
      product.setAddress(address);

      String unitName = row.getCell(6).getStringCellValue();

      ProductUnit unit = unitset.get(unitName);

      if (unit == null) {
        unit = unitService.getUnitByName(unitName);
      }

      if (unit == null) {
        // fix error
      }

      unitset.put(unitName, unit);
      product.setSellDefaultUnit(unit);
      product.setPurchaseDefaultUnit(unit);

      List<PriceValue> priceValues = new ArrayList<>();

      ProductUnitPrice pUnitPrice = new ProductUnitPrice();
      pUnitPrice.setUnit(unit);
      int crate = (int) row.getCell(7).getNumericCellValue();
      pUnitPrice.setCrate(crate);

      for (int k = 0; k < 10; k++) {
        PriceValue priceValue = getPriceValues(row, k + 8, PriceEnum.getPriceName(k), priceMap);
        priceValues.add(priceValue);
      }

      pUnitPrice.setPrices(priceValues);
      pUnitPrice.setDefault(true);
      pUnitPrice.setActionType(ActionType.ADD.getType());

      product.setParent(parent);
      product.setStopPurchase(false);
      product.setActionType(ActionType.ADD.getType());
      productList.add(product);
    }

    for (Product product : productList) {
      insertProduct(product);
    }
  }

  private PriceValue getPriceValues(Row row, int col, String priceName, Map<String, Price> priceMap) {
    PriceValue price = new PriceValue();
    Double pvalue = row.getCell(col).getNumericCellValue();
    price.setValue(pvalue);
    price.setPrice(priceMap.get(priceName));

    return price;
  }

}
