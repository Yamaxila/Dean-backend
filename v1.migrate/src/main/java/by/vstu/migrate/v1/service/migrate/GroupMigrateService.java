package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.SpecialityService;
import by.vstu.migrate.v1.models.students.V1GroupModel;
import by.vstu.migrate.v1.repo.V1GroupModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMigrateService extends BaseMigrateService<GroupModel, V1GroupModel> {

    private final GroupService groupService;
    private final V1GroupModelRepository v1GroupModelRepository;

    private final SpecialityService specialityService;
    private final FacultyService facultyService;

    @Override
    public List<GroupModel> convertNotExistsFromDB() {
        return this.convertList(this.v1GroupModelRepository.findAll());
    }

    @Override
    public GroupModel convertSingle(V1GroupModel v1GroupModel, boolean update) {
        GroupModel g = new GroupModel();
        g.setId(v1GroupModel.getId());
        g.setSourceId(v1GroupModel.getSourceId());
        g.setStatus(v1GroupModel.getStatus().map());
        g.setCreated(v1GroupModel.getCreated());
        g.setUpdated(v1GroupModel.getUpdated());

        g.setName(v1GroupModel.getName());

        if (v1GroupModel.getSpec() != null)
            this.specialityService.getById(v1GroupModel.getSpec().getId()).ifPresent(g::setSpec);

        this.facultyService.getById(v1GroupModel.getFaculty().getId()).ifPresent(g::setFaculty);

        if (g.getFaculty() == null) {
            throw new RuntimeException("Faculty for group with id = %d not found".formatted(v1GroupModel.getId()));
        }

        g.setYearStart(v1GroupModel.getYearStart());
        g.setYearEnd(v1GroupModel.getYearEnd());
        g.setEndSemester(v1GroupModel.getEndSemester().map());
        g.setDateStart(v1GroupModel.getDateStart());
        g.setDateEnd(v1GroupModel.getDateEnd());
        g.setScore(v1GroupModel.getScore());
        g.setCurrentCourse(v1GroupModel.getCurrentCourse());

        return g;
    }

    @Override
    public GroupModel insertSingle(GroupModel t) {
        return this.groupService.save(t);
    }

    @Override
    public List<GroupModel> insertAll(List<GroupModel> t) {
        return this.groupService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}


