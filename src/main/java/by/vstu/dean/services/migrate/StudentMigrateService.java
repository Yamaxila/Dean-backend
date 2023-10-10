package by.vstu.dean.services.migrate;


import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentMigrateService extends BaseMigrateService<StudentModel, DStudentModel> {

    private final StudentModelRepository studentModelRepository;
    private final SpecializationModelRepository specializationModelRepository;
    private final GroupModelRepository groupModelRepository;

    private final DStudentModelRepository dStudentModelRepository;

    private final DocumentMigrateService documentMigrateService;

    private final List<SpecializationModel> specializations = new ArrayList<>();
    private final List<GroupModel> groups = new ArrayList<>();

    @Override
    public Long getLastDBId() {
        return this.studentModelRepository.findTopByOrderByIdDesc() == null ? 0 : this.studentModelRepository.findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<StudentModel> convertNotExistsFromDB() {

        List<StudentModel> out = new ArrayList<>();
        List<Long> ids = this.studentModelRepository.findAllSourceIds();
        this.specializations.addAll(this.specializationModelRepository.findAll());
        this.groups.addAll(this.groupModelRepository.findAll());
        this.groups.forEach(group -> {

            List<DStudentModel> temp = this.dStudentModelRepository.findAllByGroupId(group.getSourceId())
                    .stream().filter(p -> !ids.contains(p.getId())).toList();

            out.addAll(this.convertList(temp));

        });

        return out;
    }

    @Override
    public StudentModel convertSingle(DStudentModel dStudentModel) {

        StudentModel studentModel = new StudentModel();

        if(this.groups.isEmpty()) {
            this.groups.addAll(this.groupModelRepository.findAll());
        }

        if(this.specializations.isEmpty()) {
            this.specializations.addAll(this.specializationModelRepository.findAll());
        }

        Optional<GroupModel> group = this.groups.stream().filter(p -> p.getSourceId().equals(dStudentModel.getGroup().getId())).findFirst();

        if(group.isEmpty())
            throw new RuntimeException("Group for student with sourceId " + dStudentModel.getId() + " not found!");

        studentModel.setGroup(this.groupModelRepository.findBySourceId(dStudentModel.getGroup().getId()));
        studentModel.setLastName(StringUtils.safeTrim(dStudentModel.getLastName()));
        studentModel.setFirstName(StringUtils.safeTrim(dStudentModel.getFirstName()));
        studentModel.setSecondName(StringUtils.safeTrim(dStudentModel.getSecondName()));
        studentModel.setAddressCountry(StringUtils.safeTrim(dStudentModel.getAddressCity()));
        studentModel.setAddressCity(StringUtils.safeTrim(dStudentModel.getAddressCity2()));
        studentModel.setAddressHouse(StringUtils.safeTrim(dStudentModel.getAddressHouse()));
        studentModel.setAddressHousePart(StringUtils.safeTrim(dStudentModel.getAddressHousePart()));
        studentModel.setAddressIndex(StringUtils.safeTrim(dStudentModel.getAddressIndex()));
        studentModel.setAddressState(StringUtils.safeTrim(dStudentModel.getAddressState()));
        studentModel.setAddressStreet(StringUtils.safeTrim(dStudentModel.getAddressStreet()));
        studentModel.setAddressRegion(StringUtils.safeTrim(dStudentModel.getAddressRegion()));
        studentModel.setAddressFlat(StringUtils.safeTrim(dStudentModel.getAddressFlat()));
        studentModel.setPhone(StringUtils.safeTrim(dStudentModel.getPhone()));
        studentModel.setSex((dStudentModel.getSex() != null && dStudentModel.getSex().equals("М")) ? 1 : 0);
        studentModel.setSourceId(dStudentModel.getId());
        studentModel.setApproved(false);
        studentModel.setHostelRoom(null);
        studentModel.setStatus(dStudentModel.isExpelled() || dStudentModel.getGroup().getCurrentCourse().equals(99) ? EStatus.DELETED : EStatus.ACTIVE);
        if (dStudentModel.getSpecialization() != null)
            if (this.specializations.isEmpty())
                studentModel.setSpecialization(this.specializationModelRepository.findBySourceId(dStudentModel.getSpecialization().getId()));
            else
                studentModel.setSpecialization(this.specializations.stream().filter(p -> p.getSourceId().equals(dStudentModel.getSpecialization().getId())).findAny().orElse(null));

        studentModel.setLastDocument(documentMigrateService.convertSingle(dStudentModel));


        return studentModel;
    }

    @Override
    public List<StudentModel> convertList(List<DStudentModel> t) {

        List<StudentModel> temp = new ArrayList<>();

        t.forEach(dStudentModel -> temp.add(this.convertSingle(dStudentModel)));

        return temp;
    }

    @Override
    public StudentModel insertSingle(StudentModel t) {
        return this.studentModelRepository.saveAndFlush(t);
    }

    @Override
    public List<StudentModel> insertAll(List<StudentModel> t) {
        return this.studentModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
