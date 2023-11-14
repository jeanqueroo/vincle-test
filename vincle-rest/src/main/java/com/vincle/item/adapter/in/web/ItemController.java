package com.vincle.item.adapter.in.web;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.adapter.in.web.model.ItemUpdateDTO;
import com.vincle.item.adapter.in.web.model.SearchItemDTO;
import com.vincle.item.app.port.in.DeleteItemPort;
import com.vincle.item.app.port.in.SaveItemPort;
import com.vincle.item.app.port.in.SearchItemPort;
import com.vincle.item.app.port.in.UpdateItemPort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {

    private final SearchItemPort searchItemPort;

    private final SaveItemPort saveItemPort;

    private final UpdateItemPort updateItemPort;

    private final DeleteItemPort deleteItemPort;

    public ItemController(SearchItemPort searchItemPort, SaveItemPort saveItemPort, UpdateItemPort updateItemPort, DeleteItemPort deleteItemPort) {
        this.searchItemPort = searchItemPort;
        this.saveItemPort = saveItemPort;
        this.updateItemPort = updateItemPort;
        this.deleteItemPort = deleteItemPort;
    }

    @CrossOrigin
    @PostMapping
    public Mono<ItemDTO> saveItem(@Valid @RequestBody ItemDTO itemDTO) {
            return saveItemPort.saveItem(itemDTO);
    }

    @CrossOrigin
    @GetMapping("/{code}")
    public Mono<ItemDTO> searchItemByCode(@PathVariable("code") String code) {
        return searchItemPort.searchItemByCode(code);
    }

    @CrossOrigin
    @GetMapping
    public Flux<ItemDTO> searchItemByCode(@Valid SearchItemDTO searchItemDTO) {
        return searchItemPort.findItems(searchItemDTO.getCode(),searchItemDTO.getItemType(),
                searchItemDTO.getState());
    }



    @CrossOrigin
    @PutMapping("/{code}")
    public Mono<ItemDTO> updateItem(@Valid @RequestBody ItemUpdateDTO itemUpdateDTO, @PathVariable("code") String code) {
        return updateItemPort.updateItem(itemUpdateDTO,code);
    }

    @CrossOrigin
    @DeleteMapping("/{code}")
    public Mono<ItemDTO> deleteItem(@PathVariable("code") String code) {
        return deleteItemPort.deleteItemByCode(code);
    }
}
