package by.vstu.dean.models.hostels;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.models.students.StudentModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "hostel_rooms")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HostelRoomModel extends DBBaseModel {

    @ApiModelProperty(value = "Номер комнаты", example = "302")
    @NotNull
    private int roomNumber;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Тип комнаты", example = "LITTLE")
    @NotNull
    private EHostelRoomType roomType;

    @ApiModelProperty(value = "Этаж", example = "2")
    @NotNull
    private Integer floor;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Общежитие", example = "1")
    @NotNull
    private EHostel hostel;

    @JoinColumn(referencedColumnName = "id", name = "hostel_room_id")
    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(value = "Список студентов в комнате")
//    @JsonIgnore
    private Set<StudentModel> students;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        HostelRoomModel that = (HostelRoomModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
