package by.vstu.dean.core.dto;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PublicDTO {

    @ReflectionField(value = "id", clazz = DBBaseModel.class)
    private Long id;
    @ReflectionField(value = "status", clazz = DBBaseModel.class)
    private EStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicDTO publicDTO = (PublicDTO) o;
        return Objects.equals(id, publicDTO.id) && status == publicDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
