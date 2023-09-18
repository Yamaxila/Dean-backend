package by.vstu.dean.future.models;


import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faculties")
public class FacultyModel extends DBBaseModel {

    private String shortName;
    private String name;
    private String nameGenitive;
    private String nameDative;
    private String rectorName;
    private String dean;
    private String clerkName;
    private String enrollMsgPaid;
    private LocalDate enrollDatePaid;
    private String enrollMsgNotPaid;
    private LocalDate enrollDateNotPaid;
    private Integer journalType;
    private Integer facultyType;
    private Integer semesterStartYear;
    private Integer semesterEndYear;
    private String semester;
    private String moveMsgNumber;
    private LocalDate moveMsgDate;
    private Integer educationType;

}
