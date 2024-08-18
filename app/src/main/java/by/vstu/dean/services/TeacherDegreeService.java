package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.dto.mapper.TeacherDegreeMapper;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели ученой степени преподавателя.
 */
@Service
@Cacheable("degree")
public class TeacherDegreeService extends BaseService<TeacherDegreeDTO, TeacherDegreeModel, TeacherDegreeMapper, TeacherDegreeModelRepository> {


    public TeacherDegreeService(TeacherDegreeModelRepository repo, TeacherDegreeMapper mapper) {
        super(repo, mapper);
    }
}
