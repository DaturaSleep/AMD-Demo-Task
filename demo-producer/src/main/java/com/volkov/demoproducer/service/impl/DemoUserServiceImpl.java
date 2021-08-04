package com.volkov.demoproducer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkov.demoproducer.entity.DemoUser;
import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import com.volkov.demoproducer.exception.custom.DemoUserDuplicateException;
import com.volkov.demoproducer.exception.custom.DemoUserNotFoundException;
import com.volkov.demoproducer.repository.DemoUserRepository;
import com.volkov.demoproducer.service.DemoUserService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@Service
public class DemoUserServiceImpl implements DemoUserService {

    private final DemoUserRepository demoUserRepository;
    private final MapperFacade mapperFacade;
    private final RabbitMQService rabbitMQService;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    public DemoUserServiceImpl(final DemoUserRepository demoUserRepository, final MapperFacade mapperFacade, final RabbitMQService rabbitMQService) {
        this.demoUserRepository = demoUserRepository;
        this.mapperFacade = mapperFacade;
        this.rabbitMQService = rabbitMQService;
    }

    @Override
    public DemoUserDTO saveDemoUser(final DemoUserDTO demoUser) {
        DemoUser demoUserToSave = mapperFacade.map(demoUser, DemoUser.class);
        demoUserToSave.setCreationDate(LocalDateTime.now());
        if (Objects.nonNull(demoUserRepository.findByUsername(demoUserToSave.getUsername()))) {
            throw new DemoUserDuplicateException();
        }
        DemoUserDTO returnedDemoUserDTO = mapperFacade.map(demoUserRepository.insert(demoUserToSave), DemoUserDTO.class);
        rabbitMQService.send(returnedDemoUserDTO);
        return returnedDemoUserDTO;
    }

    @Override
    public List<DemoUserDTO> getDemoUsers() {
        return Collections.unmodifiableList(demoUserRepository.findAll().stream().map(demoUser ->
                mapperFacade.map(demoUser, DemoUserDTO.class)
        ).collect(Collectors.toList()));
    }

    @Override
    public boolean deleteDemoUserByUsername(final String username) {
        if (demoUserRepository.deleteByUsername(username) > 0) {
            return true;
        } else {
            throw new DemoUserNotFoundException();
        }

    }
}
