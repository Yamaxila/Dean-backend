package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.TeacherDegreeModel;
import by.vstu.dean.future.repo.TeacherDegreeModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели ученой степени преподавателя.
 */
@Service
public class TeacherDegreeService extends BaseService<TeacherDegreeModel, TeacherDegreeModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса ученой степени преподавателя.
     *
     * @param repo Репозиторий для работы с моделью ученой степени преподавателя.
     */
    public TeacherDegreeService(TeacherDegreeModelRepository repo) {
        super(repo);
    }
}
