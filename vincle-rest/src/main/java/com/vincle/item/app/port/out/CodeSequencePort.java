package com.vincle.item.app.port.out;

import com.vincle.item.app.util.ItemTypeEnum;
import reactor.core.publisher.Mono;

public interface CodeSequencePort {

    Mono<String> getCode(final ItemTypeEnum tabla);
}
