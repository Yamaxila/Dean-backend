package by.vstu.migrate.timetable.repo;

import by.vstu.migrate.timetable.models.V1LessonModel;
import by.vstu.migrate.v1.V1DBBaseModelRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface V1LessonModelRepository extends V1DBBaseModelRepository<V1LessonModel> {
//    List<V1LessonModel> findAllByRoomId(Long roomId);
//
//    List<V1LessonModel> findByStatus(V1EStatus status);
//
//    List<V1LessonModel> findByGroupIdAndStatus(Long groupId, V1EStatus status);
//
//    @Query("""
//            select l from V1LessonModel l
//            where l.groupId = ?1 and l.status = ?2 and l.startDate between ?3 and ?4 and l.endDate between ?3 and ?4""")
//    List<V1LessonModel> findByGroupIdAndStatusAndStartDateBetweenAndEndDateBetween(Long groupId, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT * FROM lessons WHERE group_id = :groupId AND status = :#{#status?.ordinal()} AND " +
//            "start_date = :date and end_date = :date", nativeQuery = true)
//    List<V1LessonModel> findByGroupIdAndStatusAndDate(long groupId, V1EStatus status, LocalDate date);
//
////    boolean existsByRoomIdAndTeacherIdAndWeekTypeAndDayAndLessonNumberAndLessonTypeAndStatusAndIdNot(long roomId, long teacherId, EWeekType weekType, short day, short lessonNumber, ELessonType lessonType, V1EStatus status, Long id);
//
//    //    boolean existsByRoomIdAndLessonNumberAndDayAndStatusAndIdNot(long roomId, short lessonNumber, short day, V1EStatus status, Long id);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "room_id = :roomId AND lesson_number = :lessonNumber AND day = :day AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByRoomIdAndLessonNumberAndDayAndStatusAndBetweenDates(long roomId, short lessonNumber, short day, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
////    boolean existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndIdNot(long roomId, short lessonNumber, short day, EWeekType weekType, V1EStatus status, Long id);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "room_id = :roomId AND lesson_number = :lessonNumber AND day = :day AND week_type = :#{#weekType?.ordinal()} AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long roomId, short lessonNumber, short day, V1EWeekType weekType, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
////    boolean existsByTeacherIdAndLessonNumberAndDayAndStatusAndIdNot(long teacherId, short lessonNumber, short day, V1EStatus status, Long id);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "teacher_id = :teacherId AND lesson_number = :lessonNumber AND day = :day AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByTeacherIdAndLessonNumberAndDayAndStatusAndBetweenDates(long teacherId, short lessonNumber, short day, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
////    boolean existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndIdNot(long teacherId, short lessonNumber, short day, EWeekType weekType, V1EStatus status, Long id);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "teacher_id = :teacherId AND lesson_number = :lessonNumber AND day = :day AND week_type = :#{#weekType?.ordinal()} AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long teacherId, short lessonNumber, short day, V1EWeekType weekType, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "group_id = :groupId AND sub_group = :#{#subGroup?.ordinal()} AND lesson_number = :lessonNumber AND day = :day AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByGroupIdAndSubGroupAndLessonNumberAndDayAndStatusAndBetweenDates(long groupId, V1ESubGroup subGroup, short lessonNumber, short day, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "group_id = :groupId AND lesson_number = :lessonNumber AND day = :day AND week_type = :#{#weekType?.ordinal()} AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByGroupIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long groupId, short lessonNumber, short day, V1EWeekType weekType, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l WHERE " +
//            "group_id = :groupId AND sub_group = :#{#subGroup?.ordinal()} AND lesson_number = :lessonNumber AND day = :day AND week_type = :#{#weekType?.ordinal()} AND status = :#{#status?.ordinal()} AND " +
//            "((:dateFrom BETWEEN start_date AND end_date) OR (:dateTo BETWEEN start_date AND end_date))", nativeQuery = true)
//    boolean existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(long groupId, V1ESubGroup subGroup, short lessonNumber, short day, V1EWeekType weekType, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
////    boolean existsByTeacherIdAndDisciplineIdAndWeekTypeAndDayAndLessonNumberAndLessonTypeAndStatusAndRoomIdNotAndIdNot(long teacherId, long disciplineId, EWeekType weekType, short day, short lessonNumber, ELessonType lessonType, V1EStatus status, long roomId, Long id);
////
////    List<V1LessonModel> findByTeacherIdAndDisciplineIdAndGroupIdAndDayAndWeekTypeAndLessonNumberAndLessonTypeAndSubGroupAndStatus(long teacherId, long disciplineId, long groupId, short day, EWeekType weekType, short lessonNumber, ELessonType lessonType, ESubGroup subGroup, V1EStatus status);
////
////    List<V1LessonModel> findByGroupIdAndTeacherIdAndDisciplineIdAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndSubGroupAndStatus(long groupId, long teacherId, long disciplineId, short day, short lessonNumber, ELessonType lessonType, EWeekType weekType, ESubGroup subGroup, V1EStatus status);
////
////    List<V1LessonModel> findByGroupIdAndTeacherIdAndDisciplineIdAndRoomIdAndDayAndLessonNumberAndWeekTypeAndSubGroupAndStatus(long groupId, long teacherId, long disciplineId, long roomId, short day, short lessonNumber, EWeekType weekType, ESubGroup subGroup, V1EStatus status);
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l " +
//            "WHERE room_id = :roomId AND teacher_id = :teacherId AND discipline_id = :disciplineId " +
//            "AND group_id = :groupId AND sub_group = :#{#subGroup?.ordinal()} AND day = :day " +
//            "AND lesson_number = :lessonNumber AND lesson_type = :#{#lessonType?.ordinal()} AND week_type = :#{#weekType?.ordinal()} " +
//            "AND status = :#{#status?.ordinal()} AND ((start_date BETWEEN :startDate AND :endDate) OR (end_date BETWEEN :startDate AND :endDate))", nativeQuery = true)
//    boolean existsByRoomIdAndTeacherIdAndDisciplineIdAndGroupIdAndSubGroupAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndStatusAndStartDateAndEndDate(long roomId, long teacherId, long disciplineId, long groupId, V1ESubGroup subGroup, short day, short lessonNumber, V1ELessonTypes lessonType, V1EWeekType weekType, V1EStatus status, LocalDate startDate, LocalDate endDate);
//
//
//    @Query(value = "SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM lessons l " +
//            "WHERE room_id = :roomId AND teacher_id = :teacherId AND discipline_id = :disciplineId " +
//            "AND group_id = :groupId AND sub_group = :#{#subGroup?.ordinal()} AND day = :day AND id <> :lessonId " +
//            "AND lesson_number = :lessonNumber AND lesson_type = :#{#lessonType?.ordinal()} AND week_type = :#{#weekType?.ordinal()} " +
//            "AND status = :#{#status?.ordinal()} AND ((start_date BETWEEN :startDate AND :endDate) OR (end_date BETWEEN :startDate AND :endDate))", nativeQuery = true)
//    boolean existsByRoomIdAndTeacherIdAndDisciplineIdAndGroupIdAndSubGroupAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndStatusAndStartDateAndEndDateAndLessonIdNot(long roomId, long teacherId, long disciplineId, long groupId, by.vstu.timetable.enums.V1ESubGroup subGroup, short day, short lessonNumber, by.vstu.timetable.enums.V1ELessonTypes lessonType, by.vstu.timetable.enums.V1EWeekType weekType, V1EStatus status, LocalDate startDate, LocalDate endDate, long lessonId);
//
//    List<V1LessonModel> findByGroupIdAndDayAndWeekTypeAndLessonNumberAndStatus(long groupId, short day, by.vstu.timetable.enums.V1EWeekType weekType, short lessonNumber, V1EStatus status);
//
//    @Query(value = "SELECT * FROM lessons WHERE group_id = :groupId AND day = :day AND week_type = :#{#weekType?.ordinal()} AND " +
//            "lesson_number = :lessonNumber AND sub_group = :#{#subGroup?.ordinal()} AND status = :#{#status?.ordinal()} AND " +
//            "((start_date BETWEEN :dateFrom AND :dateTo) OR (end_date BETWEEN :dateFrom AND :dateTo))", nativeQuery = true)
//    List<V1LessonModel> findByGroupIdAndDayAndWeekTypeAndLessonNumberAndSubGroupAndStatusAndDateBetween(long groupId, short day, by.vstu.timetable.enums.V1EWeekType weekType, short lessonNumber, by.vstu.timetable.enums.V1ESubGroup subGroup, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT * FROM lessons WHERE group_id = :groupId AND day = :day AND lesson_number = :lessonNumber AND status = :#{#status?.ordinal()} AND " +
//            "((start_date BETWEEN :dateFrom AND :dateTo) OR (end_date BETWEEN :dateFrom AND :dateTo))", nativeQuery = true)
//    List<V1LessonModel> findByGroupIdAndDayAndLessonNumberAndStatusAndBetweenDates(long groupId, short day, short lessonNumber, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT * FROM lessons WHERE group_id = :groupId AND day = :day AND lesson_number = :lessonNumber AND sub_group = :#{#subGroup?.ordinal()} AND " +
//            "start_date = :date AND end_date = :date AND status = :#{#status?.ordinal()}", nativeQuery = true)
//    List<V1LessonModel> findByGroupIdAndDayAndLessonNumberAndSubGroupAndDateAndStatus(long groupId, short day, short lessonNumber, by.vstu.timetable.enums.V1ESubGroup subGroup, LocalDate date, V1EStatus status);
//
//    List<V1LessonModel> findByGroupIdAndDisciplineIdAndTeacherIdAndStatus(long groupId, long disciplineId, long teacherId, V1EStatus status);
//
//    List<V1LessonModel> findByDayAndStatus(short day, V1EStatus status);
//
//    @Query(value = "SELECT * FROM lessons WHERE status = :#{#status?.ordinal()} AND ((start_date BETWEEN :date1 AND :date2) OR (end_date BETWEEN :date1 AND :date2))", nativeQuery = true)
//    List<V1LessonModel> findByStatusAndDate(V1EStatus status, LocalDate date1, LocalDate date2);
//
//    @Query(value = "SELECT * FROM lessons WHERE status = :#{#status?.ordinal()} AND ((start_date BETWEEN :date1 AND :date2) OR (end_date BETWEEN :date1 AND :date2))", nativeQuery = true)
//    List<V1LessonModel> findByStatusAndBetweenDates(V1EStatus status, LocalDate date1, LocalDate date2);
//
//    @Query(value = "SELECT * FROM lessons WHERE teacher_id = :teacherId AND status = :#{#status?.ordinal()} AND ((:date1 BETWEEN start_date AND end_date) OR (:date2 BETWEEN start_date AND end_date))", nativeQuery = true)
//    List<V1LessonModel> findByTeacherIdAndStatusAndDate(long teacherId, V1EStatus status, LocalDate date1, LocalDate date2);
//
//    List<V1LessonModel> findByTeacherIdAndStatus(long teacherId, V1EStatus status);
//
//    List<V1LessonModel> findByRoomIdAndStatus(long roomId, V1EStatus status);
//
//    @Query(value = "SELECT * FROM lessons WHERE status = :#{#status?.ordinal()} AND group_id IN (:groupsIds) AND " +
//            "((start_date BETWEEN :dateFrom AND :dateTo) OR (end_date BETWEEN :dateFrom AND :dateTo))", nativeQuery = true)
//    List<V1LessonModel> findByGroupIdInAndStatusAndBetweenDates(long[] groupsIds, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT * FROM lessons WHERE group_id = :groupId AND status = :#{#status?.ordinal()} AND" +
//            "((start_date BETWEEN :dateFrom AND :dateTo) OR (end_date BETWEEN :dateFrom AND :dateTo))", nativeQuery = true)
//    List<V1LessonModel> findByGroupIdAndStatusAndBetweenDates(Long groupId, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    @Query(value = "SELECT * FROM lessons WHERE status = :#{#status?.ordinal()} AND week_type IN (:weekType) AND ((start_date BETWEEN :startDate AND :endDate) OR (end_date BETWEEN :startDate AND :endDate)) AND room_id IN :roomId", nativeQuery = true)
//    List<V1LessonModel> findByStatusAndRoomAndWeekTypeAndDate(V1EStatus status, Long[] roomId, Integer[] weekType, LocalDate startDate, LocalDate endDate);
//
//    List<V1LessonModel> findByRoomIdInAndWeekTypeInAndStatus(Collection<Long> roomIds, Collection<by.vstu.timetable.enums.V1EWeekType> weekTypes, V1EStatus status);
//
//    @Query(value = "SELECT * FROM lessons WHERE room_id = :roomId AND day = :day AND lesson_number = :lessonNumber AND status = :#{#status?.ordinal()} AND " +
//            "((start_date BETWEEN :dateFrom AND :dateTo) OR (end_date BETWEEN :dateFrom AND :dateTo))", nativeQuery = true)
//    List<V1LessonModel> findByRoomIdAndDayAndLessonNumberAndStatusAndDateBetween(Long roomId, Short day, Short lessonNumber, V1EStatus status, LocalDate dateFrom, LocalDate dateTo);
//
//    List<V1LessonModel> findByStatusAndVisible(V1EStatus status, boolean visible);
}