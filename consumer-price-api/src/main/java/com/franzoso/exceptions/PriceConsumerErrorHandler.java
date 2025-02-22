package com.franzoso.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class PriceConsumerErrorHandler implements ErrorHandler {

  @Override
  public void handleError(Throwable t) {
    String nameQueue = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
    System.out.println(nameQueue);

    String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
    System.out.println(message);

    System.out.println(t.getCause().getMessage());

    throw new AmqpRejectAndDontRequeueException("Should not return the queue!");
  }
}
