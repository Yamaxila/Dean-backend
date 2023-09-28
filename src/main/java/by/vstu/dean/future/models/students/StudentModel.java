package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.specs.SpecializationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class StudentModel extends DBBaseModel {

    private String lastName, firstName, secondName;

    private Integer sex;

    private Integer cityType;
    private String address, addressCountry, addressIndex, addressState
            , addressRegion, addressCity, addressStreet
            , addressHouse, addressHousePart, addressFlat;
    private String phone;
    private boolean cityIsVillage;

    @JoinColumn(name = "last_document_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private DocumentModel lastDocument;

    @JoinColumn(name = "last_deviation_id")
    @ManyToOne
    private DeviationModel lastDeviation;

    @JoinColumn(name = "spez_id")
    @ManyToOne
    private SpecializationModel specialization;

    @JoinColumn(name = "group_id")
    @ManyToOne
    private GroupModel group;
}
