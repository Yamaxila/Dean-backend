package by.vstu.dean.models.rooms;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EClassroomType;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.models.lessons.DepartmentModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "rooms")
@Setter
@Getter
public class ClassroomModel extends DBBaseModel {

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private EFrame frame;
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private EClassroomType roomType;
    @JoinColumn(name = "department_id")
    @ManyToOne
    private DepartmentModel department;
    @NotNull
    private String roomNumber;
    private Integer seatsNumber;
    private Double square;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ClassroomModel that = (ClassroomModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
