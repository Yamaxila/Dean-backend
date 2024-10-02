package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с объектами модели студента.
 */
@Service
public class StudentService extends BaseService<StudentModel, StudentModelRepository> {

    public StudentService(StudentModelRepository repo, Javers javers) {
        super(repo, javers);
    }

    /**
     * Получает список студентов по идентификатору группы.
     *
     * @param groupId Идентификатор группы.
     * @return Список студентов, принадлежащих указанной группе.
     */

    @Cacheable(value = "students", key = "#groupId")
    public List<StudentModel> findAllByGroupId(long groupId) {
        return this.repo.findAllByGroupId(groupId);
    }

}
