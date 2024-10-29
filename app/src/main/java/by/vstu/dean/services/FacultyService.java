package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели факультета.
 */
@Service
@Cacheable("faculty")
public class FacultyService extends BaseService<FacultyModel, FacultyModelRepository> {


    public FacultyService(FacultyModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
