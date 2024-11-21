package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.enums.ESCMessageType;
import by.vstu.dean.models.changes.ChangeModel;
import by.vstu.dean.models.changes.StudentChangeModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.changes.ChangeService;
import by.vstu.dean.services.changes.StudentChangeService;
import by.vstu.dean.services.students.StudentService;
import by.vstu.old.dean.models.DDeviationModel;
import by.vstu.old.dean.repo.DDeviationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentChangeMigrateService extends BaseMigrateService<StudentChangeModel, DDeviationModel> {

    private final StudentChangeService studentChangeService;
    private final ChangeService changeService;
    private final StudentService studentService;

    private final DDeviationModelRepository deviationRepository;

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
        StudentChangeModel studentChangeModel = new StudentChangeModel();

        StudentModel student = this.studentService.getRepo().findBySourceId(dDeviationModel.findLastStudent().getId());

        if (student == null)
            throw new NullPointerException("Cannot find student with id " + dDeviationModel.findLastStudent().getId());

        studentChangeModel.setStudent(student);
        studentChangeModel.setOrderNumber(dDeviationModel.getDeviationMsg());
        studentChangeModel.setOrderText(StringUtils.safeTrim(dDeviationModel.getCommandMsg() + " " + dDeviationModel.getCommandMsg1()));
        studentChangeModel.setMsgDate(dDeviationModel.getMsgDate());
        studentChangeModel.setMsg(StringUtils.safeTrim(dDeviationModel.getMsg1() + " " + dDeviationModel.getMsg2()));

        studentChangeModel.setChanges(createChanges(dDeviationModel));

        studentChangeModel.setStudentChangeType(ESCMessageType.UNKNOWN);
        studentChangeModel.setReason(null);
        //TODO: нужно добавить причины

        if (!update) {
            studentChangeModel.setCreated(LocalDateTime.now());
            studentChangeModel.setSourceId(dDeviationModel.getId());
        }

        studentChangeModel.setUpdated(LocalDateTime.now());


        return studentChangeModel;
    }

    private List<ChangeModel> createChanges(DDeviationModel dDeviationModel) {
        List<ChangeModel> out = new ArrayList<>();

        if (dDeviationModel.getGroupName() != null && dDeviationModel.getGroupNameNew() != null)
            out.add(ChangeModel.builder().changedField("group").oldValue(dDeviationModel.getGroupName()).newValue(dDeviationModel.getGroupNameNew()).build());

        if (dDeviationModel.getLastName() != null && dDeviationModel.getLastNameNew() != null)
            out.add(ChangeModel.builder().changedField("lastName").oldValue(dDeviationModel.getLastName()).newValue(dDeviationModel.getLastNameNew()).build());
//
//        if(dDeviationModel.getExpelled() != null)
//

        return out;
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
