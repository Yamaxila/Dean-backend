package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.repo.TeacherModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели преподавателя.
 */
@Service
public class TeacherService extends BaseService<TeacherModel, TeacherModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса преподавателя.
     *
     * @param repo Репозиторий для работы с моделью преподавателя.
     */
    public TeacherService(TeacherModelRepository repo) {
        super(repo);
    }
}
