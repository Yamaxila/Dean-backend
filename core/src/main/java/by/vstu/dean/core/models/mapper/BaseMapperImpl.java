package by.vstu.dean.core.models.mapper;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;

@SuppressWarnings("unused")
public class BaseMapperImpl {

    protected BaseDTO mapBaseFields(DBBaseModel dbBaseModel) {
        BaseDTO dto = new BaseDTO();
        dto.setId(dbBaseModel.getId());
        dto.setSourceId(dbBaseModel.getSourceId());
        dto.setUpdated(dbBaseModel.getUpdated());
        dto.setStatus(dbBaseModel.getStatus());
        return dto;
    }

}
