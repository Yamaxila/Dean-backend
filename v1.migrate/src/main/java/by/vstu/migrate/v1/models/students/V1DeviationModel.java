package by.vstu.migrate.v1.models.students;

import by.vstu.dean.core.adapters.LocalDateTimeTypeAdapter;
import by.vstu.migrate.v1.V1DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.JsonAdapter;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deviations")
@Deprecated
public class V1DeviationModel extends V1DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    private V1StudentModel student;
    private String msgType;
    private String expelled;
    private Integer reasonNumber;
    private String deviationMsg;
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime msgDate;
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateStart;
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateEnd;
    private String lastNameNew;
    private String groupNameNew;
    private String msg1;
    private String msg2;
    private String commandMsg1;
    private String commandMsg;
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateLiquidation;
    private Integer penalty;

}
