package com.volkov.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.consumer.entity.DemoUser;
import com.volkov.consumer.entity.dto.DemoUserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DemoUserService {
    Mono<DemoUser> saveDemoUser(DemoUserDTO demoUser) throws JsonProcessingException;

    Flux<DemoUserDTO> getDemoUsers();

    Mono<Boolean> deleteDemoUserByUsername(String username);
}
