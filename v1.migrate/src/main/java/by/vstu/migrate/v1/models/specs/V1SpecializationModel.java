package by.vstu.migrate.v1.models.specs;

import by.vstu.migrate.v1.V1DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель объекта специализации.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specializations")
public class V1SpecializationModel extends V1DBBaseModel {

    /**
     * Название специализации.
     */
    private String name;

    /**
     * Краткое название специализации.
     */
    private String shortName;

    /**
     * Код специализации.
     */
    private String spezCode;

    /**
     * Специальность.
     */
    @JoinColumn(name = "spec_id")
    @ManyToOne
    @JsonIgnore
    private V1SpecialityModel spec;

    /**
     * Квалификация.
     */
    @JoinColumn(name = "qualification_id")
    @ManyToOne
    private V1QualificationModel qualification;

}
