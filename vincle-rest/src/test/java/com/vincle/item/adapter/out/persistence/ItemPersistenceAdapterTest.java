package com.vincle.item.adapter.out.persistence;

import com.vincle.item.adapter.out.persistence.repository.SpringItemRepository;
import com.vincle.item.app.exception.ResourceNotFoundException;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.domain.mapper.ItemMapper;
import com.vincle.item.domain.mapper.ItemMapperImpl;
import com.vincle.item.mock.ItemMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemPersistenceAdapterTest {

    @Mock
    private SpringItemRepository springItemRepository;

    @Spy
    private ItemMapper itemMapper = new ItemMapperImpl();

    @InjectMocks
    private ItemPersistenceAdapter itemPersistenceAdapter;


    @Test
    void findItemByCode() {
        when(springItemRepository.findByCode(ItemMock.CODE)).thenReturn(Mono.just(ItemMock.ITEM_ENTITY));
        StepVerifier
                .create(itemPersistenceAdapter.findItemByCode(ItemMock.CODE))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE);
                    assertEquals(item.getName(), ItemMock.NAME);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                })
                .verifyComplete();
        verify(springItemRepository).findByCode(ItemMock.CODE);
    }

    @Test
    void findItems() {
        when(springItemRepository.findByCodeAndItemTypeAndStateAndDate(ItemMock.CODE,ItemTypeEnum.DRINK, StateEnum.CREATED)).thenReturn(Flux.just(ItemMock.ITEM_ENTITY,ItemMock.ITEM_ENTITY2));
        StepVerifier
                .create(itemPersistenceAdapter.findItems(ItemMock.CODE,ItemTypeEnum.DRINK, StateEnum.CREATED))
                .expectNext(ItemMock.ITEM,ItemMock.ITEM2)
                .verifyComplete();
        verify(springItemRepository).findByCodeAndItemTypeAndStateAndDate(ItemMock.CODE,ItemTypeEnum.DRINK, StateEnum.CREATED);
    }

    @Test
    void saveItem() {
        when(springItemRepository.save(any())).thenReturn(Mono.just(ItemMock.ITEM_ENTITY));
        StepVerifier
                .create(itemPersistenceAdapter.saveItem(ItemMock.ITEM))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE);
                    assertEquals(item.getName(), ItemMock.NAME);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                })
                .verifyComplete();
        verify(springItemRepository).save(any());
    }

    @Test
    void updateItem() {
        when(springItemRepository.findByCode(ItemMock.CODE)).thenReturn(Mono.just(ItemMock.ITEM_ENTITY));
        when(springItemRepository.save(any())).thenReturn(Mono.just(ItemMock.ITEM_ENTITY));
        StepVerifier
                .create(itemPersistenceAdapter.updateItem(ItemMock.ITEM_UPDATE, ItemMock.CODE))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE);
                    assertEquals(item.getName(), ItemMock.NAME);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                })
                .verifyComplete();
        verify(springItemRepository).save(any());
        verify(springItemRepository).findByCode(ItemMock.CODE);

    }

    @Test
    void deleteItem() {
        when(springItemRepository.findByCode(ItemMock.CODE)).thenReturn(Mono.just(ItemMock.ITEM_ENTITY));
        when(springItemRepository.save(any())).thenReturn(Mono.just(ItemMock.ITEM_ENTITY_DELETED));
        StepVerifier
                .create(itemPersistenceAdapter.deleteItem(ItemMock.CODE))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE2);
                    assertEquals(item.getName(), ItemMock.NAME2);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                    assertEquals(item.getState(), StateEnum.DELETED);
                })
                .verifyComplete();
        verify(springItemRepository).save(any());
        verify(springItemRepository).findByCode(ItemMock.CODE);
    }

    @Test
    void findItemByNameAndContainerType() {
        when(springItemRepository.findItemByNameAndContainerType(ItemMock.NAME,ItemMock.ITEM.getContainerType())).thenReturn(Mono.just(ItemMock.ITEM_ENTITY));
        StepVerifier
                .create(itemPersistenceAdapter.findItemByNameAndContainerType(ItemMock.NAME,ItemMock.ITEM.getContainerType()))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE);
                    assertEquals(item.getName(), ItemMock.NAME);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                })
                .verifyComplete();
        verify(springItemRepository).findItemByNameAndContainerType(ItemMock.NAME,ItemMock.ITEM.getContainerType());
    }
}