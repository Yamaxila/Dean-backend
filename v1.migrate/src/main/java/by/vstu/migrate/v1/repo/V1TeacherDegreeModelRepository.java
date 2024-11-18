package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1TeacherDegreeModel;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями званий.
 */
@Repository
public interface V1TeacherDegreeModelRepository extends V1DBBaseModelRepository<V1TeacherDegreeModel> {

    /**
     * Найти звание по назвнию (с частичным соответствием имени).
     *
     * @param name Часть имени
     * @return Объект TeacherDegreeModel, соответствующий части имени
     */
    V1TeacherDegreeModel findByNameLike(String name);
}
