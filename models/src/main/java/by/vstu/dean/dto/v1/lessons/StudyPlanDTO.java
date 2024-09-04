package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.StudyPlanModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link StudyPlanModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Учебного плана")
public class StudyPlanDTO extends BaseDTO {
}
