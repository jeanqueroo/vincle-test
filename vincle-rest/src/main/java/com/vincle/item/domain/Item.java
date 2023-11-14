package com.vincle.item.domain;

import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    private ItemTypeEnum itemType;

    private Boolean needFridge;

    private Integer weight;

    private ContainerTypeEnum containerType;

    private String name;

    private String code;

    private Integer clientId;

    private Instant createData;

    private StateEnum state;

    private Integer modifiedBy;

}
