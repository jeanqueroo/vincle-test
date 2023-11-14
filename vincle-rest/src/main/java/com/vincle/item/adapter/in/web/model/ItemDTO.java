package com.vincle.item.adapter.in.web.model;

import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    @NotNull(message = "ItemType cant be null")
    private ItemTypeEnum itemType;

    @NotNull(message = "needFridge  cant be null")
    private Boolean needFridge;

    @NotNull(message = "weight cant be null")
    private Integer weight;

    @NotNull(message = "containerType cant be null")
    private ContainerTypeEnum containerType;

    @NotNull(message = "name cant be null")
    private String name;

    private String code;

    private Integer clientId;

    private Instant createData;

    private StateEnum state;

}
