package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.students.EducationDTO;
import by.vstu.dean.dto.mapper.EducationMapper;
import by.vstu.dean.models.students.EducationModel;
import by.vstu.dean.repo.EducationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с объектами модели образования.
 */
@Service
@Cacheable("education")
public class EducationService extends BaseService<EducationDTO, EducationModel, EducationMapper, EducationModelRepository> {

    public EducationService(EducationModelRepository repo, EducationMapper mapper) {
        super(repo, mapper);
    }

    public List<EducationModel> getAllBySourceId(Long sourceId) {
        return this.repo.findAllBySourceId(sourceId);
    }

}
