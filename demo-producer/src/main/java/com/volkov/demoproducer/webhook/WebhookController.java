package com.volkov.demoproducer.webhook;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("webhook")
public class WebhookController {
    private static final Logger LOGGER = getLogger(WebhookController.class);

    @GetMapping("get/")
    public ResponseEntity<String> getWebhook(@RequestParam("metaData") String metaData) {
        LOGGER.info("Echo meta-data back once received");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("post/")
    public ResponseEntity<String> postWebhook(@RequestBody String metaDataBody) {
        LOGGER.info("Echo meta-data body back once received");
        return new ResponseEntity<>(metaDataBody, HttpStatus.OK);
    }
}
