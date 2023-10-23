package by.vstu.dean.services;

import by.vstu.dean.dto.future.students.InstitutionDTO;
import by.vstu.dean.dto.mapper.InstitutionMapper;
import by.vstu.dean.future.models.students.InstitutionModel;
import by.vstu.dean.future.repo.InstitutionModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного учреждения.
 */
@Service
@Cacheable("institution")
public class InstitutionService extends BaseService<InstitutionDTO, InstitutionModel, InstitutionMapper, InstitutionModelRepository> {

    public InstitutionService(InstitutionModelRepository repo, InstitutionMapper mapper) {
        super(repo, mapper);
    }
}
