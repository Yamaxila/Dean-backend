package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.specs.SpecializationModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@ApiModel(description = "Объект студента")
public class StudentModel extends DBBaseModel {

    @NotNull
    @ApiModelProperty(notes = "")
    private String lastName;
    @NotNull
    @ApiModelProperty(notes = "")
    private String firstName;
    @NotNull
    @ApiModelProperty(notes = "")
    private String secondName;
    @ApiModelProperty(notes = "")
    private Integer sex;
    @ApiModelProperty(notes = "")
    private Integer cityType;
    @ApiModelProperty(notes = "")
    private String address;
    @ApiModelProperty(notes = "")
    private String addressCountry;
    @ApiModelProperty(notes = "")
    private String addressIndex;
    @ApiModelProperty(notes = "")
    private String addressState;
    @ApiModelProperty(notes = "")
    private String addressRegion;
    @ApiModelProperty(notes = "")
    private String addressCity;
    @ApiModelProperty(notes = "")
    private String addressStreet;
    @ApiModelProperty(notes = "")
    private String addressHouse;
    @ApiModelProperty(notes = "")
    private String addressHousePart;
    @ApiModelProperty(notes = "")
    private String addressFlat;
    @ApiModelProperty(notes = "")
    private String phone;
    @ApiModelProperty(notes = "")
    private boolean cityIsVillage;

    @JoinColumn(name = "last_document_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "Последний документ")
    private DocumentModel lastDocument;

    @JoinColumn(name = "last_deviation_id")
    @ManyToOne
    @ApiModelProperty(notes = "Последнее отклонение")
    private DeviationModel lastDeviation;

    @JoinColumn(name = "spez_id")
    @ManyToOne
    @ApiModelProperty(notes = "Специализация")
    private SpecializationModel specialization;

    @JoinColumn(name = "group_id")
    @ManyToOne
    @NotNull
    @ApiModelProperty(notes = "Группа")
    private GroupModel group;
}
