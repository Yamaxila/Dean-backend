package by.vstu.dean.core.models.mapper;

import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.ReflectionUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Интерфейс базового маппера для преобразования между DTO (Data Transfer Object) и сущностями базы данных.
 *
 * @param <D> Тип DTO.
 * @param <O> Тип сущности базы данных.
 */
public interface BaseMapperInterface<D extends PublicDTO, O extends DBBaseModel> {

    /**
     * Преобразует DTO в сущность базы данных.
     *
     * @param dto DTO, который нужно преобразовать.
     * @return Сущность базы данных, соответствующую переданному DTO.
     */
    O toEntity(D dto);

    /**
     * Преобразует сущность базы данных в DTO.
     *
     * @param entity Сущность базы данных, которую нужно преобразовать.
     * @return DTO, соответствующее переданной сущности.
     */
    D toDto(O entity);

    /**
     * Частичное обновление сущности на основе данных из DTO.
     *
     * @param dto    DTO, содержащее обновленные данные.
     * @param entity Сущность, которую нужно обновить.
     * @return Обновленная сущность базы данных.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default O partialUpdate(D dto, @MappingTarget O entity) {
        if(dto == null) {
            return null;
        }

        return (O) ReflectionUtils.mapObject(entity, dto, true, true);
    }

    /**
     * Преобразует список DTO в список сущностей базы данных.
     *
     * @param all Список DTO для преобразования.
     * @return Список сущностей базы данных, соответствующих переданным DTO.
     */
    default List<O> toEntity(List<D> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

    /**
     * Преобразует список сущностей базы данных в список DTO.
     *
     * @param all Список сущностей для преобразования.
     * @return Список DTO, соответствующих переданным сущностям.
     */
    default List<D> toDto(List<O> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }
}

