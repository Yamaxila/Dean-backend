package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.old.dean.models.DFacultyModel;
import by.vstu.old.dean.repo.DFacultyModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyMigrateService extends BaseMigrateService<FacultyModel, DFacultyModel> {

    private final DFacultyModelRepository dFacultyRepo;
    private final FacultyService facultyService;

    @Override
    public Long getLastDBId() {
        FacultyModel facultyModel = this.facultyService.getRepo().findTopByOrderByIdDesc();
        return facultyModel == null ? 0 : facultyModel.getSourceId();
    }

    @Override
    public List<FacultyModel> convertNotExistsFromDB() {
        List<FacultyModel> out = new ArrayList<>();

        dFacultyRepo.findAllByIdAfter(this.getLastDBId()).forEach(dBase -> {
            FacultyModel faculty = this.convertSingle(dBase);

            out.add(faculty);

        });

        return out;
    }

    @Override
    public FacultyModel convertSingle(DFacultyModel dFaculty, boolean update) {
        FacultyModel faculty = new FacultyModel();
        faculty.setSourceId(dFaculty.getId());
        faculty.setStatus(EStatus.ACTIVE);
        faculty.setFacultyType(dFaculty.getFacultyType().equalsIgnoreCase("заочное") ? 1 : 0);
        faculty.setDean(dFaculty.getDean());
        faculty.setRectorName(dFaculty.getRectorName());
        faculty.setClerkName(dFaculty.getClerkName());
        faculty.setEnrollMsgPaid(dFaculty.getEnrollMsgPaid());
        faculty.setEnrollMsgNotPaid(dFaculty.getEnrollMsgNotPaid());
        faculty.setEnrollDatePaid(dFaculty.getEnrollDateNotPaid() == null ? LocalDate.now() : dFaculty.getEnrollDateNotPaid().toLocalDate());
        faculty.setEnrollDateNotPaid(dFaculty.getEnrollDateNotPaid() == null ? LocalDate.now() : dFaculty.getEnrollDateNotPaid().toLocalDate());
        faculty.setMoveMsgDate(dFaculty.getMoveMsgDate() == null ? LocalDate.now() : dFaculty.getMoveMsgDate().toLocalDate());
        faculty.setMoveMsgNumber(dFaculty.getMoveMsgNumber());
        faculty.setName(dFaculty.getName() + (dFaculty.getId() == 1 ? " (Ссо)" : ""));
        faculty.setNameDative(dFaculty.getNameDative());
        faculty.setNameGenitive(dFaculty.getNameGenitive());
        faculty.setShortName(dFaculty.getShortName());
        faculty.setJournalType(dFaculty.getJournalType());

        if (!update)
            faculty.setCreated(LocalDateTime.now());
        faculty.setUpdated(LocalDateTime.now());

        return faculty;
    }

    @Override
    public List<FacultyModel> convertList(List<DFacultyModel> t) {
        List<FacultyModel> out = new ArrayList<>();

        t.forEach(dFaculty -> {
            FacultyModel faculty = this.convertSingle(dFaculty);
            out.add(faculty);
        });

        return out;
    }

    @Override
    public FacultyModel insertSingle(FacultyModel facultyModel) {
        return this.facultyService.save(facultyModel);
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
