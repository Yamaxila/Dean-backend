package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import org.springframework.stereotype.Repository;

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
}
