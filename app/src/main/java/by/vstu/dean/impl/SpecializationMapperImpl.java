package by.vstu.dean.impl;

import by.vstu.dean.dto.mapper.SpecializationMapper;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.models.specs.SpecializationModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpecializationMapperImpl implements SpecializationMapper {
    public SpecializationMapperImpl() {
    }

    public SpecializationModel toEntity(SpecializationDTO dto) {
        if (dto == null) {
            return null;
        } else {
            SpecializationModel specializationModel = new SpecializationModel();
            specializationModel.setId(dto.getId());
            specializationModel.setSourceId(dto.getSourceId());
            specializationModel.setStatus(dto.getStatus());
            specializationModel.setUpdated(dto.getUpdated());
            specializationModel.setName(dto.getName());
            specializationModel.setShortName(dto.getShortName());
            specializationModel.setSpezCode(dto.getSpezCode());
            specializationModel.setSpec(this.specialityDTOToSpecialityModel(dto.getSpec()));
            return specializationModel;
        }
    }

    public SpecializationDTO toDto(SpecializationModel entity) {
        if (entity == null) {
            return null;
        } else {
            SpecializationDTO specializationDTO = new SpecializationDTO();
            specializationDTO.setId(entity.getId());
            specializationDTO.setSourceId(entity.getSourceId());
            specializationDTO.setUpdated(entity.getUpdated());
            specializationDTO.setStatus(entity.getStatus());
            specializationDTO.setName(entity.getName());
            specializationDTO.setShortName(entity.getShortName());
            specializationDTO.setSpezCode(entity.getSpezCode());
            specializationDTO.setSpec(this.specialityModelToSpecialityDTO(entity.getSpec()));
            return specializationDTO;
        }
    }

    public SpecializationModel partialUpdate(SpecializationDTO dto, SpecializationModel entity) {
        if (dto == null) {
            return null;
        } else {
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }

            if (dto.getStatus() != null) {
                entity.setStatus(dto.getStatus());
            }

            if (dto.getUpdated() != null) {
                entity.setUpdated(dto.getUpdated());
            }

            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }

            if (dto.getShortName() != null) {
                entity.setShortName(dto.getShortName());
            }

            if (dto.getSpezCode() != null) {
                entity.setSpezCode(dto.getSpezCode());
            }

            if (dto.getSpec() != null) {
                if (entity.getSpec() == null) {
                    entity.setSpec(new SpecialityModel());
                }

                this.specialityDTOToSpecialityModel1(dto.getSpec(), entity.getSpec());
            }

            return entity;
        }
    }

    public List<SpecializationDTO> toDto(List<SpecializationModel> all) {
        if (all == null) {
            return null;
        } else {
            List<SpecializationDTO> list = new ArrayList<>(all.size());

            for (SpecializationModel specializationModel : all) {
                list.add(this.toDto(specializationModel));
            }

            return list;
        }
    }

    public List<SpecializationModel> toEntity(List<SpecializationDTO> all) {
        if (all == null) {
            return null;
        } else {
            List<SpecializationModel> list = new ArrayList<>(all.size());

            for (SpecializationDTO specializationDTO : all) {
                list.add(this.toEntity(specializationDTO));
            }

            return list;
        }
    }

    protected SpecialityModel specialityDTOToSpecialityModel(SpecialityDTO specialityDTO) {
        if (specialityDTO == null) {
            return null;
        } else {
            SpecialityModel specialityModel = new SpecialityModel();
            specialityModel.setId(specialityDTO.getId());
            specialityModel.setStatus(specialityDTO.getStatus());
            specialityModel.setUpdated(specialityDTO.getUpdated());
            specialityModel.setName(specialityDTO.getName());
            specialityModel.setShortName(specialityDTO.getShortName());
            specialityModel.setSpecCode(specialityDTO.getSpecCode());
            return specialityModel;
        }
    }

    protected SpecialityDTO specialityModelToSpecialityDTO(SpecialityModel specialityModel) {
        if (specialityModel == null) {
            return null;
        } else {
            SpecialityDTO specialityDTO = new SpecialityDTO();
            specialityDTO.setId(specialityModel.getId());
            specialityDTO.setUpdated(specialityModel.getUpdated());
            specialityDTO.setStatus(specialityModel.getStatus());
            specialityDTO.setName(specialityModel.getName());
            specialityDTO.setShortName(specialityModel.getShortName());
            specialityDTO.setSpecCode(specialityModel.getSpecCode());
            return specialityDTO;
        }
    }

    protected void specialityDTOToSpecialityModel1(SpecialityDTO specialityDTO, SpecialityModel mappingTarget) {
        if (specialityDTO != null) {
            if (specialityDTO.getId() != null) {
                mappingTarget.setId(specialityDTO.getId());
            }

            if (specialityDTO.getStatus() != null) {
                mappingTarget.setStatus(specialityDTO.getStatus());
            }

            if (specialityDTO.getUpdated() != null) {
                mappingTarget.setUpdated(specialityDTO.getUpdated());
            }

            if (specialityDTO.getName() != null) {
                mappingTarget.setName(specialityDTO.getName());
            }

            if (specialityDTO.getShortName() != null) {
                mappingTarget.setShortName(specialityDTO.getShortName());
            }

            if (specialityDTO.getSpecCode() != null) {
                mappingTarget.setSpecCode(specialityDTO.getSpecCode());
            }

        }
    }
}
