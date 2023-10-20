package by.vstu.dean.services;

import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с объектами модели студента.
 */
@Service
@Cacheable("student")
public class StudentService extends BaseService<StudentModel, StudentModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса студента.
     *
     * @param repo Репозиторий для работы с моделью студента.
     */
    public StudentService(StudentModelRepository repo) {
        super(repo);
    }

    /**
     * Получает список студентов по идентификатору группы.
     *
     * @param id Идентификатор группы.
     * @return Список студентов, принадлежащих указанной группе.
     */
    public List<StudentModel> findAllByGroupId(long id) {
        return this.repo.findAllByGroupId(id);
    }

}
