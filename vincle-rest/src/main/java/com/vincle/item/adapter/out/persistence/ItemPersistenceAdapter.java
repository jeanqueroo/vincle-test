package com.vincle.item.adapter.out.persistence;

import com.vincle.item.adapter.out.persistence.repository.SpringItemRepository;
import com.vincle.item.app.exception.ResourceNotFoundException;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.domain.Item;
import com.vincle.item.domain.ItemUpdate;
import com.vincle.item.domain.mapper.ItemMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ItemPersistenceAdapter implements ItemPort {

    private final SpringItemRepository springItemRepository;

    private final ItemMapper itemMapper;

    public ItemPersistenceAdapter(SpringItemRepository springItemRepository, ItemMapper itemMapper) {
        this.springItemRepository = springItemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    @Transactional
    public Mono<Item> findItemByCode(String code) {
      return  springItemRepository.findByCode(code).map(itemMapper::entitySourceToDestination);

    }

    @Override
    @Transactional
    public Flux<Item> findItems(String code, ItemTypeEnum itemType, StateEnum state) {
        return springItemRepository.findByCodeAndItemTypeAndStateAndDate(code,itemType,state)
                                   .map(itemMapper::entitySourceToDestination);
    }

    @Override
    @Transactional
    public Mono<Item> saveItem(Item item) {
        return springItemRepository.save(itemMapper.entityDestinationToSource(item))
                                   .map(itemMapper::entitySourceToDestination);
    }

    @Override
    @Transactional
    public Mono<Item> updateItem(ItemUpdate item, String code) {
        return springItemRepository.findByCode(code)
                                   .switchIfEmpty(Mono.error(new ResourceNotFoundException("Item doesnt exit.")))
                                   .flatMap(itemEntity ->
                                           springItemRepository.save(itemMapper.entityToUpdate(item,itemEntity))
                                                               .map(itemMapper::entitySourceToDestination));
    }

    @Override
    @Transactional
    public Mono<Item> deleteItem(String code) {
        return springItemRepository.findByCode(code)
                                          .switchIfEmpty(Mono.error(new ResourceNotFoundException("Item doesnt exit.")))
                                          .flatMap(itemEntity ->{
                                              itemEntity.setState(StateEnum.DELETED);
                                                 return springItemRepository.save(itemEntity)
                                                                      .map(itemMapper::entitySourceToDestination);
                                          });
    }

    @Override
    @Transactional
    public Mono<Item> findItemByNameAndContainerType(String name, ContainerTypeEnum containerType) {
        return  springItemRepository.findItemByNameAndContainerType(name,containerType).map(itemMapper::entitySourceToDestination);
    }

}
