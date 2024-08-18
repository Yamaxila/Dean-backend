package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.EducationModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сужностями EducationModel.
 */
@Repository
public interface EducationModelRepository extends DBBaseModelRepository<EducationModel> {

    /**
     * Найти все объекты EducationModel по i источника.
     *
     * @param id Идентификатор источника
     * @return Список объектов EducationModel
     */
    List<EducationModel> findAllBySourceId(Long id);

    /**
     * Найти все объекты EducationModel, у которых studentId равен null.
     *
     * @return Список объектов EducationModel
     */
    List<EducationModel> findAllByStudentIdIsNull();
}
