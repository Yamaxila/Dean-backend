package by.vstu.dean.models.students.internal;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@Table(name = "addresses")
@Schema(title = "Модель адреса")
@AllArgsConstructor
public class AddressModel extends DBBaseModel {

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @Schema(title = "Адрес")
    @Deprecated
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    @Schema(title = "Страна")
    private String country;

    /**
     * Почтовый индекс адреса студента.
     */
    @Schema(title = "Почтовый индекс")
    private String postIndex;

    /**
     * Область адреса студента.
     */
    @Schema(title = "Область")
    private String state;

    /**
     * Район адреса студента.
     */
    @Schema(title = "Район")
    private String region;

    /**
     * Город адреса студента.
     */
    @Schema(title = "Город")
    private String city;

    /**
     * Улица адреса студента.
     */
    @Schema(title = "Улица")
    private String street;

    /**
     * Дом адреса студента.
     */
    @Schema(title = "Дом")
    private String house;

    /**
     * Корпус адреса студента.
     */
    @Schema(title = "Корпус")
    private String housePart;

    /**
     * Квартира адреса студента.
     */
    @Schema(title = "Квартира")
    private String flat;

}
