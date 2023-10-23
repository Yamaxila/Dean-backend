package by.vstu.dean.services;

import by.vstu.dean.dto.future.students.EducationDTO;
import by.vstu.dean.dto.mapper.EducationMapper;
import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели образования.
 */
@Service
@Cacheable("education")
public class EducationService extends BaseService<EducationDTO, EducationModel, EducationMapper, EducationModelRepository> {

    public EducationService(EducationModelRepository repo, EducationMapper mapper) {
        super(repo, mapper);
    }
}
