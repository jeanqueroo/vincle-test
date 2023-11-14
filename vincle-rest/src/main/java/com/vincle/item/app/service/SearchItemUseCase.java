package com.vincle.item.app.service;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.app.exception.ResourceNotFoundException;
import com.vincle.item.app.port.in.SearchItemPort;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.domain.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SearchItemUseCase implements SearchItemPort {

    private final ItemPort itemPort;

    private final ItemMapper itemMapper;

    public SearchItemUseCase(ItemPort itemPort, ItemMapper itemMapper) {
        this.itemPort = itemPort;
        this.itemMapper = itemMapper;
    }

    @Override
    public Mono<ItemDTO> searchItemByCode(final String code) {
        return itemPort.findItemByCode(code)
                       .switchIfEmpty(Mono.error(new ResourceNotFoundException("Item doesnt exit.")))
                       .map(itemMapper::sourceToDestination);
    }

    @Override
    public Flux<ItemDTO> findItems(final String code,final ItemTypeEnum itemType,final StateEnum state) {
        return itemPort.findItems(code,itemType,state)
                       .map(itemMapper::sourceToDestination);
    }
}
