package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Cacheable("absence")
public class AbsenceService extends BaseService<AbsenceModel, AbsenceModelRepository> {
    private final AbsenceModelRepository absenceModelRepository;

    public AbsenceService(AbsenceModelRepository repo, Javers javers, WSControllerManager tm, AbsenceModelRepository absenceModelRepository) {
        super(repo, javers, tm);
        this.absenceModelRepository = absenceModelRepository;
    }

    public List<AbsenceModel> getAbsenceByStudentCaseNo(Long caseNo) {
        return absenceModelRepository.findByStudent_CaseNo(caseNo);
    }
}
