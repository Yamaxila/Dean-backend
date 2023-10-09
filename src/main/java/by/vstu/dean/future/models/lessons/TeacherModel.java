package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Модель объекта преподавателя.
 */
@Entity
@Setter
@Getter
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект преподавателя")
public class TeacherModel extends DBBaseModel {

    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия преподавателя")
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя преподавателя")
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество преподавателя")
    private String patronymic;

    /**
     * Должность преподавателя.
     */
    @JoinColumn(name = "degree_id")
    @ManyToOne
    @ApiModelProperty(notes = "Должность преподавателя")
    private TeacherDegreeModel degree;

    public String toString() {
        return "TeacherModel(surname=" + this.getSurname() + ", name=" + this.getName() + ", patronymic=" + this.getPatronymic() + ", degree=" + this.getDegree() + ")";
    }
}
