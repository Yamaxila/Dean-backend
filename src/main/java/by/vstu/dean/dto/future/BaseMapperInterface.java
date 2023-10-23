package by.vstu.dean.dto.future;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.future.DBBaseModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface BaseMapperInterface<D extends BaseDTO, O extends DBBaseModel> {

    O toEntity(D dto);

    D toDto(O entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    O partialUpdate(D dto, @MappingTarget O entity);

    List<D> toDto(List<O> all);

    List<O> toEntity(List<D> all);
}
