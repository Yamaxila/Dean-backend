package by.vstu.photo.dean.models;


import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "Pass_dat", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "stud_id", nullable = false))
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PhotoDataModel extends OldDBBaseModel {

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "foto")
    private byte[] photoBytes;

}
