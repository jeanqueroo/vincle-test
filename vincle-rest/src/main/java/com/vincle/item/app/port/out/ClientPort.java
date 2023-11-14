package com.vincle.item.app.port.out;

import reactor.core.publisher.Mono;

public interface ClientPort {

    Mono<Integer> getUserID(final String username);

    Mono<Integer> saveUser(final String username);
}
