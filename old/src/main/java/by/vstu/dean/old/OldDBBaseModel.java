package by.vstu.dean.old;


import by.vstu.dean.PreventAnyUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class, PreventAnyUpdate.class})
public class OldDBBaseModel {
    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
