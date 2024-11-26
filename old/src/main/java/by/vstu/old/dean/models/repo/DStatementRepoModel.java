package by.vstu.old.dean.models.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DStatementRepoModel {
    private Long facultyId;
    private Long studyPlanId;
    private Integer globalStatementNumber;
}
