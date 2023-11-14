package com.vincle.item.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("CLIENTS")
public class ClientEntity {

    @Id
    @Column("ID")
    private Integer id;

    @Column("USER_NAME")
    private String username;

}
