package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.TeacherDegreeModel;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями званий.
 */
@Repository
public interface TeacherDegreeModelRepository extends DBBaseModelRepository<TeacherDegreeModel> {

    /**
     * Найти звание по назвнию (с частичным соответствием имени).
     *
     * @param name Часть имени
     * @return Объект TeacherDegreeModel, соответствующий части имени
     */
    TeacherDegreeModel findByNameLike(String name);
}
