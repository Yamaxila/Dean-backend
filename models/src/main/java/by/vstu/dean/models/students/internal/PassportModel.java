package by.vstu.dean.models.students.internal;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EPassportType;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dean_passports")
@Schema(title = "Модель паспорта/документа студента")
public class PassportModel extends DBBaseModel {

    @Schema(title = "Тип документа")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private EPassportType passportType;
    @Schema(title = "Серия документа")
    @NotNull
    private String passportSerial;
    @Schema(title = "Номер документа")
    @NotNull
    private String passportNumber;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата выдачи")
    @NotNull
    private LocalDate passportIssueDate;
    @Schema(title = "Кем выдан")
    @NotNull
    private String passportIssueString;
    @Schema(title = "ID")
    @NotNull
    private String passportId;

}
