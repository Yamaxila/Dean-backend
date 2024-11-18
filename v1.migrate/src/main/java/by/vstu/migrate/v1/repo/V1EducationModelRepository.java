package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1EducationModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сужностями EducationModel.
 */
@Repository
public interface V1EducationModelRepository extends V1DBBaseModelRepository<V1EducationModel> {

    /**
     * Найти все объекты EducationModel по i источника.
     *
     * @param id Идентификатор источника
     * @return Список объектов EducationModel
     */
    List<V1EducationModel> findAllBySourceId(Long id);

    /**
     * Найти все объекты EducationModel, у которых studentId равен null.
     *
     * @return Список объектов EducationModel
     */
    List<V1EducationModel> findAllByStudentIdIsNull();

    boolean existsBySourceId(Long sourceId);
}
