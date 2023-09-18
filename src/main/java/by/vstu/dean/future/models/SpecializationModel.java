package by.vstu.dean.future.models;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specializations")
public class SpecializationModel extends DBBaseModel {

    private String name;
    private String shortName;
    private String spezCode;

    @JoinColumn(name = "spec_id")
    @ManyToOne
    private SpecialityModel spec;

    @JoinColumn(name = "qualification_id")
    @ManyToOne
    private QualificationModel qualification;

}
