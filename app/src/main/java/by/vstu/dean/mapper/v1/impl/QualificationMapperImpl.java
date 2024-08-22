package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.mapper.v1.QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.repo.QualificationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QualificationMapperImpl implements QualificationMapper {

    @Autowired
    private QualificationModelRepository qualificationModelRepository;

    public QualificationModel toEntity(QualificationDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<QualificationModel> optionalQualification = this.qualificationModelRepository.findById(dto.getId());
        QualificationModel qualificationModel = new QualificationModel();
        if (optionalQualification.isPresent())
            qualificationModel = optionalQualification.get();


        return (QualificationModel) ReflectionUtils.mapObject(qualificationModel, dto, true, optionalQualification.isPresent());
    }

}
