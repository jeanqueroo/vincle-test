package com.vincle.item.app.service;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.adapter.in.web.model.ItemUpdateDTO;
import com.vincle.item.app.port.in.UpdateItemPort;
import com.vincle.item.app.port.out.ClientPort;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.config.IAuthentication;
import com.vincle.item.domain.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateItemUseCase implements UpdateItemPort {

    private final ItemPort itemPort;

    private final ClientPort clientPort;

    private final IAuthentication authentication;

    private final ItemMapper itemMapper;

    public UpdateItemUseCase(ItemPort itemPort, ClientPort clientPort, IAuthentication authentication, ItemMapper itemMapper) {
        this.itemPort = itemPort;
        this.clientPort = clientPort;
        this.authentication = authentication;
        this.itemMapper = itemMapper;
    }

    @Override
    public Mono<ItemDTO> updateItem(ItemUpdateDTO itemDTO, String code) {
        return clientPort.getUserID(authentication.getUserName())
                         .switchIfEmpty(clientPort.saveUser(authentication.getUserName()))
                         .flatMap(cliendId -> {
                             itemDTO.setModifiedBy(cliendId);
                             return itemPort.updateItem(itemMapper.itemToUpdate(itemDTO), code)
                                            .map(itemMapper::sourceToDestination);
                         });
    }

}
