package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.merge.TeacherDepartmentMerge;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с связующими сущностями Преодаватель-Кафедра.
 */
@Repository
public interface TeacherDepartmentMergeRepository extends DBBaseModelRepository<TeacherDepartmentMerge> {

    /**
     * Найти связь по идентификаторам кафедры и преподавателя.
     *
     * @param dId Идентификатор кафедры
     * @param tId Идентификатор преподавателя
     * @return Объект TeacherDepartmentMerge, соответствующий указанным идентификаторам
     */
    TeacherDepartmentMerge findByDepartmentIdAndTeacherId(Long dId, Long tId);

    /**
     * Найти связь по идентификатору преподавателя.
     *
     * @param tId Идентификатор преподавателя
     * @return Объект TeacherDepartmentMerge, соответствующий указанным идентификаторам
     */
    Optional<TeacherDepartmentMerge> findByTeacherId(Long tId);
}
