package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.TeacherService;
import by.vstu.migrate.v1.models.merge.V1TeacherDepartmentMerge;
import by.vstu.migrate.v1.repo.V1TeacherDepartmentMergeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherDepartmentMigrateService extends BaseMigrateService<TeacherDepartmentMerge, V1TeacherDepartmentMerge> {

    private final TeacherDepartmentMergeRepository teacherDepartmentMergeRepository;
    private final V1TeacherDepartmentMergeRepository v1TeacherDepartmentMergeRepository;

    private final TeacherService teacherService;
    private final DepartmentService departmentService;

    @Override
    public List<TeacherDepartmentMerge> convertNotExistsFromDB() {
        return this.convertList(this.v1TeacherDepartmentMergeRepository.findAll());
    }

    @Override
    public TeacherDepartmentMerge convertSingle(V1TeacherDepartmentMerge v1TeacherDepartmentMerge, boolean update) {
        TeacherDepartmentMerge tdm = new TeacherDepartmentMerge();
        tdm.setId(v1TeacherDepartmentMerge.getId());
        tdm.setSourceId(v1TeacherDepartmentMerge.getSourceId());
        tdm.setStatus(v1TeacherDepartmentMerge.getStatus().map());
        tdm.setCreated(v1TeacherDepartmentMerge.getCreated());
        tdm.setUpdated(v1TeacherDepartmentMerge.getUpdated());

        this.teacherService.getById(v1TeacherDepartmentMerge.getTeacher().getId()).ifPresent(tdm::setTeacher);

        if (tdm.getTeacher() == null) {
            throw new RuntimeException("Teacher for TDM with id = %d not found".formatted(v1TeacherDepartmentMerge.getId()));
        }

        this.departmentService.getById(v1TeacherDepartmentMerge.getDepartment().getId()).ifPresent(tdm::setDepartment);

        if (tdm.getDepartment() == null) {
            throw new RuntimeException("Department for TDM with id = %d not found".formatted(v1TeacherDepartmentMerge.getId()));
        }

        return tdm;
    }

    @Override
    public TeacherDepartmentMerge insertSingle(TeacherDepartmentMerge t) {
        return this.teacherDepartmentMergeRepository.saveAndFlush(t);
    }

    @Override
    public List<TeacherDepartmentMerge> insertAll(List<TeacherDepartmentMerge> t) {
        return this.teacherDepartmentMergeRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
