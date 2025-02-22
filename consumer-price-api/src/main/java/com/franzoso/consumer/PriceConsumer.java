package com.franzoso.consumer;

import Constants.RabbitmqConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PriceDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PriceConsumer {

  @RabbitListener(queues = RabbitmqConstants.QUEUE_PRICE)
  private void consumer(String message) throws JsonProcessingException, InterruptedException {
    PriceDTO price = new ObjectMapper().readValue(message, PriceDTO.class);

    System.out.println("ORDER NUMBER: " + price.orderNumber);
    System.out.println("PRICE: " + price.price);
    System.out.println("------------------------------------");

    Thread.sleep(15000);
    //throw new IllegalArgumentException("Invalid argument!");
  }
}
