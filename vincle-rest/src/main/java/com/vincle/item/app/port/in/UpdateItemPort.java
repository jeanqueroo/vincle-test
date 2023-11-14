package com.vincle.item.app.port.in;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.adapter.in.web.model.ItemUpdateDTO;
import reactor.core.publisher.Mono;

public interface UpdateItemPort {

    Mono<ItemDTO> updateItem(final ItemUpdateDTO itemUpdateDTO,final String code);
}
