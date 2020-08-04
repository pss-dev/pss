package com.pssdev.pss.controller;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.*;
import com.pssdev.pss.model.GeneratePriceModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/1.0")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping("/product")
  @RequiresPermissions("*:2:w")
  public void insertProducts(@RequestBody Product product) throws Exception {
    productService.insertProduct(product);
  }

  @PostMapping("/product/duplicate")
  @RequiresPermissions("*:2:r")
  public boolean checkProductDuplicate(@RequestBody Product product) throws Exception {
    Product pro = productService.getProductByName(product.getName());
    System.out.println("==========  " + pro.getId() + " === " + product.getId());
    return pro != null && pro.getId() != product.getId();
  }

  @PostMapping("/product/upload")
  @RequiresPermissions("*:2:w")
  public void uploadProducts(String parentId, MultipartFile file) throws Exception {
    productService.importData(file, parentId);
  }

  @GetMapping("/product/template")
  public void getDataTemplate(HttpServletResponse response) throws Exception {
    ServletOutputStream out = response.getOutputStream();
    String fileName = "Pss MS Products Template" + ExportService.Excel_xls;
    productService.getDataTemplate(out);

    String type = new MimetypesFileTypeMap().getContentType(fileName);

    response.setCharacterEncoding("utf-8");
    response.setContentType(type);
    String downloadFileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
    response.setHeader("Content-Disposition", "attachment;fileName=" + downloadFileName);
    out.flush();
  }

  @DeleteMapping("/product")
  @RequiresPermissions("*:2:d")
  public void deleteProduct(@RequestBody Product product) {
    productService.deleteProduct(product);
  }

  @PutMapping("/product")
  @RequiresPermissions("*:2:w")
  public void modifyProduct(@RequestBody Product product) throws Exception {
    productService.modifyProduct(product);
  }

  @GetMapping("/product")
  @RequiresPermissions("*:2:r")
  public List<Product> getProducts(@RequestParam(required = false) Integer fatherID) {
    return productService.getProducts(fatherID);
  }

  @PostMapping("/product/generate-price")
  @RequiresPermissions("*:2:w")
  public void generatePrice(@RequestBody GeneratePriceModel model) {
    System.out.println("========= generatePrice " + model.getCalculate());
    productService.generatePrice(model);
  }
}
