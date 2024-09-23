package by.vstu.dean.models.students.internal;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EMobileOperatorType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phones")
@Schema(title = "Модель номера телефона")
public class PhoneModel extends DBBaseModel {

    @Enumerated(EnumType.ORDINAL)
    private EMobileOperatorType type;

    private String phone;
}
