package com.vincle.item.adapter.out.persistence;

import com.vincle.item.adapter.out.persistence.entity.ClientEntity;
import com.vincle.item.adapter.out.persistence.repository.SpringClientRepository;
import com.vincle.item.app.port.out.ClientPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
public class ClientPersistenceAdapter implements ClientPort {

    private final SpringClientRepository springClientRepository;

    public ClientPersistenceAdapter(SpringClientRepository springClientRepository) {
        this.springClientRepository = springClientRepository;
    }

    @Override
    public Mono<Integer> getUserID(String username) {
        return springClientRepository.findByUsername(username).map(clientEntity -> clientEntity.getId());
    }

    @Override
    @Transactional
    public Mono<Integer> saveUser(String username) {
        return springClientRepository.save(ClientEntity.builder().username(username).build())
                                     .map(clientEntity -> clientEntity.getId());
    }
}
