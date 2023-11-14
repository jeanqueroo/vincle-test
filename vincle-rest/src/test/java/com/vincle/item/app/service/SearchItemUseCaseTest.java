package com.vincle.item.app.service;

import com.vincle.item.app.exception.ResourceNotFoundException;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.domain.mapper.ItemMapper;
import com.vincle.item.domain.mapper.ItemMapperImpl;
import com.vincle.item.mock.ItemMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchItemUseCaseTest {

    @Mock
    private ItemPort itemPort;

    @Spy
    private ItemMapper itemMapper = new ItemMapperImpl();

    @InjectMocks
    private SearchItemUseCase searchItemUseCase;

    @Test
    void searchItemByCodeTest() {
        when(itemPort.findItemByCode(ItemMock.CODE)).thenReturn(ItemMock.getItem());

        StepVerifier
                .create(searchItemUseCase.searchItemByCode(ItemMock.CODE))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE);
                    assertEquals(item.getName(), ItemMock.NAME);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                })
                .verifyComplete();

        verify(itemPort).findItemByCode(ItemMock.CODE);
    }

    @Test
    void searchItemByCodeTestEmpty() {
        when(itemPort.findItemByCode(ItemMock.CODE)).thenReturn(Mono.empty());
        StepVerifier
                .create(searchItemUseCase.searchItemByCode(ItemMock.CODE))
                .expectErrorMatches(throwable -> throwable instanceof ResourceNotFoundException)
                .verify();
        verify(itemPort).findItemByCode(ItemMock.CODE);
    }

    @Test
    void findItems() {
        when(itemPort.findItems(ItemMock.CODE,ItemMock.ITEM_TYPE,ItemMock.STATE))
                .thenReturn(ItemMock.getItems());

        StepVerifier
                .create(searchItemUseCase.findItems(ItemMock.CODE,ItemMock.ITEM_TYPE,ItemMock.STATE))
                .expectNext(ItemMock.ITEM_DTO,ItemMock.ITEM_DTO2)
                .verifyComplete();

        verify(itemPort).findItems(ItemMock.CODE,ItemMock.ITEM_TYPE,ItemMock.STATE);
    }
}