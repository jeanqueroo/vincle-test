package com.vincle.item.adapter.out.persistence.repository;

import com.vincle.item.adapter.out.persistence.entity.ItemEntity;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface SpringItemRepository extends ReactiveCrudRepository<ItemEntity, Integer> {

    @Query("SELECT * FROM ITEMS i "
            + "WHERE i.code = :code "
    )
    Mono<ItemEntity> findByCode(@Param("code")String code);

    @Query("SELECT * FROM ITEMS i "
            + "WHERE (:code IS NULL OR i.code LIKE CONCAT('%',:code,'%')) AND "
            + "(:itemType IS NULL OR i.item_type = :itemType) AND "
            + "(:state IS NULL OR  i.state = :state)  order by i.create_data"
    )
    Flux<ItemEntity> findByCodeAndItemTypeAndStateAndDate(@Param("code") String code,@Param("itemType") ItemTypeEnum itemType,
            @Param("state") StateEnum state);

    @Query("SELECT * FROM ITEMS i "
            + "WHERE i.name = :name AND "
            + "i.container_type = :containerType"
    )
    Mono<ItemEntity> findItemByNameAndContainerType(@Param("name") String name,@Param("containerType") ContainerTypeEnum containerType);
}
