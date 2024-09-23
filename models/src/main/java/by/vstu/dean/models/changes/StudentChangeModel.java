package by.vstu.dean.models.changes;

import by.vstu.dean.core.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ESCMessageType;
import by.vstu.dean.models.students.StudentModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_changes")
@Schema(title = "Модель изменений студента")
public class StudentChangeModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @Schema(title = "Студент")
    private StudentModel student;
    private ESCMessageType studentChangeType;
    @JoinColumn(name = "reason_id")
    @ManyToOne
    @Schema(title = "Причина изменения")
    private ReasonModel reason;
    private String orderNumber;
    private String orderText;
    @Size(max = 2048)
    private String msg;
    @Schema(title = "Дата сообщения")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime msgDate;
    @JoinColumn(referencedColumnName = "id", name = "change_id")
    @OneToMany(cascade = CascadeType.MERGE)
    private List<ChangeModel> changes;

    public void addChange(ChangeModel change) {
        if (changes == null)
            changes = new ArrayList<>();
        change.setChange(this);
        changes.add(change);
    }

    public void setChanges(List<ChangeModel> changes) {
        changes.forEach(change -> change.setChange(this));
        this.changes = changes;
    }

}
