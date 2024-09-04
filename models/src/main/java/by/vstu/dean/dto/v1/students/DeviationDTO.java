package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.DeviationModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link DeviationModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Отклонения")
public class DeviationDTO extends BaseDTO {
}
