package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.enums.ESemester;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.GroupService;
import by.vstu.old.dean.OldDBBaseModel;
import by.vstu.old.dean.models.DGroupModel;
import by.vstu.old.dean.repo.DGroupModelRepository;
import by.vstu.old.dean.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupMigrateService extends BaseMigrateService<GroupModel, DGroupModel> {

    private final GroupService groupService;
    private final StudentModelRepository studentModelRepository;
    private final FacultyModelRepository facultyModelRepository;

    private final DStudentModelRepository dStudentModelRepository;
    private final DGroupModelRepository dGroupModelRepository;

    @Override
    public Long getLastDBId() {
        return this.groupService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.groupService.getRepo().findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<GroupModel> convertNotExistsFromDB() {
        List<DGroupModel> dGroupModels = new ArrayList<>(this.dStudentModelRepository.findAllGroups(14000L).stream().distinct().toList());
        List<Long> temp = dGroupModels.stream().map(OldDBBaseModel::getId).distinct().toList();
        dGroupModels.addAll(this.dGroupModelRepository.findAllByIdAfter(14000L).stream().filter(p -> !temp.contains(p.getId())).toList());
        dGroupModels = dGroupModels.stream().distinct().toList();
        List<Long> ids = this.groupService.getAll().stream().map(DBBaseModel::getSourceId).toList();

        return this.convertList(dGroupModels.stream().filter(p -> !ids.contains(p.getId())).toList());
    }

    @Override
    public GroupModel convertSingle(DGroupModel dGroupModel, boolean update) {

        GroupModel groupModel = new GroupModel();

        groupModel.setName(StringUtils.safeTrim(dGroupModel.getName()));
        groupModel.setScore(Double.valueOf(dGroupModel.getScore()));
        groupModel.setFaculty(facultyModelRepository.findBySourceId(dGroupModel.getFaculty().getId()));
        groupModel.setYearStart(dGroupModel.getYearStart());
        groupModel.setYearEnd(dGroupModel.getYearEnd());
        groupModel.setCurrentCourse(dGroupModel.getCurrentCourse());
        try {
            groupModel.setEndSemester(dGroupModel.getSemestrEnd().equalsIgnoreCase("осенний") ? ESemester.AUTUMN : ESemester.SPRING);
        } catch (Exception ignored) {
            groupModel.setEndSemester(ESemester.UNKNOWN);
        }
        groupModel.setDateEnd(dGroupModel.getDateEnd() == null ? null : dGroupModel.getDateEnd().toLocalDate());
        groupModel.setDateStart(dGroupModel.getDateStart() == null ? null : dGroupModel.getDateStart().toLocalDate());
        groupModel.setStatus(dGroupModel.getCurrentCourse() == null || dGroupModel.getCurrentCourse() != 99 ? EStatus.ACTIVE : EStatus.DELETED);
        groupModel.setSourceId(dGroupModel.getId());

        if (!update)
            groupModel.setCreated(LocalDateTime.now());
        groupModel.setUpdated(LocalDateTime.now());

        return groupModel;
    }

    @Override
    public List<GroupModel> convertList(List<DGroupModel> t) {
        List<GroupModel> out = new ArrayList<>();

        t.forEach(dGroup -> out.add(this.convertSingle(dGroup)));

        return out;
    }

    public List<GroupModel> applySpecIdByStudents() {
        List<GroupModel> temp = this.groupService.getRepo().findAllBySpecIsNull();
        temp.forEach((group) -> {
            StudentModel studentModel = this.studentModelRepository.findTopByGroupIdAndSpecializationNotNull(group.getId());
            if (studentModel != null) {
                SpecializationModel specializationModel = studentModel.getSpecialization();

                if (specializationModel != null)
                    group.setSpec(specializationModel.getSpec());
            }
        });
        return temp;
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
