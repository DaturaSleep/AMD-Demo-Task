package com.volkov.demoproducer.webhook;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class WebHookTest {

    @Autowired
    WebhookController webhookController;

    @ClassRule
    public static HoverflyRule postHoverflyRule = HoverflyRule.inSimulationMode(dsl(
            service("localhost:8080")
                    .post("/webhook/post")
                    .body("\"{ \"test\": \"test\" }")
                    .willReturn(success("test test", "text/plain"))
    ));

    @ClassRule
    public static HoverflyRule getHoverflyRule = HoverflyRule.inSimulationMode(dsl(
            service("localhost:8080")
                    .get("/webhook/get")
                    .queryParam("test")
                    .willReturn(success())
    ));

    @Test
    public void makeCallToPostWebhook() {
        String data = "\"{ \"test\": \"test\" }";
        assertEquals(HttpStatus.OK, webhookController.postWebhook(data).getStatusCode());
        assertEquals(data, webhookController.postWebhook(data).getBody());
    }

    @Test
    public void makeCallToGetWebhook() {
        String data = "test";
        assertEquals(HttpStatus.OK, webhookController.getWebhook(data).getStatusCode());
        assertNull(webhookController.getWebhook(data).getBody());
    }
}
