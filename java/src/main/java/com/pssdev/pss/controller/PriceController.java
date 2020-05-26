package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Price;
import com.pssdev.pss.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class PriceController {
   @Autowired
   private PriceService priceService;

   @PostMapping("/price")
   public List<Price> insertPrices(@RequestBody Price price) throws Exception {
      priceService.insertPrice(price);
      return priceService.getPrices();
   }

   @DeleteMapping("/price")
   public List<Price> deletePrice(@RequestBody Price price) {
      priceService.deletePrice(price);

      return priceService.getPrices();
   }

   @PutMapping("/price")
   public List<Price> modifyPrice(@RequestBody Price price) throws Exception {
      priceService.modifyPrice(price);

      return priceService.getPrices();
   }

   @GetMapping("/price")
   public List<Price> getProducts() {
      return priceService.getPrices();
   }
}
