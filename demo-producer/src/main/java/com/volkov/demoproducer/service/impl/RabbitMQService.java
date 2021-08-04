package com.volkov.demoproducer.service.impl;

import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import java.util.logging.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private static final Logger LOGGER = Logger.getLogger(RabbitMQService.class.getName());

    private final AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public RabbitMQService(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(DemoUserDTO demoUserDTO) {
        rabbitTemplate.convertAndSend(exchange, routingkey, demoUserDTO);
        LOGGER.info("sent to mq, message = " + demoUserDTO);
    }
}
