package com.vincle.item.adapter.out.persistence.repository;

import com.vincle.item.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SpringClientRepository extends ReactiveCrudRepository<ClientEntity, Integer> {

    Mono<ClientEntity> findByUsername(@Param("username")String username);
}
