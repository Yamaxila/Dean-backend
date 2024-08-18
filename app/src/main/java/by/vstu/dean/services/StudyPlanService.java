package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.lessons.StudyPlanDTO;
import by.vstu.dean.dto.mapper.StudyPlanMapper;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.repo.StudyPlanModelRepository;
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
