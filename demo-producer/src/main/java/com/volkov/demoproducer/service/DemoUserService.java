package com.volkov.demoproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import java.util.List;

public interface DemoUserService {

    DemoUserDTO saveDemoUser(DemoUserDTO demoUser) throws JsonProcessingException;

    List<DemoUserDTO> getDemoUsers();

    boolean deleteDemoUserByUsername(String username);
}
