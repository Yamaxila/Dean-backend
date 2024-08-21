package by.vstu.dean.models;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
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

/**
 * Класс, представляющий объект факультета.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faculties")
@ApiModel(description = "Объект факультета")
public class FacultyModel extends DBBaseModel {

    /**
     * Краткое название факультета.
     */
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

    /**
     * Полное название факультета.
     */
    @ApiModelProperty(notes = "Полное название")
    private String name;

    /**
     * Название факультета в родительном падеже.
     */
    @ApiModelProperty(notes = "Название в родительном падеже")
    private String nameGenitive;

    /**
     * Название факультета в дательном падеже.
     */
    @ApiModelProperty(notes = "Название в дательном падеже")
    private String nameDative;

    /**
     * Ректор факультета.
     */
    @ApiModelProperty(notes = "Ректор")
    private String rectorName;

    /**
     * Декан факультета.
     */
    @ApiModelProperty(notes = "Декан")
    private String dean;

    /**
     * Методист/Секретарь факультета.
     */
    @ApiModelProperty(notes = "Методист/Секретарь")
    private String clerkName;

    /**
     * Приказ о зачислении платников.
     */
    @ApiModelProperty(notes = "Приказ о зачислении платников")
    private String enrollMsgPaid;

    @ApiModelProperty(notes = "Дата зачисления бесплатников")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate enrollDatePaid;

    /**
     * Приказ о зачислении бесплатников.
     */
    @ApiModelProperty(notes = "Приказ о зачислении бесплатников")
    private String enrollMsgNotPaid;

    /**
     * Дата зачисления бесплатников.
     */
    @ApiModelProperty(notes = "Дата зачисления бесплатников")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate enrollDateNotPaid;

    /**
     * Тип журнала (ведется на год, семестр)... со слов Зуевой.
     */
    @ApiModelProperty(notes = "Тип журнала (ведется на год, семестр)... со слов Зуевой...")
    private Integer journalType;

    /**
     * Тип факультета.
     */
    @ApiModelProperty(notes = "Тип факультета")
    private Integer facultyType;

    /**
     * Год начала семестра.
     */
    @ApiModelProperty(notes = "Год начала семестра")
    private Integer semesterStartYear;

    /**
     * Год конца семестра.
     */
    @ApiModelProperty(notes = "Год конца семестра")
    private Integer semesterEndYear;

    /**
     * Семестр.
     */
    @ApiModelProperty(notes = "Семестр")
    private String semester;

    /**
     * Приказ о переводе.
     */
    @ApiModelProperty(notes = "Приказ о переводе")
    private String moveMsgNumber;

    /**
     * Дата приказа о переводе.
     */
    @ApiModelProperty(notes = "Дата приказа о переводе")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate moveMsgDate;

    /**
     * Тип образования (устаревшее поле).
     */
    @ApiModelProperty(notes = "Тип образования")
    @Deprecated
    private Integer educationType;


    @Override
    public String toString() {
        return "FacultyModel{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
