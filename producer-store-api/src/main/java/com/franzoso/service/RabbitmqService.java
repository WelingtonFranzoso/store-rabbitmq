package com.franzoso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {

  private final RabbitTemplate rabbitTemplate;

  private final ObjectMapper objectMapper;

    public RabbitmqService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String nameQueue, Object message){
    try {
      String messageJson = this.objectMapper.writeValueAsString(message);
      this.rabbitTemplate.convertAndSend(nameQueue, messageJson);
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
