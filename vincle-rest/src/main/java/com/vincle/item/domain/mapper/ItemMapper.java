package com.vincle.item.domain.mapper;

import com.vincle.item.adapter.in.web.model.ItemDTO;
import com.vincle.item.adapter.in.web.model.ItemUpdateDTO;
import com.vincle.item.adapter.out.persistence.entity.ItemEntity;
import com.vincle.item.app.util.ContainerTypeEnum;
import com.vincle.item.app.util.StateEnum;
import com.vincle.item.app.util.UpdateStateEnum;
import com.vincle.item.config.MapStructConfig;
import com.vincle.item.domain.Item;
import com.vincle.item.domain.ItemUpdate;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface ItemMapper {

    ItemDTO sourceToDestination(Item item);

    Item destinationToSource(ItemDTO itemDTO);

    Item entitySourceToDestination(ItemEntity itemEntity);

    ItemEntity entityDestinationToSource(Item item);


    @Mapping(target = "state", source = "state", qualifiedByName = "statusUpdate")
    ItemEntity entityToUpdate(ItemUpdate item, @MappingTarget ItemEntity itemEntity);

    ItemUpdate itemToUpdate(ItemUpdateDTO item);

    @Named("statusUpdate")
    default StateEnum statusUpdate(UpdateStateEnum value) {
        return StateEnum.valueOf(value.name());
    }


}
