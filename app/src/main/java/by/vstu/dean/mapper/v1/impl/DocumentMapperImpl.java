package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.DocumentDTO;
import by.vstu.dean.mapper.v1.*;
import by.vstu.dean.models.students.DocumentModel;
import by.vstu.dean.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentMapperImpl implements DocumentMapper {

    private final CitizenshipMapper citizenshipMapper;
    private final StudentLanguageMapper studentLanguageMapper;
    private final EducationMapper educationMapper;
    private final InstitutionMapper institutionMapper;
    private final DocumentService documentService;

    @Override
    public DocumentModel toEntity(DocumentDTO dto) {
        if (dto == null) {
            return null;
        }

        DocumentModel documentModel = new DocumentModel();

        if(dto.getId() != null)
            documentModel = this.documentService.getById(dto.getId()).orElse(new DocumentModel());

        documentModel = (DocumentModel) ReflectionUtils.mapObject(documentModel, dto, true, dto.getId() != null);

        if(dto.getCitizenship() != null)
            documentModel.setCitizenship(this.citizenshipMapper.toEntity(dto.getCitizenship()));
        if(dto.getStudentLanguage() != null)
            documentModel.setStudentLanguage(this.studentLanguageMapper.toEntity(dto.getStudentLanguage()));
        if(dto.getEducations() != null)
            documentModel.setEducations(this.educationMapper.toEntity(dto.getEducations()));
        if(dto.getInstitution() != null)
            documentModel.setInstitution(this.institutionMapper.toEntity(dto.getInstitution()));

        return documentModel;
    }

    @Override
    public DocumentDTO toDto(DocumentModel entity) {
        if (entity == null) {
            return null;
        }

        DocumentDTO documentDTO = DocumentMapper.super.toDto(entity);

        documentDTO.setEducations(this.educationMapper.toDto(entity.getEducations()));
        documentDTO.setCitizenship(this.citizenshipMapper.toDto(entity.getCitizenship()));
        documentDTO.setStudentLanguage(this.studentLanguageMapper.toDto(entity.getStudentLanguage()));
        documentDTO.setInstitution(this.institutionMapper.toDto(entity.getInstitution()));

        return documentDTO;
    }

    @Override
    public DocumentModel partialUpdate(DocumentDTO dto, DocumentModel entity) {
        if(dto == null)
            return null;

        entity = DocumentMapper.super.partialUpdate(dto, entity);

        if(dto.getCitizenship() != null)
            entity.setCitizenship(this.citizenshipMapper.toEntity(dto.getCitizenship()));
        if(dto.getStudentLanguage() != null)
            entity.setStudentLanguage(this.studentLanguageMapper.toEntity(dto.getStudentLanguage()));
        if(dto.getEducations() != null)
            entity.setEducations(this.educationMapper.toEntity(dto.getEducations()));
        if(dto.getInstitution() != null)
            entity.setInstitution(this.institutionMapper.toEntity(dto.getInstitution()));


        return entity;
    }

}
