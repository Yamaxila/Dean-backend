package by.vstu.dean.impl;

import by.vstu.dean.dto.mapper.*;
import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AbsenceMapperImpl implements AbsenceMapper {

    @Autowired
    private DisciplineMapper disciplineService;
    @Autowired
    private DepartmentMapper departmentService;
    @Autowired
    private TeacherMapper teacherService;
    @Autowired
    private StudentMapper studentService;

    @Override
    public AbsenceModel toEntity(AbsenceDTO dto) {
        if (dto == null) {
            return null;
        }

        AbsenceModel absenceModel = new AbsenceModel();

        absenceModel.setId(dto.getId());
        absenceModel.setSourceId(dto.getSourceId());
        absenceModel.setStatus(dto.getStatus());
        absenceModel.setUpdated(dto.getUpdated());

        absenceModel.setDate(dto.getDate());
        if (dto.getDiscipline() != null) {
            absenceModel.setDiscipline(this.disciplineService.toEntity(dto.getDiscipline()));
        }
        if (dto.getDepartment() != null) {
            absenceModel.setDepartment(this.departmentService.toEntity(dto.getDepartment()));
        }
        if (dto.getTeacher() != null) {
            absenceModel.setTeacherModel(this.teacherService.toEntity(dto.getTeacher()));
        }
        absenceModel.setLessonType(dto.getLessonType());
        absenceModel.setLessonNumber(dto.getLessonNumber());
        if (dto.getStudent() != null) {
            absenceModel.setStudent(this.studentService.toEntity(dto.getStudent()));
        }
        absenceModel.setAbsenceTime(dto.getAbsenceTime());
        absenceModel.setHourPrice(dto.getHourPrice());
        absenceModel.setPaymentDate(dto.getPaymentDate());
        absenceModel.setDateCompleted(dto.getDateCompleted());
        absenceModel.setDatePrint(dto.getDatePrint());
        absenceModel.setReasonMsg(dto.getReasonMsg());
        absenceModel.setDateErip(dto.getDateErip());
        absenceModel.setPrinted(dto.isPrinted());

        return absenceModel;
    }

    @Override
    public AbsenceDTO toDto(AbsenceModel entity) {
        if (entity == null) {
            return null;
        }

        AbsenceDTO absenceDTO = new AbsenceDTO();

        absenceDTO.setId(entity.getId());
        absenceDTO.setSourceId(entity.getSourceId());
        absenceDTO.setUpdated(entity.getUpdated());
        absenceDTO.setStatus(entity.getStatus());

        absenceDTO.setDate(entity.getDate());
        if (entity.getDiscipline() != null) {
            absenceDTO.setDiscipline(this.disciplineService.toDto(entity.getDiscipline()));
        }
        if (entity.getDepartment() != null) {
            absenceDTO.setDepartment(this.departmentService.toDto(entity.getDepartment()));
        }
        if (entity.getTeacherModel() != null) {
            absenceDTO.setTeacher(this.teacherService.toDto(entity.getTeacherModel()));
        }
        absenceDTO.setLessonType(entity.getLessonType());
        absenceDTO.setLessonNumber(entity.getLessonNumber());
        if (entity.getStudent() != null) {
            absenceDTO.setStudent(this.studentService.toDto(entity.getStudent()));
        }
        absenceDTO.setAbsenceTime(entity.getAbsenceTime());
        absenceDTO.setHourPrice(entity.getHourPrice());
        absenceDTO.setPaymentDate(entity.getPaymentDate());
        absenceDTO.setDateCompleted(entity.getDateCompleted());
        absenceDTO.setDatePrint(entity.getDatePrint());
        absenceDTO.setReasonMsg(entity.getReasonMsg());
        absenceDTO.setDateErip(entity.getDateErip());
        absenceDTO.setPrinted(entity.isPrinted());

        return absenceDTO;
    }

    @Override
    public AbsenceModel partialUpdate(AbsenceDTO dto, AbsenceModel entity) {
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
        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }
        if (dto.getDiscipline() != null) {
            entity.setDiscipline(this.disciplineService.toEntity(dto.getDiscipline()));
        }
        if (dto.getDepartment() != null) {
            entity.setDepartment(this.departmentService.toEntity(dto.getDepartment()));
        }
        if (dto.getTeacher() != null) {
            entity.setTeacherModel(this.teacherService.toEntity(dto.getTeacher()));
        }
        if (dto.getLessonType() != null) {
            entity.setLessonType(dto.getLessonType());
        }
        if (dto.getLessonNumber() != null) {
            entity.setLessonNumber(dto.getLessonNumber());
        }
        if (dto.getStudent() != null) {
            entity.setStudent(this.studentService.toEntity(dto.getStudent()));
        }
        if (dto.getAbsenceTime() != null) {
            entity.setAbsenceTime(dto.getAbsenceTime());
        }
        if (dto.getHourPrice() != null) {
            entity.setHourPrice(dto.getHourPrice());
        }
        if (dto.getPaymentDate() != null) {
            entity.setPaymentDate(dto.getPaymentDate());
        }
        if (dto.getDateCompleted() != null) {
            entity.setDateCompleted(dto.getDateCompleted());
        }
        if (dto.getDatePrint() != null) {
            entity.setDatePrint(dto.getDatePrint());
        }
        if (dto.getReasonMsg() != null) {
            entity.setReasonMsg(dto.getReasonMsg());
        }
        if (dto.getDateErip() != null) {
            entity.setDateErip(dto.getDateErip());
        }
        entity.setPrinted(dto.isPrinted());

        return entity;
    }

    @Override
    public List<AbsenceDTO> toDto(List<AbsenceModel> all) {
        if (all == null) {
            return null;
        }

        List<AbsenceDTO> list = new ArrayList<>(all.size());
        for (AbsenceModel absenceModel : all) {
            list.add(toDto(absenceModel));
        }

        return list;
    }

    @Override
    public List<AbsenceModel> toEntity(List<AbsenceDTO> all) {
        if (all == null) {
            return null;
        }

        List<AbsenceModel> list = new ArrayList<>(all.size());
        for (AbsenceDTO absenceDTO : all) {
            list.add(toEntity(absenceDTO));
        }

        return list;
    }
}
