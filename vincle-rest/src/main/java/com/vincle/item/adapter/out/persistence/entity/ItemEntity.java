package com.vincle.item.adapter.out.persistence.entity;

import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("ITEMS")
public class ItemEntity {

    @Id
    @Column("ID")
    private Integer id;

    @Column("ITEM_TYPE")
    private ItemTypeEnum itemType;

    @Column("NEED_FRIDGE")
    private Boolean needFridge;

    @Column("WEIGHT")
    private Integer weight;

    @Column("CONTAINER_TYPE")
    private ContainerTypeEnum containerType;

    @Column("NAME")
    private String name;

    @Column("CODE")
    private String code;

    @Column("CLIENT_ID")
    @CreatedBy
    private Integer clientId;

    @Column("CREATE_DATA")
    @CreatedDate
    private Instant createData;

    @Column("STATE")
    private StateEnum state;

    @Column("MODIFIED_BY")
    @LastModifiedBy
    private Integer modifiedBy;

    @Column("MODIFIED_DATA")
    @LastModifiedDate
    private Instant modifiedDate;
}
