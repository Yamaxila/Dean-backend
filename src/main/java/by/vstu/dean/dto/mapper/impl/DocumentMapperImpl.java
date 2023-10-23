package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.students.*;
import by.vstu.dean.dto.mapper.DocumentMapper;
import by.vstu.dean.future.models.students.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public DocumentModel toEntity(DocumentDTO dto) {
        if (dto == null) {
            return null;
        }

        DocumentModel documentModel = new DocumentModel();

        documentModel.setId(dto.getId());
        documentModel.setSourceId(dto.getSourceId());
        documentModel.setStatus(dto.getStatus());
        documentModel.setUpdated(dto.getUpdated());
        documentModel.setFullNameL(dto.getFullNameL());
        documentModel.setFirstNameL(dto.getFirstNameL());
        documentModel.setCaseNo(dto.getCaseNo());
        documentModel.setCitizenship(citizenshipDTOToCitizenshipModel(dto.getCitizenship()));
        documentModel.setStudentLanguage(studentLanguageDTOToStudentLanguageModel(dto.getStudentLanguage()));
        documentModel.setBirthDate(dto.getBirthDate());
        documentModel.setEducationString(dto.getEducationString());
        documentModel.setInstitution(institutionDTOToInstitutionModel(dto.getInstitution()));
        documentModel.setEnrollmentDate(dto.getEnrollmentDate());
        documentModel.setEducations(educationDTOListToEducationModelList(dto.getEducations()));
        documentModel.setEnrollScore(dto.getEnrollScore());
        documentModel.setNeedHostel(dto.isNeedHostel());
        documentModel.setReEnroll(dto.isReEnroll());
        documentModel.setLastSurname(dto.getLastSurname());
        documentModel.setEnrollStudentScore(dto.getEnrollStudentScore());
        documentModel.setStateSupport(dto.isStateSupport());
        documentModel.setMove(dto.isMove());
        documentModel.setEmail(dto.getEmail());
        documentModel.setEnrollDate(dto.getEnrollDate());

        return documentModel;
    }

    @Override
    public DocumentDTO toDto(DocumentModel entity) {
        if (entity == null) {
            return null;
        }

        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setId(entity.getId());
        documentDTO.setSourceId(entity.getSourceId());
        documentDTO.setUpdated(entity.getUpdated());
        documentDTO.setStatus(entity.getStatus());
        documentDTO.setFullNameL(entity.getFullNameL());
        documentDTO.setFirstNameL(entity.getFirstNameL());
        documentDTO.setCaseNo(entity.getCaseNo());
        documentDTO.setCitizenship(citizenshipModelToCitizenshipDTO(entity.getCitizenship()));
        documentDTO.setStudentLanguage(studentLanguageModelToStudentLanguageDTO(entity.getStudentLanguage()));
        documentDTO.setBirthDate(entity.getBirthDate());
        documentDTO.setEducationString(entity.getEducationString());
        documentDTO.setInstitution(institutionModelToInstitutionDTO(entity.getInstitution()));
        documentDTO.setEnrollmentDate(entity.getEnrollmentDate());
        documentDTO.setEducations(educationModelListToEducationDTOList(entity.getEducations()));
        documentDTO.setEnrollScore(entity.getEnrollScore());
        documentDTO.setNeedHostel(entity.isNeedHostel());
        documentDTO.setReEnroll(entity.isReEnroll());
        documentDTO.setLastSurname(entity.getLastSurname());
        documentDTO.setEnrollStudentScore(entity.getEnrollStudentScore());
        documentDTO.setStateSupport(entity.isStateSupport());
        documentDTO.setMove(entity.isMove());
        documentDTO.setEmail(entity.getEmail());
        documentDTO.setEnrollDate(entity.getEnrollDate());

        return documentDTO;
    }

    @Override
    public DocumentModel partialUpdate(DocumentDTO dto, DocumentModel entity) {
        if (dto == null) {
            return null;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getUpdated() != null) {
            entity.setUpdated(dto.getUpdated());
        }
        if (dto.getFullNameL() != null) {
            entity.setFullNameL(dto.getFullNameL());
        }
        if (dto.getFirstNameL() != null) {
            entity.setFirstNameL(dto.getFirstNameL());
        }
        if (dto.getCaseNo() != null) {
            entity.setCaseNo(dto.getCaseNo());
        }
        if (dto.getCitizenship() != null) {
            if (entity.getCitizenship() == null) {
                entity.setCitizenship(new CitizenshipModel());
            }
            citizenshipDTOToCitizenshipModel1(dto.getCitizenship(), entity.getCitizenship());
        }
        if (dto.getStudentLanguage() != null) {
            if (entity.getStudentLanguage() == null) {
                entity.setStudentLanguage(new StudentLanguageModel());
            }
            studentLanguageDTOToStudentLanguageModel1(dto.getStudentLanguage(), entity.getStudentLanguage());
        }
        if (dto.getBirthDate() != null) {
            entity.setBirthDate(dto.getBirthDate());
        }
        if (dto.getEducationString() != null) {
            entity.setEducationString(dto.getEducationString());
        }
        if (dto.getInstitution() != null) {
            if (entity.getInstitution() == null) {
                entity.setInstitution(new InstitutionModel());
            }
            institutionDTOToInstitutionModel1(dto.getInstitution(), entity.getInstitution());
        }
        if (dto.getEnrollmentDate() != null) {
            entity.setEnrollmentDate(dto.getEnrollmentDate());
        }
        if (entity.getEducations() != null) {
            List<EducationModel> list = educationDTOListToEducationModelList(dto.getEducations());
            if (list != null) {
                entity.getEducations().clear();
                entity.getEducations().addAll(list);
            }
        } else {
            List<EducationModel> list = educationDTOListToEducationModelList(dto.getEducations());
            if (list != null) {
                entity.setEducations(list);
            }
        }
        if (dto.getEnrollScore() != null) {
            entity.setEnrollScore(dto.getEnrollScore());
        }
        entity.setNeedHostel(dto.isNeedHostel());
        entity.setReEnroll(dto.isReEnroll());
        if (dto.getLastSurname() != null) {
            entity.setLastSurname(dto.getLastSurname());
        }
        if (dto.getEnrollStudentScore() != null) {
            entity.setEnrollStudentScore(dto.getEnrollStudentScore());
        }
        entity.setStateSupport(dto.isStateSupport());
        entity.setMove(dto.isMove());
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getEnrollDate() != null) {
            entity.setEnrollDate(dto.getEnrollDate());
        }

        return entity;
    }

    @Override
    public List<DocumentDTO> toDto(List<DocumentModel> all) {
        if (all == null) {
            return null;
        }

        List<DocumentDTO> list = new ArrayList<>(all.size());
        for (DocumentModel documentModel : all) {
            list.add(toDto(documentModel));
        }

        return list;
    }

    @Override
    public List<DocumentModel> toEntity(List<DocumentDTO> all) {
        if (all == null) {
            return null;
        }

        List<DocumentModel> list = new ArrayList<>(all.size());
        for (DocumentDTO documentDTO : all) {
            list.add(toEntity(documentDTO));
        }

        return list;
    }

    protected CitizenshipModel citizenshipDTOToCitizenshipModel(CitizenshipDTO citizenshipDTO) {
        if (citizenshipDTO == null) {
            return null;
        }

        CitizenshipModel citizenshipModel = new CitizenshipModel();

        citizenshipModel.setId(citizenshipDTO.getId());
        citizenshipModel.setStatus(citizenshipDTO.getStatus());
        citizenshipModel.setUpdated(citizenshipDTO.getUpdated());
        citizenshipModel.setName(citizenshipDTO.getName());

        return citizenshipModel;
    }

    protected StudentLanguageModel studentLanguageDTOToStudentLanguageModel(StudentLanguageDTO studentLanguageDTO) {
        if (studentLanguageDTO == null) {
            return null;
        }

        StudentLanguageModel studentLanguageModel = new StudentLanguageModel();

        studentLanguageModel.setId(studentLanguageDTO.getId());
        studentLanguageModel.setStatus(studentLanguageDTO.getStatus());
        studentLanguageModel.setUpdated(studentLanguageDTO.getUpdated());

        return studentLanguageModel;
    }

    protected InstitutionModel institutionDTOToInstitutionModel(InstitutionDTO institutionDTO) {
        if (institutionDTO == null) {
            return null;
        }

        InstitutionModel institutionModel = new InstitutionModel();

        institutionModel.setId(institutionDTO.getId());
        institutionModel.setStatus(institutionDTO.getStatus());
        institutionModel.setUpdated(institutionDTO.getUpdated());

        return institutionModel;
    }

    protected EducationModel educationDTOToEducationModel(EducationDTO educationDTO) {
        if (educationDTO == null) {
            return null;
        }

        EducationModel educationModel = new EducationModel();

        educationModel.setId(educationDTO.getId());
        educationModel.setStatus(educationDTO.getStatus());
        educationModel.setUpdated(educationDTO.getUpdated());
        educationModel.setEducation(educationDTO.getEducation());
        educationModel.setEducationDocumentType(educationDTO.getEducationDocumentType());
        educationModel.setEducationDocumentSerial(educationDTO.getEducationDocumentSerial());
        educationModel.setEducationDocumentNumber(educationDTO.getEducationDocumentNumber());

        return educationModel;
    }

    protected List<EducationModel> educationDTOListToEducationModelList(List<EducationDTO> list) {
        if (list == null) {
            return null;
        }

        List<EducationModel> list1 = new ArrayList<>(list.size());
        for (EducationDTO educationDTO : list) {
            list1.add(educationDTOToEducationModel(educationDTO));
        }

        return list1;
    }

    protected CitizenshipDTO citizenshipModelToCitizenshipDTO(CitizenshipModel citizenshipModel) {
        if (citizenshipModel == null) {
            return null;
        }

        CitizenshipDTO citizenshipDTO = new CitizenshipDTO();

        citizenshipDTO.setId(citizenshipModel.getId());
        citizenshipDTO.setUpdated(citizenshipModel.getUpdated());
        citizenshipDTO.setStatus(citizenshipModel.getStatus());
        citizenshipDTO.setName(citizenshipModel.getName());

        return citizenshipDTO;
    }

    protected StudentLanguageDTO studentLanguageModelToStudentLanguageDTO(StudentLanguageModel studentLanguageModel) {
        if (studentLanguageModel == null) {
            return null;
        }

        StudentLanguageDTO studentLanguageDTO = new StudentLanguageDTO();

        studentLanguageDTO.setId(studentLanguageModel.getId());
        studentLanguageDTO.setUpdated(studentLanguageModel.getUpdated());
        studentLanguageDTO.setStatus(studentLanguageModel.getStatus());

        return studentLanguageDTO;
    }

    protected InstitutionDTO institutionModelToInstitutionDTO(InstitutionModel institutionModel) {
        if (institutionModel == null) {
            return null;
        }

        InstitutionDTO institutionDTO = new InstitutionDTO();

        institutionDTO.setId(institutionModel.getId());
        institutionDTO.setUpdated(institutionModel.getUpdated());
        institutionDTO.setStatus(institutionModel.getStatus());

        return institutionDTO;
    }

    protected EducationDTO educationModelToEducationDTO(EducationModel educationModel) {
        if (educationModel == null) {
            return null;
        }

        EducationDTO educationDTO = new EducationDTO();

        educationDTO.setId(educationModel.getId());
        educationDTO.setUpdated(educationModel.getUpdated());
        educationDTO.setStatus(educationModel.getStatus());
        educationDTO.setEducation(educationModel.getEducation());
        educationDTO.setEducationDocumentType(educationModel.getEducationDocumentType());
        educationDTO.setEducationDocumentSerial(educationModel.getEducationDocumentSerial());
        educationDTO.setEducationDocumentNumber(educationModel.getEducationDocumentNumber());

        return educationDTO;
    }

    protected List<EducationDTO> educationModelListToEducationDTOList(List<EducationModel> list) {
        if (list == null) {
            return null;
        }

        List<EducationDTO> list1 = new ArrayList<>(list.size());
        for (EducationModel educationModel : list) {
            list1.add(educationModelToEducationDTO(educationModel));
        }

        return list1;
    }

    protected void citizenshipDTOToCitizenshipModel1(CitizenshipDTO citizenshipDTO, CitizenshipModel mappingTarget) {
        if (citizenshipDTO == null) {
            return;
        }

        if (citizenshipDTO.getId() != null) {
            mappingTarget.setId(citizenshipDTO.getId());
        }
        if (citizenshipDTO.getStatus() != null) {
            mappingTarget.setStatus(citizenshipDTO.getStatus());
        }
        if (citizenshipDTO.getUpdated() != null) {
            mappingTarget.setUpdated(citizenshipDTO.getUpdated());
        }
        if (citizenshipDTO.getName() != null) {
            mappingTarget.setName(citizenshipDTO.getName());
        }
    }

    protected void studentLanguageDTOToStudentLanguageModel1(StudentLanguageDTO studentLanguageDTO, StudentLanguageModel mappingTarget) {
        if (studentLanguageDTO == null) {
            return;
        }

        if (studentLanguageDTO.getId() != null) {
            mappingTarget.setId(studentLanguageDTO.getId());
        }
        if (studentLanguageDTO.getStatus() != null) {
            mappingTarget.setStatus(studentLanguageDTO.getStatus());
        }
        if (studentLanguageDTO.getUpdated() != null) {
            mappingTarget.setUpdated(studentLanguageDTO.getUpdated());
        }
    }

    protected void institutionDTOToInstitutionModel1(InstitutionDTO institutionDTO, InstitutionModel mappingTarget) {
        if (institutionDTO == null) {
            return;
        }

        if (institutionDTO.getId() != null) {
            mappingTarget.setId(institutionDTO.getId());
        }
        if (institutionDTO.getStatus() != null) {
            mappingTarget.setStatus(institutionDTO.getStatus());
        }
        if (institutionDTO.getUpdated() != null) {
            mappingTarget.setUpdated(institutionDTO.getUpdated());
        }
    }
}
