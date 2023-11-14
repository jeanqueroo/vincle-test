package com.vincle.item.adapter.in.web.model;

import com.vincle.item.app.util.ItemTypeEnum;
import com.vincle.item.app.util.StateEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchItemDTO {

    @NotNull(message = "ItemType id cant be null")
    private ItemTypeEnum itemType;


    @NotNull(message = "code id cant be null")
    private String code;

    private StateEnum state;

}
