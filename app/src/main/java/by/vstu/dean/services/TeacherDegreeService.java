package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели ученой степени преподавателя.
 */
@Service
@Cacheable("degree")
public class TeacherDegreeService extends BaseService<TeacherDegreeModel, TeacherDegreeModelRepository> {


    public TeacherDegreeService(TeacherDegreeModelRepository repo) {
        super(repo);
    }
}