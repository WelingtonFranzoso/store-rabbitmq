package com.franzoso.controller;


import Constants.RabbitmqConstants;
import com.franzoso.service.RabbitmqService;
import dto.PriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final RabbitmqService rabbitmqService;

    public PriceController(RabbitmqService rabbitmqService) {
        this.rabbitmqService = rabbitmqService;
    }

    @PutMapping
    private ResponseEntity changePrice(@RequestBody PriceDTO price) {
        System.out.println("PRICE HAS CHANGED! ORDER NUMBER: " + price.orderNumber + " NEW PRICE: " + price.price);
        this.rabbitmqService.sendMessage(RabbitmqConstants.QUEUE_PRICE, price);
        return new ResponseEntity(HttpStatus.OK);
    }
}
