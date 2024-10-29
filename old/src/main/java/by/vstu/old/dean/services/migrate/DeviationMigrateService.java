package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.changes.ChangeModel;
import by.vstu.dean.models.changes.PenaltyModel;
import by.vstu.dean.models.changes.StudentChangeModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.changes.ChangeService;
import by.vstu.dean.services.changes.PenaltyService;
import by.vstu.dean.services.changes.StudentChangeService;
import by.vstu.dean.services.students.StudentService;
import by.vstu.old.dean.models.DDeviationModel;
import by.vstu.old.dean.repo.DDeviationModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviationMigrateService extends BaseMigrateService<StudentChangeModel, DDeviationModel> implements IMigrateExecutor {

    private final DDeviationModelRepository deviationRepository;
    private final StudentChangeService studentChangeService;
    private final PenaltyService penaltyService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final ChangeService changeService;

    @Override
    public Long getLastDBId() {
        return 0L;
    }

    @Override
    public List<StudentChangeModel> convertNotExistsFromDB() {
        return List.of();
    }

    @Override
    public StudentChangeModel convertSingle(DDeviationModel dDeviationModel, boolean update) {
        throw new RuntimeException("Use separate methods!");
    }

    //TODO: нужно везде использовать StringUtils.safeTrim(s)

    public StudentChangeModel convertToStudentChange(DDeviationModel dDeviationModel, boolean update) {

        StudentChangeModel studentChangeModel = new StudentChangeModel();

        StudentModel student = this.studentService.getRepo().findBySourceId(dDeviationModel.findLastStudent().getId());

        if (student == null)
            throw new NullPointerException("Cannot find student with id " + dDeviationModel.findLastStudent().getId());

        studentChangeModel.setStudent(student);
        studentChangeModel.setOrderNumber(dDeviationModel.getDeviationMsg());
        studentChangeModel.setOrderText(StringUtils.safeTrim(dDeviationModel.getCommandMsg() + " " + dDeviationModel.getCommandMsg1()));
        studentChangeModel.setMsgDate(dDeviationModel.getMsgDate());
        studentChangeModel.setMsg(StringUtils.safeTrim(dDeviationModel.getMsg1() + " " + dDeviationModel.getMsg2()));

        List<ChangeModel> changes = createChanges(dDeviationModel, studentChangeModel);

        //TODO: нужно добавить причины

        if (!update) {
            studentChangeModel.setCreated(LocalDateTime.now());
            studentChangeModel.setSourceId(dDeviationModel.getId());
        }

        studentChangeModel.setUpdated(LocalDateTime.now());


        return studentChangeModel;
    }

    private List<ChangeModel> createChanges(DDeviationModel dDeviationModel, StudentChangeModel studentChangeModel) {
        List<ChangeModel> out = new ArrayList<>();

        if (dDeviationModel.getGroupName() != null && dDeviationModel.getGroupNameNew() != null)
            out.add(ChangeModel.builder().change(studentChangeModel).changedField("group").oldValue(dDeviationModel.getGroupName()).newValue(dDeviationModel.getGroupNameNew()).build());

        if (dDeviationModel.getLastName() != null && dDeviationModel.getLastNameNew() != null)
            out.add(ChangeModel.builder().change(studentChangeModel).changedField("lastName").oldValue(dDeviationModel.getLastName()).newValue(dDeviationModel.getLastNameNew()).build());
//
//        if(dDeviationModel.getExpelled() != null)
//

        return out;
    }

    public PenaltyModel convertToPenalty(DDeviationModel dDeviationModel, boolean update) {
        PenaltyModel penaltyModel = new PenaltyModel();

        StudentModel student = this.studentService.getRepo().findBySourceId(dDeviationModel.findLastStudent().getId());

        if (student == null)
            throw new NullPointerException("Cannot find student with id " + dDeviationModel.findLastStudent().getId());

        penaltyModel.setStudent(student);
        penaltyModel.setOrderNumber(dDeviationModel.getDeviationMsg());
        penaltyModel.setDateLiquidation(dDeviationModel.getDateLiquidation());
        penaltyModel.setOrderText(StringUtils.safeTrim(dDeviationModel.getCommandMsg() + " " + dDeviationModel.getCommandMsg1()));
//        penaltyModel.setReason(dDeviationModel.getReason());
        //TODO: Нужно подсмотреть в проект на Delphi для переноса причин

        if (!update) {
            penaltyModel.setCreated(LocalDateTime.now());
            penaltyModel.setSourceId(dDeviationModel.getId());
        }
        penaltyModel.setUpdated(LocalDateTime.now());

        return penaltyModel;
    }

    @Override
    public StudentChangeModel insertSingle(StudentChangeModel t) {
        return null;
    }

    @Override
    public List<StudentChangeModel> insertAll(List<StudentChangeModel> t) {
        return List.of();
    }

    @Override
    public void migrate() {

    }
}
