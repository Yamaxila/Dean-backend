package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;

public interface TeacherDepartmentMergeRepository extends DBBaseModelRepository<TeacherDepartmentMerge> {

    TeacherDepartmentMerge findByDepartmentIdAndTeacherId(Long dId, Long tId);

}