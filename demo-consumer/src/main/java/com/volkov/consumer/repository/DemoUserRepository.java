package com.volkov.consumer.repository;

import com.volkov.consumer.entity.DemoUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DemoUserRepository extends ReactiveCrudRepository<DemoUser, String> {

    Mono<Long> deleteByUsername(final String username);

}
