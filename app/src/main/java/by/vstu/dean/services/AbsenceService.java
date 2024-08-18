package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.dto.mapper.AbsenceMapper;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("absence")
public class AbsenceService extends BaseService<AbsenceDTO, AbsenceModel, AbsenceMapper, AbsenceModelRepository> {
    public AbsenceService(AbsenceModelRepository repo, AbsenceMapper mapper) {
        super(repo, mapper);
    }
}
