package by.vstu.dean.core.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее статусы объектов.
 */
@Schema(description = "Enum реализующий статус объекта")
public enum EStatus implements BaseEnum<EStatus> {

    @Schema(title = "Неизвестный статус")
    UNKNOWN(0),

    @Schema(title = "Активный статус")
    ACTIVE(1),

    @Schema(title = "Удаленный статус")
    DELETED(2),

    /**
     * Устанавливается записям, на которые могут ссылаться, но они не нормализованны
     */
    @Schema(title = "Сломанный статус")
    BROKEN(3);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор статуса
     */
    EStatus(int ignoredId) {
    }

    @Override
    public List<EStatus> getValues() {
        return Arrays.stream(EStatus.values()).toList();
    }


}
