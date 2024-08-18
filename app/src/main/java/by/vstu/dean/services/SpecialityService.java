package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.dto.mapper.SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели специальности.
 */
@Service
@Cacheable("speciality")
public class SpecialityService extends BaseService<SpecialityDTO, SpecialityModel, SpecialityMapper, SpecialityModelRepository> {


    public SpecialityService(SpecialityModelRepository repo, SpecialityMapper mapper) {
        super(repo, mapper);
    }
}
