package by.vstu.dean.services.migrate;

import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentSpecialityMergeService implements IMigrateExecutor {

    private final DepartmentService departmentService;
    private final SpecialityService specialityService;

    private SpecialityModel get(Long departmentSourceId, Long specSourceId) {

        DepartmentModel department = this.departmentService.getBySourceId(departmentSourceId);
        SpecialityModel speciality = this.specialityService.getBySourceId(specSourceId);

        if (department == null)
            throw new RuntimeException("Department cannot be null!");

        if (speciality == null)
            throw new RuntimeException("Speciality cannot be null!");

        speciality.setDepartment(department);


        return speciality;
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        List<SpecialityModel> list = new ArrayList<>();
        //List<DepartmentSpecialityMerge> old = this.departmentSpecialityMergeRepository.findAll();

        list.add(this.get(1L, 418L));
        list.add(this.get(35L, 496L)); //Автомацизация
        list.add(this.get(35L, 419L)); //Автоматизация
        list.add(this.get(33L, 492L)); // Тт - НОВОЙ СПЕЦИЛЬНОСТИ НЕТ
        list.add(this.get(33L, 20L));
        list.add(this.get(32L, 23L)); // ТЭ
        list.add(this.get(2L, 682L)); // ДМ
        list.add(this.get(2L, 684L));
        list.add(this.get(2L, 683L));
        list.add(this.get(8L, 22L)); //КиТОиО
        list.add(this.get(15L, 320L));
        list.add(this.get(15L, 322L));
        list.add(this.get(26L, 221L));//ЭиЭБ
        list.add(this.get(26L, 513L));
        list.add(this.get(26L, 511L));

        System.out.println(this.specialityService.saveAll(list));
    }


}
