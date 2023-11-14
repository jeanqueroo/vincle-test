package com.vincle.item.adapter.out.persistence.entity;

import com.vincle.item.app.util.ItemTypeEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("CODE_SEQUENCE")
public class CodeSequenceEntity {

    @Id
    @Column("ID")
    private Integer id;

    @Column("NAME")
    private ItemTypeEnum name;

    @Column("CODE")
    private Integer code;
}
