package by.vstu.dean.timetable.repo;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.timetable.enums.ESubGroup;
import by.vstu.dean.timetable.enums.EWeekType;
import by.vstu.dean.timetable.models.LessonModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface LessonModelRepository extends DBBaseModelRepository<LessonModel> {
    List<LessonModel> findAllByRoom_Id(Long roomId);

    List<LessonModel> findByGroup_IdAndDiscipline_IdAndTeacher_IdAndStatus(long groupId, long disciplineId, long teacherId, EStatus eStatus);

    List<LessonModel> findByDayAndStatus(short day, EStatus eStatus);

    List<LessonModel> findByTeacherIdAndStatus(long teacherId, EStatus status);

    List<LessonModel> findByRoomIdAndStatus(long roomId, EStatus status);

    List<LessonModel> findByRoom_FrameAndRoom_RoomNumberInAndWeekTypeInAndStatus(@NotNull EFrame frame, Collection<String> roomNumbers, Collection<EWeekType> weekTypes, EStatus status);

    @Query("select l from LessonModel l where l.status = :status and ((l.startDate between :date1 and :date2) or (l.endDate between :date1 and :date2))")
    List<LessonModel> findByStatusAndBetweenDates(EStatus status, LocalDate date1, LocalDate date2);

    @Query("""
            select l from LessonModel l
            where l.group.id = ?1 and l.status = ?2 and l.startDate between ?3 and ?4 and l.endDate between ?3 and ?4""")
    List<LessonModel> findByGroupIdAndStatusAndStartDateBetweenAndEndDateBetween(Long groupId, EStatus eStatus, LocalDate dateFrom, LocalDate dateTo);

    List<LessonModel> findByStatusAndVisible(EStatus eStatus, boolean b);

    @Query("select l from LessonModel l where l.status = :status and ((l.startDate between :dateFrom and :dateTo) or (l.endDate between :dateFrom and :dateTo))")
    List<LessonModel> findByStatusAndDate(EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select l from LessonModel l where l.status = :status and l.group in (:groupsIds) and l.status = :status and\s
            ((l.startDate between :dateFrom and :dateTo) or (l.endDate between :dateFrom and :dateTo))""")
    List<LessonModel> findByGroupIdInAndStatusAndBetweenDates(long[] groupsIds, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select l from LessonModel l where l.group.id = :groupId and l.day = :day and l.lessonNumber = :lessonNumber and\s
            l.status = :status and ((l.startDate between :dateFrom and :dateTo) or (l.endDate between :dateFrom and :dateTo))""")
    List<LessonModel> findByGroupIdAndDayAndLessonNumberAndStatusAndBetweenDates(long groupId, short day, short lessonNumber, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select l from LessonModel l where l.group.id = :groupId and l.day = :day and l.weekType = :weekType and\s
            l.lessonNumber = :lessonNumber and l.subGroup = :subGroup and l.status = :status and\s
            ((l.startDate between :dateFrom and :dateTo) or (l.endDate between :dateFrom and :dateTo))""")
    List<LessonModel> findByGroupIdAndDayAndWeekTypeAndLessonNumberAndSubGroupAndStatusAndDateBetween(long groupId, short day, EWeekType weekType, short lessonNumber, ESubGroup subGroup, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select count(l) > 0 from LessonModel l where l.room.id = :roomId and l.teacher.id = :teacherId and l.discipline.id = :disciplineId and\s
            l.group.id = :groupId and l.subGroup = :subGroup and l.day = :day and l.lessonNumber = :lessonNumber and l.lessonType = :lessonType and\s
            l.weekType = :weekType and l.status = :status and ((l.startDate between :startDate and :endDate) or (l.endDate between :startDate and :endDate))""")
    boolean existsByRoomIdAndTeacherIdAndDisciplineIdAndGroupIdAndSubGroupAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndStatusAndStartDateAndEndDate(long roomId, long teacherId, long disciplineId, long groupId, ESubGroup subGroup, short day, short lessonNumber, ELessonType lessonType, EWeekType weekType, EStatus status, LocalDate startDate, LocalDate endDate);

    @Query("""
            select count(l) > 0 from LessonModel l where l.room.id = :roomId and l.teacher.id = :teacherId and l.discipline.id = :disciplineId and\s
            l.group.id = :groupId and l.subGroup = :subGroup and l.day = :day and l.lessonNumber = :lessonNumber and l.lessonType = :lessonType and\s
            l.weekType = :weekType and l.status = :status and ((l.startDate between :startDate and :endDate) or (l.endDate between :startDate and :endDate)) and\s
            l.id <> :lessonId""")
    boolean existsByRoomIdAndTeacherIdAndDisciplineIdAndGroupIdAndSubGroupAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndStatusAndStartDateAndEndDateAndLessonIdNot(long roomId, long teacherId, long disciplineId, long groupId, ESubGroup subGroup, short day, short lessonNumber, ELessonType lessonType, EWeekType weekType, EStatus status, LocalDate startDate, LocalDate endDate, long lessonId);

    @Query("""
            select count(l) > 0 from LessonModel l where l.room.id = :roomId and l.lessonNumber = :lessonNumber and l.day = :day and\s
            l.status = :status and ((:dateFrom between l.startDate and l.endDate) or (:dateTo between l.startDate and l.endDate))""")
    boolean existsByRoomIdAndLessonNumberAndDayAndStatusAndBetweenDates(long roomId, short lessonNumber, short day, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select count(l) > 0 from LessonModel l where l.teacher.id = :teacherId and l.lessonNumber = :lessonNumber and l.day = :day and\s
            l.status = :status and ((:dateFrom between l.startDate and l.endDate) or (:dateTo between l.startDate and l.endDate))""")
    boolean existsByTeacherIdAndLessonNumberAndDayAndStatusAndBetweenDates(long teacherId, short lessonNumber, short day, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select count(l) > 0 from LessonModel l where l.group.id = :groupId and l.subGroup = :subGroup and\s
            l.lessonNumber = :lessonNumber and l.day = :day and l.status = :status and\s
            ((:dateFrom between l.startDate and l.endDate) or (:dateTo between l.startDate and l.endDate))""")
    boolean existsByGroupIdAndSubGroupAndLessonNumberAndDayAndStatusAndBetweenDates(long groupId, ESubGroup subGroup, short lessonNumber, short day, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select count(l) > 0 from LessonModel l where l.room.id = :roomId and l.lessonNumber = :lessonNumber and l.day = :day and\s
            l.weekType = :weekType and l.status = :status and\s
            ((:dateFrom between l.startDate and l.endDate) or (:dateTo between l.startDate and l.endDate))""")
    boolean existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long roomId, short lessonNumber, short day, EWeekType weekType, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select count(l) > 0 from LessonModel l where l.teacher.id = :teacherId and l.lessonNumber = :lessonNumber and\s
            l.day = :day and l.weekType = :weekType and l.status = :status and\s
            ((:dateFrom between l.startDate and l.endDate) or (:dateTo between l.startDate and l.endDate))""")
    boolean existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long teacherId, short lessonNumber, short day, EWeekType weekType, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select count(l) > 0 from LessonModel l where l.group.id = :groupId and l.subGroup = :subGroup and\s
            l.lessonNumber = :lessonNumber and l.day = :day and l.weekType = :weekType and l.status = :status and\s
            ((:dateFrom between l.startDate and l.endDate) or (:dateTo between l.startDate and l.endDate))""")
    boolean existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long groupId, ESubGroup subGroup, short lessonNumber, short day, EWeekType weekType, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select l from LessonModel l where l.room.id = :roomId and l.day = :day and l.lessonNumber = :lessonNumber and\s
            l.status = :status and ((l.startDate between :dateFrom and :dateTo) or (l.endDate between :dateFrom and :dateTo))""")
    List<LessonModel> findByRoomIdAndDayAndLessonNumberAndStatusAndDateBetween(Long roomId, Short day, Short lessonNumber, EStatus status, LocalDate dateFrom, LocalDate dateTo);

    @Query("""
            select l from LessonModel l where l.group.id = :groupId and l.day = :day and l.lessonNumber = :lessonNumber and\s
            l.subGroup = :subGroup and l.status = :status and l.startDate = :date and l.endDate = :date""")
    List<LessonModel> findByGroupIdAndDayAndLessonNumberAndSubGroupAndDateAndStatus(long groupId, short day, short lessonNumber, ESubGroup subGroup, LocalDate date, EStatus status);

}