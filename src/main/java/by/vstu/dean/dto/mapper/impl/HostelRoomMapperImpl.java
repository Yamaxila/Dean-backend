package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.hostels.HostelRoomDTO;
import by.vstu.dean.dto.future.students.StudentDTO;
import by.vstu.dean.dto.mapper.HostelRoomMapper;
import by.vstu.dean.dto.mapper.StudentMapper;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.future.models.hostels.HostelRoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class HostelRoomMapperImpl implements HostelRoomMapper {

    @Autowired
    private StudentMapper studentMapper;

    public HostelRoomModel toEntity(HostelRoomDTO dto) {
        if (dto == null) {
            return null;
        } else {
            HostelRoomModel hostelRoomModel = new HostelRoomModel();
            hostelRoomModel.setId(dto.getId());
            hostelRoomModel.setSourceId(dto.getSourceId());
            hostelRoomModel.setStatus(dto.getStatus());
            hostelRoomModel.setUpdated(dto.getUpdated());
            hostelRoomModel.setRoomNumber(dto.getRoomNumber());
            hostelRoomModel.setRoomType(dto.getRoomType());
            hostelRoomModel.setFloor(dto.getFloor());
            hostelRoomModel.setHostel(EHostel.valueOf(dto.getHostel().name()));
//            hostelRoomModel.setStudents(new HashSet<>(this.studentMapper.toEntity(dto.getStudents().stream().toList())));
            return hostelRoomModel;
        }
    }

    public HostelRoomDTO toDto(HostelRoomModel entity) {
        if (entity == null) {
            return null;
        } else {
            HostelRoomDTO hostelRoomDTO = new HostelRoomDTO();
            hostelRoomDTO.setId(entity.getId());
            hostelRoomDTO.setSourceId(entity.getSourceId());
            hostelRoomDTO.setUpdated(entity.getUpdated());
            hostelRoomDTO.setStatus(entity.getStatus());
            hostelRoomDTO.setRoomNumber(entity.getRoomNumber());
            hostelRoomDTO.setRoomType(entity.getRoomType());
            hostelRoomDTO.setFloor(entity.getFloor());
            hostelRoomDTO.setHostel(EHostel.valueOf(entity.getHostel().name()));
//            hostelRoomDTO.setStudents(new HashSet<>(this.studentMapper.toDto(entity.getStudents().stream().toList())));
            return hostelRoomDTO;
        }
    }

    public HostelRoomModel partialUpdate(HostelRoomDTO dto, HostelRoomModel entity) {
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
                entity.setUpdated(LocalDateTime.now());
            }

            entity.setRoomNumber(dto.getRoomNumber());
            if (dto.getRoomType() != null) {
                entity.setRoomType(dto.getRoomType());
            }

            entity.setFloor(dto.getFloor());
            if (dto.getHostel() != null) {
                entity.setHostel(dto.getHostel());
            }

//            if (dto.getStudents() != null) {
//                dto.setStudents(new HashSet<>(this.studentMapper.toDto(entity.getStudents().stream().toList())));
//            }

            return entity;
        }
    }

    public List<HostelRoomDTO> toDto(List<HostelRoomModel> all) {
        if (all == null) {
            return null;
        }

        List<HostelRoomDTO> out = new ArrayList<>();

        all.forEach(h -> {

            HostelRoomDTO hostelRoomDTO = this.toDto(h);
            if(h.getStudents().isEmpty()) {
                hostelRoomDTO.setStudent(new StudentDTO());
                out.add(hostelRoomDTO);
            } else
                h.getStudents().forEach((student) -> {
                    hostelRoomDTO.setStudent(this.studentMapper.toDto(student));
                    out.add(hostelRoomDTO);
                });

        });


        return out;
    }

    public List<HostelRoomModel> toEntity(List<HostelRoomDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

}
