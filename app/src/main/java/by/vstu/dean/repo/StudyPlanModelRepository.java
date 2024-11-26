package by.vstu.dean.repo;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.models.lessons.TeacherModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория сущности учебных планов.
 */
@Repository
public interface StudyPlanModelRepository extends DBBaseModelRepository<StudyPlanModel> {

    @NotNull
    List<StudyPlanModel> findAllDistinctByGroupId(Long groupId);

    @Query("select s.group.faculty.sourceId from StudyPlanModel s where s.id = :id")
    Long findFacultySourceId(Long id);

    @Query("select s.teacher from StudyPlanModel s where s.id = :id")
    Optional<TeacherModel> findTeacherByStudyPlanId(Long id);

    /**
     * Найти id деканата всех учебных планов по статусу.
     *
     * @return Список идентификаторов источников.
     */
    @Query("select s.sourceId from StudyPlanModel s where s.status = :status")
    List<Long> findAllSourceIdsByStatus(EStatus status);

    /**
     * Найти id деканата всех учебных планов.
     *
     * @return Список идентификаторов источников.
     */
    @Query("select s.sourceId from StudyPlanModel s")
    List<Long> findAllSourceIds();
}