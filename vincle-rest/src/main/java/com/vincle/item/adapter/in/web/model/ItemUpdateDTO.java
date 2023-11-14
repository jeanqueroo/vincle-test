package com.vincle.item.adapter.in.web.model;

import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.app.util.UpdateStateEnum;
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
public class ItemUpdateDTO {


    @NotNull(message = "needFridge id cant be null")
    private Boolean needFridge;

    @NotNull(message = "weight id cant be null")
    private Integer weight;

    @NotNull(message = "containerType id cant be null")
    private ContainerTypeEnum containerType;

    @NotNull(message = "name id cant be null")
    private String name;

    private Integer modifiedBy;

    @NotNull(message = "state id cant be null")
    private UpdateStateEnum state;

}
