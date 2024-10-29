package by.vstu.dean.mapper.v1;


import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1DeviationDTO;
import by.vstu.dean.models.students.DeviationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1DeviationMapper extends BaseMapperInterface<V1DeviationDTO, DeviationModel> {

    @Override
    default V1DeviationDTO toDto(DeviationModel entity) {
        return entity == null ? null : (V1DeviationDTO) ReflectionUtils.mapObject(entity, new V1DeviationDTO(), false, false);
    }

}
