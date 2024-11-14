package by.vstu.migrate.v1.models;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.adapters.V1LocalDateTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class V1FacultyModel extends V1DBBaseModel {

    /**
     * Краткое название факультета.
     */
    private String shortName;

    /**
     * Полное название факультета.
     */
    private String name;

    /**
     * Название факультета в родительном падеже.
     */
    private String nameGenitive;

    /**
     * Название факультета в дательном падеже.
     */
    private String nameDative;

    /**
     * Ректор факультета.
     */
    private String rectorName;

    /**
     * Декан факультета.
     */
    private String dean;

    /**
     * Методист/Секретарь факультета.
     */
    private String clerkName;

    /**
     * Приказ о зачислении платников.
     */
    private String enrollMsgPaid;

    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate enrollDatePaid;

    /**
     * Приказ о зачислении бесплатников.
     */
    private String enrollMsgNotPaid;

    /**
     * Дата зачисления бесплатников.
     */
    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate enrollDateNotPaid;

    /**
     * Тип журнала (ведется на год, семестр)... со слов Зуевой.
     */
    private Integer journalType;

    /**
     * Тип факультета.
     */
    private Integer facultyType;

    /**
     * Год начала семестра.
     */
    private Integer semesterStartYear;

    /**
     * Год конца семестра.
     */
    private Integer semesterEndYear;

    /**
     * Семестр.
     */
    private String semester;

    /**
     * Приказ о переводе.
     */
    private String moveMsgNumber;

    /**
     * Дата приказа о переводе.
     */
    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate moveMsgDate;

    /**
     * Тип образования (устаревшее поле).
     */
    @Deprecated
    private Integer educationType;

}
