package com.franzoso.conections;


import Constants.RabbitmqConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {

    private static final String NAME_EXCHANGE = "amq.direct";

    private final AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nameQueue) {
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue queueStock = this.queue(RabbitmqConstants.QUEUE_STOCK);
        Queue queuePrice = this.queue(RabbitmqConstants.QUEUE_PRICE);

        DirectExchange exchange = this.directExchange();

        Binding connectionStock = this.binding(queueStock, exchange);
        Binding connectionPrice = this.binding(queuePrice, exchange);

        this.amqpAdmin.declareQueue(queueStock);
        this.amqpAdmin.declareQueue(queuePrice);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(connectionStock);
        this.amqpAdmin.declareBinding(connectionPrice);
    }
}
