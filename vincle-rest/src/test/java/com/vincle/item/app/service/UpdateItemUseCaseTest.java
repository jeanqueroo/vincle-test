package com.vincle.item.app.service;

import com.vincle.item.app.port.out.ClientPort;
import com.vincle.item.app.port.out.ItemPort;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.config.IAuthentication;
import com.vincle.item.domain.ItemUpdate;
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

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateItemUseCaseTest {

    @Mock
    private ItemPort itemPort;

    @Mock
    private ClientPort clientPort;

    @Mock
    private IAuthentication authentication;

    @Spy
    private ItemMapper itemMapper = new ItemMapperImpl();

    @InjectMocks
    private UpdateItemUseCase updateItemUseCase;

    @Test
    void updateItem() {

        String username = "jquero";
        when(authentication.getUserName()).thenReturn(username);
        when(clientPort.getUserID(eq(username))).thenReturn(Mono.just(1));
        when(clientPort.saveUser(eq(username))).thenReturn(Mono.just(1));
        when(itemPort.updateItem(any(ItemUpdate.class), eq(ItemMock.CODE))).thenReturn(ItemMock.getItem());
        StepVerifier
                .create(updateItemUseCase.updateItem(ItemMock.ITEM_UPDATE_DTO, ItemMock.CODE))
                .consumeNextWith(item -> {
                    assertEquals(item.getCode(), ItemMock.CODE);
                    assertEquals(item.getName(), ItemMock.NAME);
                    assertEquals(item.getItemType(), ItemTypeEnum.DRINK);
                    assertEquals(item.getContainerType(), ContainerTypeEnum.GLASS);
                    assertEquals(item.getNeedFridge(), true);
                })
                .verifyComplete();
        verify(authentication,times(2)).getUserName();
        verify(clientPort).getUserID(username);
        verify(itemPort).updateItem(any(ItemUpdate.class), eq(ItemMock.CODE));
    }
}