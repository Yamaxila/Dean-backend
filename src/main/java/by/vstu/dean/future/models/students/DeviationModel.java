package by.vstu.dean.future.models.students;

import by.vstu.dean.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.future.DBBaseModel;
import com.google.gson.annotations.JsonAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deviations")
public class DeviationModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    private StudentModel student;
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
