package by.vstu.dean.future.models;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private LocalDateTime msgDate;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String lastNameNew;
    private String groupNameNew;
    private String msg1;
    private String msg2;
    private String commandMsg1;
    private String commandMsg;
    private LocalDateTime dateLiquidation;
    private Integer penalty;

}
