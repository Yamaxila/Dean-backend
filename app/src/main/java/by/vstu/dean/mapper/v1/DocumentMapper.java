package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.DocumentDTO;
import by.vstu.dean.models.students.DocumentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DocumentMapper extends BaseMapperInterface<DocumentDTO, DocumentModel> {

        @Override
        default DocumentDTO toDto(DocumentModel entity) {
            return entity == null ? null : (DocumentDTO) ReflectionUtils.mapObject(entity, new DocumentDTO(), false, false);
        }

}
