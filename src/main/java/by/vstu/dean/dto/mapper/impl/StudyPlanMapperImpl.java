package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.lessons.StudyPlanDTO;
import by.vstu.dean.dto.mapper.StudyPlanMapper;
import by.vstu.dean.future.models.lessons.StudyPlanModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class StudyPlanMapperImpl implements StudyPlanMapper {

    public StudyPlanModel toEntity(StudyPlanDTO dto) {
        if (dto == null) {
            return null;
        } else {
            StudyPlanModel studyPlanModel = new StudyPlanModel();
            studyPlanModel.setId(dto.getId());
            studyPlanModel.setSourceId(dto.getSourceId());
            studyPlanModel.setStatus(dto.getStatus());
            studyPlanModel.setUpdated(dto.getUpdated());
            return studyPlanModel;
        }
    }

    public StudyPlanDTO toDto(StudyPlanModel entity) {
        if (entity == null) {
            return null;
        } else {
            StudyPlanDTO studyPlanDTO = new StudyPlanDTO();
            studyPlanDTO.setId(entity.getId());
            studyPlanDTO.setSourceId(entity.getSourceId());
            studyPlanDTO.setUpdated(entity.getUpdated());
            studyPlanDTO.setStatus(entity.getStatus());
            return studyPlanDTO;
        }
    }

    public StudyPlanModel partialUpdate(StudyPlanDTO dto, StudyPlanModel entity) {
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

            return entity;
        }
    }

    public List<StudyPlanDTO> toDto(List<StudyPlanModel> all) {
        if (all == null) {
            return null;
        } else {
            List<StudyPlanDTO> list = new ArrayList(all.size());
            Iterator var3 = all.iterator();

            while(var3.hasNext()) {
                StudyPlanModel studyPlanModel = (StudyPlanModel)var3.next();
                list.add(this.toDto(studyPlanModel));
            }

            return list;
        }
    }

    public List<StudyPlanModel> toEntity(List<StudyPlanDTO> all) {
        if (all == null) {
            return null;
        } else {
            List<StudyPlanModel> list = new ArrayList(all.size());
            Iterator var3 = all.iterator();

            while(var3.hasNext()) {
                StudyPlanDTO studyPlanDTO = (StudyPlanDTO)var3.next();
                list.add(this.toEntity(studyPlanDTO));
            }

            return list;
        }
    }
}
