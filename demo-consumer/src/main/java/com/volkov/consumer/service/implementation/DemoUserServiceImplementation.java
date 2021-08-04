package com.volkov.consumer.service.implementation;

import com.volkov.consumer.entity.DemoUser;
import com.volkov.consumer.entity.dto.DemoUserDTO;
import com.volkov.consumer.exception.custom.DemoUserNotFoundException;
import com.volkov.consumer.repository.DemoUserRepository;
import com.volkov.consumer.service.DemoUserService;
import java.time.LocalDateTime;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DemoUserServiceImplementation implements DemoUserService {

    private final DemoUserRepository demoUserRepository;
    private final MapperFacade mapperFacade;

    public DemoUserServiceImplementation(DemoUserRepository demoUserRepository, MapperFacade mapperFacade) {
        this.demoUserRepository = demoUserRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public Mono<DemoUser> saveDemoUser(DemoUserDTO demoUserDTO) {
        DemoUser demoUserToSave = mapperFacade.map(demoUserDTO, DemoUser.class);
        demoUserToSave.setCreationDate(LocalDateTime.now());
        return demoUserRepository.save(demoUserToSave).log("DemoUser successfully saved");
    }

    @Override
    public Flux<DemoUserDTO> getDemoUsers() {
        Flux<DemoUser> demoUserCollection = demoUserRepository.findAll();
        return demoUserCollection.map(demoUser -> mapperFacade.map(demoUser, DemoUserDTO.class));
    }

    @Override
    public Mono<Boolean> deleteDemoUserByUsername(String username) {
        return demoUserRepository.deleteByUsername(username).flatMap(longResult -> {
            if (longResult.longValue() > 0) {
                return Mono.just(true);
            }
            Mono.error(DemoUserNotFoundException::new);
            return Mono.just(false);
        });
    }
}
