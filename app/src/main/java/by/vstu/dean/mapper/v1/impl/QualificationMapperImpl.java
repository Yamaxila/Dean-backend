package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.mapper.v1.QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapperImpl implements QualificationMapper {

    @Autowired
    private QualificationService qualificationService;

    public QualificationModel toEntity(QualificationDTO dto) {
        if (dto == null) {
            return null;
        }

        QualificationModel qualificationModel = new QualificationModel();

        if (dto.getId() != null)
            qualificationModel = this.qualificationService.getById(dto.getId()).orElse(new QualificationModel());


        return (QualificationModel) ReflectionUtils.mapObject(qualificationModel, dto, true, dto.getId() != null);
    }

}
