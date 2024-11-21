package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.migrate.v1.models.V1FacultyModel;
import by.vstu.migrate.v1.repo.V1FacultyModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyMigrateService extends BaseMigrateService<FacultyModel, V1FacultyModel> {

    private final FacultyService facultyService;
    private final V1FacultyModelRepository v1FacultyModelRepository;

    @Override
    public List<FacultyModel> convertNotExistsFromDB() {
        return this.convertList(this.v1FacultyModelRepository.findAll());
    }

    @Override
    public FacultyModel convertSingle(V1FacultyModel v1FacultyModel, boolean update) {
        FacultyModel f = new FacultyModel();
        f.setId(v1FacultyModel.getId());
        f.setStatus(v1FacultyModel.getStatus().map());
        f.setSourceId(v1FacultyModel.getSourceId());
        f.setCreated(v1FacultyModel.getCreated());
        f.setUpdated(v1FacultyModel.getUpdated());

        f.setShortName(v1FacultyModel.getShortName());
        f.setName(v1FacultyModel.getName());
        f.setNameGenitive(v1FacultyModel.getNameGenitive());
        f.setNameDative(v1FacultyModel.getNameDative());
        f.setRectorName(v1FacultyModel.getRectorName());
        f.setDean(v1FacultyModel.getDean());
        f.setClerkName(v1FacultyModel.getClerkName());
        f.setEnrollMsgPaid(v1FacultyModel.getEnrollMsgPaid());
        f.setEnrollDatePaid(v1FacultyModel.getEnrollDatePaid());
        f.setEnrollMsgNotPaid(v1FacultyModel.getEnrollMsgNotPaid());
        f.setEnrollDateNotPaid(v1FacultyModel.getEnrollDateNotPaid());
        f.setJournalType(v1FacultyModel.getJournalType());
        f.setFacultyType(v1FacultyModel.getFacultyType());
        f.setSemesterStartYear(v1FacultyModel.getSemesterStartYear());
        f.setSemesterEndYear(v1FacultyModel.getSemesterEndYear());
        f.setSemester(v1FacultyModel.getSemester());
        f.setMoveMsgNumber(v1FacultyModel.getMoveMsgNumber());
        f.setMoveMsgDate(v1FacultyModel.getMoveMsgDate());
        f.setEducationType(v1FacultyModel.getEducationType());

        return f;
    }

    @Override
    public FacultyModel insertSingle(FacultyModel t) {
        return this.facultyService.save(t);
    }

    @Override
    public List<FacultyModel> insertAll(List<FacultyModel> t) {
        return this.facultyService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
