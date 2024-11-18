package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.merge.V1TeacherDepartmentMerge;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с связующими сущностями Преодаватель-Кафедра.
 */
@Repository
public interface V1TeacherDepartmentMergeRepository extends V1DBBaseModelRepository<V1TeacherDepartmentMerge> {

    /**
     * Найти связь по идентификаторам кафедры и преподавателя.
     *
     * @param dId Идентификатор кафедры
     * @param tId Идентификатор преподавателя
     * @return Объект TeacherDepartmentMerge, соответствующий указанным идентификаторам
     */
    V1TeacherDepartmentMerge findByDepartmentIdAndTeacherId(Long dId, Long tId);

//    long deleteBySourceId(Long sourceId);

    /**
     * Найти связь по идентификатору преподавателя.
     *
     * @param tId Идентификатор преподавателя
     * @return Объект TeacherDepartmentMerge, соответствующий указанным идентификаторам
     */
    Optional<V1TeacherDepartmentMerge> findByTeacherId(Long tId);
}
