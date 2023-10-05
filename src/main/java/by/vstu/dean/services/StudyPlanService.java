package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.StudyPlanModel;
import by.vstu.dean.future.repo.StudyPlanModelRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyPlanService extends BaseService<StudyPlanModel, StudyPlanModelRepository> {
    public StudyPlanService(StudyPlanModelRepository repo) {
        super(repo);
    }
}
