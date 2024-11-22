package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.services.ClassroomService;
import by.vstu.dean.services.DepartmentService;
import by.vstu.migrate.v1.models.rooms.V1ClassroomModel;
import by.vstu.migrate.v1.repo.V1ClassroomModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomsMigrateService extends BaseMigrateService<ClassroomModel, V1ClassroomModel> {

    private final ClassroomService classroomService;
    private final V1ClassroomModelRepository v1ClassroomModelRepository;

    private final DepartmentService departmentService;

    @Override
    public List<ClassroomModel> convertNotExistsFromDB() {
        return this.convertList(this.v1ClassroomModelRepository.findAll());
    }

    @Override
    public ClassroomModel convertSingle(V1ClassroomModel v1ClassroomModel, boolean update) {
        ClassroomModel c = new ClassroomModel();
        c.setSourceId(v1ClassroomModel.getId());
//        c.setSourceId(v1ClassroomModel.getSourceId());
        c.setStatus(v1ClassroomModel.getStatus().map());
        c.setCreated(v1ClassroomModel.getCreated());
        c.setUpdated(v1ClassroomModel.getUpdated());

        c.setFrame(v1ClassroomModel.getFrame().map());
        c.setRoomType(v1ClassroomModel.getRoomType().map());

        if (v1ClassroomModel.getDepartment() != null)
            this.departmentService.getById(v1ClassroomModel.getDepartment().getId()).ifPresentOrElse(c::setDepartment, () -> c.setDepartment(null));

        c.setRoomNumber(v1ClassroomModel.getRoomNumber());
        c.setSeatsNumber(v1ClassroomModel.getSeatsNumber());
        c.setSquare(v1ClassroomModel.getSquare());

        return c;
    }

    @Override
    public ClassroomModel insertSingle(ClassroomModel t) {
        return this.classroomService.save(t);
    }

    @Override
    public List<ClassroomModel> insertAll(List<ClassroomModel> t) {
        return this.classroomService.saveAll(t);
    }

    @Override
    @PostConstruct
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
