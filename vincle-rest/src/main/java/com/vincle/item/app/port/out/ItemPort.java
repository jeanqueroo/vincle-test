package com.vincle.item.app.port.out;

import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.domain.Item;
import com.vincle.item.domain.ItemUpdate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemPort {

    Mono<Item> findItemByCode(final String code);

    Flux<Item> findItems(final String code, final ItemTypeEnum itemType, final StateEnum state);

    Mono<Item> saveItem(final Item Item);

    Mono<Item> updateItem(final ItemUpdate Item,final String code);

    Mono<Item> deleteItem(final String code);

    Mono<Item> findItemByNameAndContainerType(final String name, ContainerTypeEnum containerType);

}
