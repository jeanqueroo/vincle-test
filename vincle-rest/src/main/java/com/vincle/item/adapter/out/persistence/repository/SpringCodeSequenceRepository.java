package com.vincle.item.adapter.out.persistence.repository;

import com.vincle.item.adapter.out.persistence.entity.CodeSequenceEntity;
import com.vincle.item.adapter.out.persistence.entity.ItemEntity;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.domain.Item;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SpringCodeSequenceRepository extends ReactiveCrudRepository<CodeSequenceEntity, Integer> {


    Mono<CodeSequenceEntity> findByName(@Param("name") ItemTypeEnum name);
}
