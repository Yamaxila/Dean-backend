package by.vstu.dean.services;

import by.vstu.dean.dto.future.lessons.StudyPlanDTO;
import by.vstu.dean.dto.mapper.StudyPlanMapper;
import by.vstu.dean.future.models.lessons.StudyPlanModel;
import by.vstu.dean.future.repo.StudyPlanModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного плана.
 */
@Service
@Cacheable("studyplan")
public class StudyPlanService extends BaseService<StudyPlanDTO, StudyPlanModel, StudyPlanMapper, StudyPlanModelRepository> {


    public StudyPlanService(StudyPlanModelRepository repo, StudyPlanMapper mapper) {
        super(repo, mapper);
    }
}
