package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.repo.TeacherModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели преподавателя.
 */
@Service
@Cacheable("teacher")
public class TeacherService extends BaseService<TeacherDTO, TeacherModel, TeacherMapper, TeacherModelRepository> {


    public TeacherService(TeacherModelRepository repo, TeacherMapper mapper) {
        super(repo, mapper);
    }
}
