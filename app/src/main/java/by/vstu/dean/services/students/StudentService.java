package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с объектами модели студента.
 */
@Service
public class StudentService extends BaseService<StudentModel, StudentModelRepository> {

    public StudentService(StudentModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
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

    /**
     * Получает студента по номеру зачетки.
     *
     * @param caseNo Номер зачетки.
     * @return Опциональную модель студента, если он найден.
     */
    @Cacheable(value = "students", key = "#caseNo")
    public Optional<StudentModel> findByCaseNo(long caseNo) {
        return this.repo.findByCaseNo(caseNo);
    }

    /**
     * Получает студентов по номеру зачетки.
     *
     * @param caseNoLike Номер зачетки.
     * @return Список моделей студентов, если они найдены.
     */
    @Cacheable(value = "students", key = "#caseNoLike")
    public List<StudentModel> findAllByCaseNo(String caseNoLike) {
        return this.repo.findAllByCaseNo(caseNoLike);
    }

}
