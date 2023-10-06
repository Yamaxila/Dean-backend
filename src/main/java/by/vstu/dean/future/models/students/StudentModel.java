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
    @ApiModelProperty(notes = "Фамилия")
    private String lastName;
    @NotNull
    @ApiModelProperty(notes = "Имя")
    private String firstName;
    @NotNull
    @ApiModelProperty(notes = "Отчество")
    private String secondName;
    @ApiModelProperty(notes = "Пол")
    private Integer sex;
    @ApiModelProperty(notes = "Тип города")
    private Integer cityType;
    @ApiModelProperty(notes = "Адрес")
    @Deprecated
    private String address;
    @ApiModelProperty(notes = "Страна")
    private String addressCountry;
    @ApiModelProperty(notes = "Почтовый индекс")
    private String addressIndex;
    @ApiModelProperty(notes = "Область")
    private String addressState;
    @ApiModelProperty(notes = "Район")
    private String addressRegion;
    @ApiModelProperty(notes = "Город")
    private String addressCity;
    @ApiModelProperty(notes = "Улица")
    private String addressStreet;
    @ApiModelProperty(notes = "Дом")
    private String addressHouse;
    @ApiModelProperty(notes = "Корпус")
    private String addressHousePart;
    @ApiModelProperty(notes = "Квартира")
    private String addressFlat;
    @ApiModelProperty(notes = "Телефон")
    private String phone;
    @ApiModelProperty(notes = "Является ли город - деревней")
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
