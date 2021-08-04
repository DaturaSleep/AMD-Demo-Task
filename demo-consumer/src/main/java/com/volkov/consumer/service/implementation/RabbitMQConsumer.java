package com.volkov.consumer.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkov.consumer.entity.dto.DemoUserDTO;
import com.volkov.consumer.service.DemoUserService;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(RabbitMQConsumer.class.getName());
    private final DemoUserService demoUserService;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    public RabbitMQConsumer(DemoUserService demoUserService) {
        this.demoUserService = demoUserService;
    }

    @Override
    public void onMessage(Message message) {
        try {
            LOGGER.info("received from mq, message = " + message);
            DemoUserDTO demoUserDTO = jsonMapper
                    .readValue(message.getBody(), DemoUserDTO.class);
            demoUserService.saveDemoUser(demoUserDTO).subscribe();
            LOGGER.info("DemoUser has been received and successfully parsed:" + demoUserDTO);
        } catch (IOException exception) {
            LOGGER.warning(exception.getMessage());
        }
    }
}
