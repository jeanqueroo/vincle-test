package com.vincle.item.app.port.in;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import reactor.core.publisher.Mono;

public interface SaveItemPort {

    Mono<ItemDTO> saveItem(final ItemDTO itemDTO);
}
