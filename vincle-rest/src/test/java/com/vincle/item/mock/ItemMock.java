package com.vincle.item.mock;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.adapter.in.web.model.ItemUpdateDTO;
import com.vincle.item.adapter.out.persistence.entity.ItemEntity;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.app.util.UpdateStateEnum;
import com.vincle.item.domain.Item;
import com.vincle.item.domain.ItemUpdate;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public class ItemMock {

    public final static String NAME = "name";
    public final static String CODE = "DRINK-1";

    public final static ItemTypeEnum ITEM_TYPE =ItemTypeEnum.DRINK;
    public final static StateEnum STATE = StateEnum.CREATED;
    public final static Instant START_DATA = Mockito.mock(Instant.class);
    public final static Instant END_DATA = Mockito.mock(Instant.class);

    public final static String NAME2 = "name2";
    public final static String CODE2 = "DRINK-2";

    public final static ItemTypeEnum ITEM_TYPE2 =ItemTypeEnum.FOOD;
    public final static StateEnum STATE2 = StateEnum.WAITING;
    public final static Instant START_DATA2 = Mockito.mock(Instant.class);
    public final static Instant END_DATA2 = Mockito.mock(Instant.class);

    public final static ItemUpdate ITEM_UPDATE = ItemUpdate.builder().name(NAME)
                                                           .containerType(ContainerTypeEnum.GLASS)
                                                           .weight(100)
                                                           .state(UpdateStateEnum.CREATED)
                                                           .needFridge(true)
                                                           .build();
    public final static ItemUpdateDTO ITEM_UPDATE_DTO = ItemUpdateDTO.builder().name(NAME)
                                                           .containerType(ContainerTypeEnum.GLASS)
                                                           .weight(100)
                                                           .state(UpdateStateEnum.CREATED)
                                                           .needFridge(true)
                                                           .build();

    public final static Item ITEM = Item.builder()
                                           .name(NAME)
                                           .itemType(ITEM_TYPE)
                                           .containerType(ContainerTypeEnum.GLASS)
                                           .weight(100)
                                           .state(STATE)
                                           .needFridge(true)
                                           .createData(START_DATA)
                                           .code(CODE)
                                           .clientId(1)
                                           .build();
    public final static Item ITEM2 = Item.builder()
                                            .name(NAME2)
                                            .itemType(ITEM_TYPE2)
                                            .containerType(ContainerTypeEnum.GLASS)
                                            .weight(100)
                                            .state(STATE2)
                                            .needFridge(true)
                                            .createData(START_DATA2)
                                            .code(CODE2)
                                            .clientId(1)
                                            .build();
    public final static ItemDTO ITEM_DTO = ItemDTO.builder()
                                        .name(NAME)
                                        .itemType(ITEM_TYPE)
                                        .containerType(ContainerTypeEnum.GLASS)
                                        .weight(100)
                                        .state(STATE)
                                        .needFridge(true)
                                        .createData(START_DATA)
                                        .code(CODE)
                                        .clientId(1)
                                        .build();
    public final static ItemDTO ITEM_DTO2 = ItemDTO.builder()
                                         .name(NAME2)
                                         .itemType(ITEM_TYPE2)
                                         .containerType(ContainerTypeEnum.GLASS)
                                         .weight(100)
                                         .state(STATE2)
                                         .needFridge(true)
                                         .createData(START_DATA2)
                                         .code(CODE2)
                                         .clientId(1)
                                         .build();

    public final static ItemEntity ITEM_ENTITY = ItemEntity.builder().id(1).name(NAME)
                                                           .itemType(ITEM_TYPE)
                                                           .containerType(ContainerTypeEnum.GLASS)
                                                           .weight(100)
                                                           .state(STATE)
                                                           .needFridge(true)
                                                           .createData(START_DATA)
                                                           .code(CODE)
                                                           .clientId(1)
                                                           .build();
    public final static ItemEntity ITEM_ENTITY2 = ItemEntity.builder().id(2).name(NAME2)
                                                            .itemType(ITEM_TYPE2)
                                                            .containerType(ContainerTypeEnum.GLASS)
                                                            .weight(100)
                                                            .state(STATE2)
                                                            .needFridge(true)
                                                            .createData(START_DATA2)
                                                            .code(CODE2)
                                                            .clientId(1)
                                                           .build();
    public final static ItemEntity ITEM_ENTITY_DELETED = ItemEntity.builder().id(2).name(NAME2)
                                                            .itemType(ITEM_TYPE)
                                                            .containerType(ContainerTypeEnum.GLASS)
                                                            .weight(100)
                                                            .state(StateEnum.DELETED)
                                                            .needFridge(true)
                                                            .createData(START_DATA2)
                                                            .code(CODE2)
                                                            .clientId(1)
                                                            .build();

    public static Mono<ItemDTO> getItemDTO() {
        return Mono.just(ItemDTO.builder()
                                .name(NAME)
                                .itemType(ITEM_TYPE)
                                .containerType(ContainerTypeEnum.GLASS)
                                .weight(100)
                                .state(STATE)
                                .needFridge(true)
                                .createData(START_DATA)
                                .code(CODE)
                                .clientId(1)
                                .build());
    }

    public static Mono<Item> getItem() {
        return Mono.just(ITEM);
    }

    public static Flux<Item> getItems() {
        return Flux.just(ITEM,ITEM2);
    }


}
