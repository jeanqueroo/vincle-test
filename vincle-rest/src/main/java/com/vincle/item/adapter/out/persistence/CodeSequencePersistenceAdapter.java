package com.vincle.item.adapter.out.persistence;

import com.vincle.item.adapter.out.persistence.repository.SpringCodeSequenceRepository;
import com.vincle.item.app.exception.ResourceNotFoundException;
import com.vincle.item.app.port.out.CodeSequencePort;
import com.vincle.item.app.util.ItemTypeEnum;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
public class CodeSequencePersistenceAdapter implements CodeSequencePort {

    private final SpringCodeSequenceRepository springCodeSequenceRepository;

    public CodeSequencePersistenceAdapter(SpringCodeSequenceRepository springCodeSequenceRepository) {
        this.springCodeSequenceRepository = springCodeSequenceRepository;
    }

    @Override
    @Transactional
    public Mono<String> getCode(ItemTypeEnum itemType) {
        return springCodeSequenceRepository.findByName(itemType)
                                           .switchIfEmpty(Mono.error(new ResourceNotFoundException("Code doesnt exit.")))
                                           .map(codeSequence -> {
                                               String code = itemType.name() + "-" + codeSequence.getCode();

                                               // Actualizamos el número de secuencia del ámbito
                                               codeSequence.setCode(codeSequence.getCode() + 1);
                                               springCodeSequenceRepository.save(codeSequence).subscribe();
                                               return code;
                                           });
    }
}
