package by.vstu.dean.services;

import by.vstu.dean.dto.future.FacultyDTO;
import by.vstu.dean.dto.mapper.FacultyMapper;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели факультета.
 */
@Service
@Cacheable("faculty")
public class FacultyService extends BaseService<FacultyDTO, FacultyModel, FacultyMapper, FacultyModelRepository> {


    public FacultyService(FacultyModelRepository repo, FacultyMapper mapper) {
        super(repo, mapper);
    }
}
