package com.vincle.item.adapter.out.persistence;

import com.vincle.item.adapter.out.persistence.entity.ClientEntity;
import com.vincle.item.adapter.out.persistence.repository.SpringClientRepository;
import com.vincle.item.mock.ItemMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientPersistenceAdapterTest {

    private final static String USER_NAME = "username";

    private final static ClientEntity CLIENT_ENTITY = ClientEntity.builder().id(1).username(USER_NAME)
                                                                  .build();

    @Mock
    private SpringClientRepository springClientRepository;

    @InjectMocks
    private ClientPersistenceAdapter clientPersistenceAdapter;

    @Test
    void getUserID() {
        when(springClientRepository.findByUsername(USER_NAME)).thenReturn(Mono.just(CLIENT_ENTITY));

        StepVerifier
                .create(clientPersistenceAdapter.getUserID(USER_NAME))
                .consumeNextWith(code -> {
                    assertEquals(code, 1);
                })
                .verifyComplete();
        verify(springClientRepository).findByUsername(USER_NAME);
    }

    @Test
    void saveUser() {
        when(springClientRepository.save(any())).thenReturn(Mono.just(CLIENT_ENTITY));

        StepVerifier
                .create(clientPersistenceAdapter.saveUser(USER_NAME))
                .consumeNextWith(code -> {
                    assertEquals(code, 1);
                })
                .verifyComplete();
        verify(springClientRepository).save(any());
    }
}