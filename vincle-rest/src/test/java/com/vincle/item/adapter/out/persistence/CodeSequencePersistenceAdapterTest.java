package com.vincle.item.adapter.out.persistence;

import com.vincle.item.adapter.out.persistence.entity.CodeSequenceEntity;
import com.vincle.item.adapter.out.persistence.repository.SpringCodeSequenceRepository;
import com.vincle.item.app.exception.ResourceNotFoundException;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.mock.ItemMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeSequencePersistenceAdapterTest {

    private final static CodeSequenceEntity CODE_SEQUENCE_ENTITY = CodeSequenceEntity.builder()
                                                                                     .code(1)
                                                                                     .id(1)
                                                                                     .name(ItemTypeEnum.DRINK)
                                                                                     .build();

    @Mock
    private SpringCodeSequenceRepository springCodeSequenceRepository;

    @InjectMocks
    private CodeSequencePersistenceAdapter codeSequencePersistenceAdapter;

    @Test
    void getCodeTest() {
        when(springCodeSequenceRepository.findByName(ItemMock.ITEM_TYPE)).thenReturn(Mono.just(CODE_SEQUENCE_ENTITY));
        when(springCodeSequenceRepository.save(CODE_SEQUENCE_ENTITY)).thenReturn(Mono.just(CODE_SEQUENCE_ENTITY));

        StepVerifier
                .create(codeSequencePersistenceAdapter.getCode(ItemMock.ITEM_TYPE))
                .consumeNextWith(code -> {
                    assertEquals(code, "DRINK-1");
                })
                .verifyComplete();
        verify(springCodeSequenceRepository).findByName(ItemMock.ITEM_TYPE);
        verify(springCodeSequenceRepository).save(CODE_SEQUENCE_ENTITY);
    }

    @Test
    void getCodeNotFoundTest() {
        when(springCodeSequenceRepository.findByName(ItemMock.ITEM_TYPE)).thenReturn(Mono.empty());

        StepVerifier
                .create(codeSequencePersistenceAdapter.getCode(ItemMock.ITEM_TYPE))
                .expectErrorMatches(throwable -> throwable instanceof ResourceNotFoundException)
                .verify();
        verify(springCodeSequenceRepository).findByName(ItemMock.ITEM_TYPE);

    }
}