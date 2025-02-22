package com.franzoso.controller;

import Constants.RabbitmqConstants;
import com.franzoso.service.RabbitmqService;
import dto.StockDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

  private final RabbitmqService rabbitmqService;

    public StockController(RabbitmqService rabbitmqService) {
        this.rabbitmqService = rabbitmqService;
    }

    @PutMapping
  private ResponseEntity changeStock(@RequestBody StockDTO stock){
    System.out.println("STOCK HAS CHANGED! ORDER NUMBER: " + stock.orderNumber + " NEW QUANTITY: " + stock.quantity);
    this.rabbitmqService.sendMessage(RabbitmqConstants.QUEUE_STOCK, stock);
    return new ResponseEntity(HttpStatus.OK);
  }
}
