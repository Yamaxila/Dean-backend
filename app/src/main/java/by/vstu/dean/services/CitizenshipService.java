package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.dto.mapper.CitizenshipMapper;
import by.vstu.dean.models.students.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели гражданства.
 */
@Service
@Cacheable("citizenship")
public class CitizenshipService extends BaseService<CitizenshipDTO, CitizenshipModel, CitizenshipMapper, CitizenshipModelRepository> {
    /**
     * Конструктор для создания экземпляра сервиса гражданства.
     *
     * @param repo   Репозиторий для работы с моделью гражданства.
     * @param mapper Маппер.
     */
    public CitizenshipService(CitizenshipModelRepository repo, CitizenshipMapper mapper) {
        super(repo, mapper);
    }


}
