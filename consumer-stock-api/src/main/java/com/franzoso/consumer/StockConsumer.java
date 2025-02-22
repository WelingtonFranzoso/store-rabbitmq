package com.franzoso.consumer;

import Constants.RabbitmqConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.StockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {

  @RabbitListener(queues = RabbitmqConstants.QUEUE_STOCK)
  private void consumer(String message) throws JsonProcessingException, InterruptedException {
    StockDTO stock = new ObjectMapper().readValue(message, StockDTO.class);

    System.out.println("ORDER NUMBER: " + stock.orderNumber);
    System.out.println("QUANTITY: " + stock.quantity);
    System.out.println("------------------------------------");

    Thread.sleep(15000);
    //throw new IllegalArgumentException("Invalid argument!");
  }
}
