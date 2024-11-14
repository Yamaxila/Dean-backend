package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель объекта преподавателя.
 */
@Entity
@Setter
@Getter
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class V1TeacherModel extends V1DBBaseModel {

    /**
     * Фамилия преподавателя.
     */
    @NotNull
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    private String patronymic;

    /**
     * Должность преподавателя.
     */
    @JoinColumn(name = "degree_id")
    @ManyToOne
    private V1TeacherDegreeModel degree;

    public String toString() {
        return "TeacherModel(surname=" + this.getSurname() + ", name=" + this.getName() + ", patronymic=" + this.getPatronymic() + ", degree=" + this.getDegree() + ")";
    }
}
