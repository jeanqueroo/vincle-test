package com.vincle.item.app.service;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.app.port.in.DeleteItemPort;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.domain.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteItemUseCase implements DeleteItemPort {

    private final ItemPort itemPort;

    private final ItemMapper itemMapper;

    public DeleteItemUseCase(ItemPort itemPort, ItemMapper itemMapper) {
        this.itemPort = itemPort;
        this.itemMapper = itemMapper;
    }

    @Override
    public Mono<ItemDTO> deleteItemByCode(final String code) {
        return itemPort.deleteItem(code)
                       .map(itemMapper::sourceToDestination);
    }
}
