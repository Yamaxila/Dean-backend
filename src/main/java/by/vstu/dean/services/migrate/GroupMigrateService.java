package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.old.models.DGroupModel;
import by.vstu.dean.old.repo.DGroupModelRepository;
import by.vstu.dean.old.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupMigrateService extends BaseMigrateService<GroupModel, DGroupModel>{

    private final GroupModelRepository groupRepo;
    private final StudentModelRepository studentModelRepository;
    private final FacultyModelRepository facultyModelRepository;

    private final DGroupModelRepository dGroupRepo;
    private final DStudentModelRepository dStudentModelRepository;

    @Override
    public Long getLastDBId() {
        return groupRepo.findTopByOrderByIdDesc() == null ? 0 : groupRepo.findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<GroupModel> convertNotExistsFromDB() {

        List<DGroupModel> dGroupModels = this.dStudentModelRepository.findAllGroups(19000L).stream().distinct().toList();
        List<Long> ids = this.groupRepo.findAll().stream().map(DBBaseModel::getSourceId).toList();

        return this.convertList(dGroupModels.stream().filter(p -> !ids.contains(p.getId())).toList());
    }

    @Override
    public GroupModel convertSingle(DGroupModel dGroupModel) {

        GroupModel groupModel = new GroupModel();

        groupModel.setName(dGroupModel.getName());
        groupModel.setScore(Double.valueOf(dGroupModel.getScore()));
        groupModel.setFaculty(facultyModelRepository.findBySourceId(dGroupModel.getFaculty().getId()));
        groupModel.setYearStart(dGroupModel.getYearStart());
        groupModel.setYearEnd(dGroupModel.getYearEnd());
        groupModel.setDateEnd(dGroupModel.getDateEnd() == null ? null : dGroupModel.getDateEnd().toLocalDate());
        groupModel.setDateStart(dGroupModel.getDateStart() == null ? null : dGroupModel.getDateStart().toLocalDate());
        groupModel.setStatus(EStatus.ACTIVE);
        groupModel.setSourceId(dGroupModel.getId());

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
            group.setSpec(this.studentModelRepository.findTopByGroupIdAndSpecializationNotNull(group.getId()).getSpecialization().getSpec());
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
