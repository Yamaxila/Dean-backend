package by.vstu.dean.services.migrate;


import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.SpecializationModel;
import by.vstu.dean.future.models.StudentModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentMigrateService extends BaseMigrateService<StudentModel, DStudentModel> {

    private final StudentModelRepository studentModelRepository;
    private final SpecializationModelRepository specializationModelRepository;
    private final GroupModelRepository groupModelRepository;

    private final DStudentModelRepository dStudentModelRepository;

    private final DocumentMigrateService documentMigrateService;

    private final List<SpecializationModel> specializations = new ArrayList<>();

    @Override
    public Long getLastDBId() {
        return this.studentModelRepository.findTopByOrderByIdDesc() == null ? 0 : this.studentModelRepository.findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<StudentModel> convertNotExistsFromDB() {

        List<StudentModel> out = new ArrayList<>();
        List<Long> ids = this.studentModelRepository.findAllSourceIds();
        this.specializations.addAll(this.specializationModelRepository.findAll());
        this.groupModelRepository.findAll().forEach(group -> {

            List<DStudentModel> temp = this.dStudentModelRepository.findAllByGroupId(group.getSourceId())
                    .stream().filter(p -> !ids.contains(p.getId())).toList();
            out.addAll(this.convertList(temp));

        });


        return out;
    }

    @Override
    public StudentModel convertSingle(DStudentModel dStudentModel) {

        StudentModel studentModel = new StudentModel();

        studentModel.setGroup(this.groupModelRepository.findBySourceId(dStudentModel.getGroup().getId()));
        studentModel.setLastName(dStudentModel.getLastName());
        studentModel.setFirstName(dStudentModel.getFirstName());
        studentModel.setSecondName(dStudentModel.getSecondName());
        studentModel.setAddressCountry(dStudentModel.getAddressCity());
        studentModel.setAddressCity(dStudentModel.getAddressCity2());
        studentModel.setAddressHouse(dStudentModel.getAddressHouse());
        studentModel.setAddressHousePart(dStudentModel.getAddressHousePart());
        studentModel.setAddressIndex(dStudentModel.getAddressIndex());
        studentModel.setAddressState(dStudentModel.getAddressState());
        studentModel.setAddressStreet(dStudentModel.getAddressStreet());
        studentModel.setAddressRegion(dStudentModel.getAddressRegion());
        studentModel.setAddressFlat(dStudentModel.getAddressFlat());
        studentModel.setPhone(dStudentModel.getPhone());
        studentModel.setSex(dStudentModel.getSex().equals("М") ? 1 : 0);
        studentModel.setSourceId(dStudentModel.getId());
        studentModel.setStatus(dStudentModel.isExpelled() ? EStatus.DELETED : EStatus.ACTIVE);
        if(dStudentModel.getSpecialization() != null)
            if(this.specializations.isEmpty())
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
