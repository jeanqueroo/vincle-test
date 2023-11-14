package com.vincle.item.app.service;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.app.exception.ResourceExistException;
import com.vincle.item.app.port.in.SaveItemPort;
import com.vincle.item.app.port.out.CodeSequencePort;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.app.port.out.ClientPort;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.config.IAuthentication;
import com.vincle.item.domain.Item;
import com.vincle.item.domain.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SaveItemUseCase implements SaveItemPort {

    private final ItemPort itemPort;

    private final CodeSequencePort codeSequencePort;

    private final ClientPort clientPort;

    private final IAuthentication authentication;

    private final ItemMapper itemMapper;

    public SaveItemUseCase(ItemPort itemPort, CodeSequencePort codeSequencePort, ClientPort clientPort, IAuthentication authentication, ItemMapper itemMapper) {
        this.itemPort = itemPort;
        this.codeSequencePort = codeSequencePort;
        this.clientPort = clientPort;
        this.authentication = authentication;
        this.itemMapper = itemMapper;
    }

    @Override
    public Mono<ItemDTO> saveItem(final ItemDTO itemDTO) {
         return codeSequencePort.getCode(itemDTO.getItemType()).flatMap(code ->
             clientPort.getUserID(authentication.getUserName())
                       .switchIfEmpty(clientPort.saveUser(authentication.getUserName()))
                       .flatMap(cliendId -> itemPort.saveItem(Item.builder().itemType(itemDTO.getItemType())
                                                                  .state(StateEnum.CREATED)
                                                                  .weight(itemDTO.getWeight())
                                                                  .code(code)
                                                                  .clientId(cliendId)
                                                                  .name(itemDTO.getName())
                                                                  .needFridge(itemDTO.getNeedFridge())
                                                                  .containerType(itemDTO.getContainerType())
                                                                  .build()).map(itemMapper::sourceToDestination)));


    }
}
