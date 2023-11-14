package com.vincle.item.app.port.in;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchItemPort {

    Mono<ItemDTO> searchItemByCode(final String code);

    Flux<ItemDTO> findItems(final String code,final ItemTypeEnum itemType,final StateEnum state);
}
