package com.volkov.demoproducer.repository;

import com.volkov.demoproducer.entity.DemoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoUserRepository extends MongoRepository<DemoUser, String> {
    Long deleteByUsername(final String username);

    DemoUser findByUsername(final String username);
}
