package by.vstu.old.dean.services.migrate;

import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для объединения специальностей с соответствующими кафедрами.
 * Этот сервис выполняет объединение специальностей с соответствующими кафедрами и сохраняет их в базе данных.
 */
@Service
@RequiredArgsConstructor
public class DepartmentSpecialityMergeService implements IMigrateExecutor {

    private final DepartmentService departmentService;
    private final SpecialityService specialityService;

    /**
     * Метод для получения модели специальности с привязкой к соответствующей кафедре.
     *
     * @param departmentSourceId Идентификатор кафедры.
     * @param specSourceId       Идентификатор специальности.
     * @return Модель специальности с привязкой к кафедре.
     */
    private SpecialityModel get(Long departmentSourceId, Long specSourceId) {
        DepartmentModel department = this.departmentService.getBySourceId(departmentSourceId);
        SpecialityModel speciality = this.specialityService.getBySourceId(specSourceId);

        if (department == null)
            throw new RuntimeException("Кафедра не может быть null!");

        if (speciality == null)
            throw new RuntimeException("Специальность не может быть null!");

        speciality.setDepartment(department);

        return speciality;
    }

    /**
     * Метод для выполнения миграции данных, объединяя специальности с соответствующими кафедрами.
     */
    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        List<SpecialityModel> list = new ArrayList<>();

        // Примеры объединения специальностей с соответствующими кафедрами
        list.add(this.get(1L, 418L));
        list.add(this.get(35L, 496L)); // Автоматизация
        list.add(this.get(35L, 419L)); // Автоматизация
        list.add(this.get(33L, 492L)); // ТТ - НОВОЙ СПЕЦИАЛЬНОСТИ НЕТ
        list.add(this.get(33L, 20L));
        list.add(this.get(32L, 23L)); // ТЭ
        list.add(this.get(2L, 682L)); // ДМ
        list.add(this.get(2L, 684L));
        list.add(this.get(2L, 683L));
        list.add(this.get(8L, 22L)); // КиТОиО
        list.add(this.get(15L, 320L));
        list.add(this.get(15L, 322L));
        list.add(this.get(26L, 221L));// ЭиЭБ
        list.add(this.get(26L, 513L));
        list.add(this.get(26L, 511L));

        System.out.println(this.specialityService.saveAll(list));
    }
}
