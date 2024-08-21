package by.vstu.dean.mapper.v1;


import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.DeviationDTO;
import by.vstu.dean.models.students.DeviationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeviationMapper extends BaseMapperInterface<DeviationDTO, DeviationModel> {

    @Override
    default DeviationDTO toDto(DeviationModel entity) {
        return entity == null ? null : (DeviationDTO) ReflectionUtils.mapObject(entity, new DeviationDTO(), false, false);
    }

}
