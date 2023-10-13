package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.old.models.DGroupModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupMigrateService extends BaseMigrateService<GroupModel, DGroupModel> {

    private final GroupModelRepository groupRepo;
    private final StudentModelRepository studentModelRepository;
    private final FacultyModelRepository facultyModelRepository;

    private final DStudentModelRepository dStudentModelRepository;

    @Override
    public Long getLastDBId() {
        return groupRepo.findTopByOrderByIdDesc() == null ? 0 : groupRepo.findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<GroupModel> convertNotExistsFromDB() {

        List<DGroupModel> dGroupModels = this.dStudentModelRepository.findAllGroups(14000L).stream().distinct().toList();
        List<Long> ids = this.groupRepo.findAll().stream().map(DBBaseModel::getSourceId).toList();

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
        groupModel.setDateEnd(dGroupModel.getDateEnd() == null ? null : dGroupModel.getDateEnd().toLocalDate());
        groupModel.setDateStart(dGroupModel.getDateStart() == null ? null : dGroupModel.getDateStart().toLocalDate());
        groupModel.setStatus(dGroupModel.getCurrentCourse() != 99 ? EStatus.ACTIVE : EStatus.DELETED);
        groupModel.setSourceId(dGroupModel.getId());

        if(!update)
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
        List<GroupModel> temp = this.groupRepo.findAllBySpecIsNull();
        temp.forEach((group) -> {
            StudentModel studentModel = this.studentModelRepository.findTopByGroupIdAndSpecializationNotNull(group.getId());
            if(studentModel != null) {
                SpecializationModel specializationModel = studentModel.getSpecialization();

                if (specializationModel != null)
                    group.setSpec(specializationModel.getSpec());
            }
        });
        return temp;
    }

    @Override
    public GroupModel insertSingle(GroupModel t) {
        return this.groupRepo.saveAndFlush(t);
    }

    @Override
    public List<GroupModel> insertAll(List<GroupModel> t) {
        return this.groupRepo.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
