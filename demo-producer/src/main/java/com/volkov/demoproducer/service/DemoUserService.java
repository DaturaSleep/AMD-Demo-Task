package com.volkov.demoproducer.service;

import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import java.util.List;

public interface DemoUserService {

    DemoUserDTO saveDemoUser(DemoUserDTO demoUser);

    List<DemoUserDTO> getDemoUsers();

    boolean deleteDemoUserByUsername(String username);
}
