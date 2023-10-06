package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "citizenhips")
@ApiModel(description = "Объект гражданства")
public class CitizenshipModel extends DBBaseModel {

    @ApiModelProperty(notes = "Страна, название")
    private String name;

}
