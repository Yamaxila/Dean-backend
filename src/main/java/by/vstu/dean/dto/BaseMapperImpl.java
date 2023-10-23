package by.vstu.dean.dto;

import by.vstu.dean.future.DBBaseModel;


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
