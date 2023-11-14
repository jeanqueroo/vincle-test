package com.vincle.item.domain;

import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.app.util.UpdateStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemUpdate {

    private Boolean needFridge;

    private Integer weight;

    private ContainerTypeEnum containerType;

    private String name;

    private Integer modifiedBy;

    private UpdateStateEnum state;

}
