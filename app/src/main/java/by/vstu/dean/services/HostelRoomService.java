package by.vstu.dean.services;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.HostelRoomModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Cacheable("hostel")
public class HostelRoomService extends BaseService<HostelRoomModel, HostelRoomModelRepository> {


    private final StudentService studentService;

    public HostelRoomService(HostelRoomModelRepository repo, StudentService studentService) {
        super(repo);
        this.studentService = studentService;
    }

    public HostelRoomModel setStudent(HostelRoomModel hostelRoom, Long studentId) {
        Optional<StudentModel> oStudent = studentService.getById(studentId);

        if(oStudent.isEmpty())
            return null;

        StudentModel student = oStudent.get();

        student.setHostelRoom(hostelRoom);
        student.setCheckInDate(LocalDate.now());
        this.studentService.save(student);

//        Set<StudentModel> students = hostelRoom.getStudents();
//        List<StudentModel> students = hostelRoom.getStudents();
//        students.add(student);
//        hostelRoom.setStudents(students);

        return hostelRoom;
    }
    //FIXME: Нужно обновлять приходящую модель
    public HostelRoomModel removeStudent(HostelRoomModel hostelRoom, Long studentId) {
        Optional<StudentModel> oStudent = studentService.getById(studentId);

        if(oStudent.isEmpty())
            return null;

        StudentModel student = oStudent.get();

        student.setHostelRoom(null);
        student.setEvictionDate(LocalDate.now());
        studentService.save(student);

//        Set<StudentModel> students = hostelRoom.getStudents();
//        students = students.stream().filter(studentModel -> !studentModel.getId().equals(studentId)).collect(Collectors.toSet());
//        List<StudentModel> students = hostelRoom.getStudents();
//        students = students.stream().filter(studentModel -> !studentModel.getId().equals(studentId)).toList();
//        hostelRoom.setStudents(students);

        return hostelRoom;
    }
    @PostConstruct()
    @Order(7)
    public void init() {

        long count = this.repo.count();

        long calculatedCount = (24 * 7) + (44 * 4);

        if (count < calculatedCount)
            System.err.println("Calculated count of hostel rooms less that expected!");
        else
            return;

        List<HostelRoomModel> out = new ArrayList<>();

        for (int floor = 3; floor < 10; floor++) {
            for (int room = 1; room < 13; room++) {
                HostelRoomModel hostelRoomModelLittle = new HostelRoomModel();

                hostelRoomModelLittle.setHostel(EHostel.HOSTEL_3);
                hostelRoomModelLittle.setFloor(floor);
                hostelRoomModelLittle.setRoomType(EHostelRoomType.LITTLE);
                hostelRoomModelLittle.setRoomNumber(floor * 100 + room);
                hostelRoomModelLittle.setStatus(EStatus.ACTIVE);
                hostelRoomModelLittle.setSourceId(0L);

                HostelRoomModel hostelRoomModelBig = new HostelRoomModel();

                hostelRoomModelBig.setHostel(EHostel.HOSTEL_3);
                hostelRoomModelBig.setFloor(floor);
                hostelRoomModelBig.setRoomType(EHostelRoomType.BIG);
                hostelRoomModelBig.setRoomNumber(floor * 100 + room);
                hostelRoomModelBig.setStatus(EStatus.ACTIVE);
                hostelRoomModelBig.setSourceId(0L);

                out.add(hostelRoomModelLittle);
                out.add(hostelRoomModelBig);
            }
        }

        for (int floor = 2; floor < 6; floor++) {
            for (int room = 1; room < 23; room++) {
                HostelRoomModel hostelRoomModelLittle = new HostelRoomModel();

                hostelRoomModelLittle.setHostel(EHostel.HOSTEL_2);
                hostelRoomModelLittle.setFloor(floor);
                hostelRoomModelLittle.setRoomType(EHostelRoomType.LITTLE);
                hostelRoomModelLittle.setRoomNumber(floor * 100 + room);
                hostelRoomModelLittle.setStatus(EStatus.ACTIVE);
                hostelRoomModelLittle.setSourceId(0L);

                HostelRoomModel hostelRoomModelBig = new HostelRoomModel();

                hostelRoomModelBig.setHostel(EHostel.HOSTEL_2);
                hostelRoomModelBig.setFloor(floor);
                hostelRoomModelBig.setRoomType(EHostelRoomType.BIG);
                hostelRoomModelBig.setRoomNumber(floor * 100 + room);
                hostelRoomModelBig.setStatus(EStatus.ACTIVE);
                hostelRoomModelBig.setSourceId(0L);

                out.add(hostelRoomModelLittle);
                out.add(hostelRoomModelBig);
            }
        }

        System.out.println("Calculated room count: " + out.size());

        this.saveAll(out);

    }

}
