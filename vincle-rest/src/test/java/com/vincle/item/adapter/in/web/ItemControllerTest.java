package com.vincle.item.adapter.in.web;

import com.vincle.VincleApplication;
import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.adapter.in.web.model.ItemUpdateDTO;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.app.util.UpdateStateEnum;
import com.vincle.item.mock.ItemMock;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@SpringBootTest(classes = VincleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration")
class ItemControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void saveItem() {

        webTestClient.post().uri("/api/v1/item")
                     .body(Mono.just(ItemDTO.builder()
                                            .name("name")
                                            .needFridge(false)
                                            .weight(100).itemType(ItemTypeEnum.DRINK)
                                            .containerType(ContainerTypeEnum.GLASS)
                                            .build()), ItemDTO.class)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(9)
                     .jsonPath("$.code").isEqualTo("DRINK-3")
                     .jsonPath("$.state").isEqualTo(ItemMock.STATE.name());
    }

    @Test
    void saveItemFood() {

        webTestClient.post().uri("/api/v1/item")
                     .body(Mono.just(ItemDTO.builder()
                                            .name("name")
                                            .needFridge(false)
                                            .weight(100).itemType(ItemTypeEnum.FOOD)
                                            .containerType(ContainerTypeEnum.GLASS)
                                            .build()), ItemDTO.class)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(9)
                     .jsonPath("$.code").isEqualTo("FOOD-2")
                     .jsonPath("$.state").isEqualTo(ItemMock.STATE.name());
    }

    @Test
    void saveItemSauce() {

        webTestClient.post().uri("/api/v1/item")
                     .body(Mono.just(ItemDTO.builder()
                                            .name("name")
                                            .needFridge(false)
                                            .weight(100).itemType(ItemTypeEnum.SAUCE)
                                            .containerType(ContainerTypeEnum.PLASTIC)
                                            .build()), ItemDTO.class)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(9)
                     .jsonPath("$.code").isEqualTo("SAUCE-1")
                     .jsonPath("$.state").isEqualTo(ItemMock.STATE.name());
    }

    @Test
    void saveItemBadRequestWithOutName() {

        webTestClient.post().uri("/api/v1/item")
                     .body(Mono.just(ItemDTO.builder()
                                            .needFridge(false)
                                            .weight(100).itemType(ItemTypeEnum.DRINK)
                                            .containerType(ContainerTypeEnum.GLASS)
                                            .build()), ItemDTO.class)
                     .exchange()
                     .expectStatus().isBadRequest()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$[0]").isEqualTo("name cant be null");
    }

    @Test
    void saveItemBadRequestWithOutItemType() {

        webTestClient.post().uri("/api/v1/item")
                     .body(Mono.just(ItemDTO.builder()
                                            .needFridge(false)
                                            .weight(100).name("name")
                                            .containerType(ContainerTypeEnum.GLASS)
                                            .build()), ItemDTO.class)
                     .exchange()
                     .expectStatus().isBadRequest()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$[0]").isEqualTo("ItemType cant be null");
    }

    @Test
    @Order(2)
    void searchItemByCode() {
        webTestClient.get().uri("/api/v1/item?ItemType=DRINK&code=DRINK-1")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(1)
                     .jsonPath("$[0].code").isEqualTo("DRINK-1")
                     .jsonPath("$[0].state").isEqualTo(ItemMock.STATE.name());
    }

    @Test
    void testSearchItemByCode() {
        webTestClient.get().uri("/api/v1/item/DRINK-1")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(9)
                     .jsonPath("$.code").isEqualTo("DRINK-1")
                     .jsonPath("$.state").isEqualTo(ItemMock.STATE.name());
    }

    @Test
    void updateItem() {
        webTestClient.put().uri("/api/v1/item/DRINK-1")
                     .body(Mono.just(ItemUpdateDTO.builder()
                                                  .name("name")
                                                  .needFridge(false)
                                                  .weight(100).state(UpdateStateEnum.WAITING)
                                                  .containerType(ContainerTypeEnum.GLASS)
                                                  .build()), ItemDTO.class)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(9)
                     .jsonPath("$.code").isEqualTo("DRINK-1")
                     .jsonPath("$.state").isEqualTo(StateEnum.WAITING.name());
    }

    @Test
    void deleteItem() {
        webTestClient.delete().uri("/api/v1/item/DRINK-1")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(9)
                     .jsonPath("$.code").isEqualTo("DRINK-1")
                     .jsonPath("$.state").isEqualTo(StateEnum.DELETED.name());
    }
}