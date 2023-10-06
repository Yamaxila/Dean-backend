package by.vstu.dean.future.models;


import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.future.DBBaseModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faculties")
@ApiModel(description = "Объект факультета")
public class FacultyModel extends DBBaseModel {

    @ApiModelProperty(notes = "Краткое название")
    private String shortName;
    @ApiModelProperty(notes = "Полное название")
    private String name;
    @ApiModelProperty(notes = "Название в родительном падеже")
    private String nameGenitive;
    @ApiModelProperty(notes = "Название в дательном падеже")
    private String nameDative;
    @ApiModelProperty(notes = "Ректор")
    private String rectorName;
    @ApiModelProperty(notes = "Декан")
    private String dean;
    @ApiModelProperty(notes = "Методист/Секретарь")
    private String clerkName;
    @ApiModelProperty(notes = "Приказ о зачислении платников")
    private String enrollMsgPaid;
    @ApiModelProperty(notes = "Дата зачисления платников")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate enrollDatePaid;
    @ApiModelProperty(notes = "Приказ о зачислении бесплатников")
    private String enrollMsgNotPaid;
    @ApiModelProperty(notes = "Дата зачисления бесплатников")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate enrollDateNotPaid;
    @ApiModelProperty(notes = "Тип журнала (ведется на год, семестр)... со слов Зуевой...")
    private Integer journalType;
    @ApiModelProperty(notes = "Тип факультета")
    private Integer facultyType;
    @ApiModelProperty(notes = "Год начала семестра")
    private Integer semesterStartYear;
    @ApiModelProperty(notes = "Год конца семестра")
    private Integer semesterEndYear;
    @ApiModelProperty(notes = "Семестр")
    private String semester;
    @ApiModelProperty(notes = "Приказ о переводе")
    private String moveMsgNumber;
    @ApiModelProperty(notes = "Дата приказа о переводе")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate moveMsgDate;
    @ApiModelProperty(notes = "Тип образования")
    @Deprecated
    private Integer educationType;

}
