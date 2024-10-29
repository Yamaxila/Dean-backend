package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.DepartmentService;
import by.vstu.old.dean.models.DDepartmentModel;
import by.vstu.old.dean.repo.DDepartmentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис миграции данных для моделей DepartmentModel и DDepartmentModel.
 * Этот сервис предоставляет функциональность для миграции данных из старой версии моделей (DDepartmentModel)
 * в новую версию (DepartmentModel) и сохранения их в репозитории новой версии.
 */
@Service
@RequiredArgsConstructor
public class DepartmentMigrateService extends BaseMigrateService<DepartmentModel, DDepartmentModel> {

    private final DDepartmentModelRepository dDepartmentModelRepository;
    private final DepartmentService departmentService;
    private final FacultyModelRepository facultyModelRepository;

    private List<FacultyModel> facultyModels;

    /**
     * Метод для получения идентификатора последней записи в репозитории новой версии модели.
     *
     * @return Идентификатор последней записи или 0, если репозиторий пуст.
     */
    @Override
    public Long getLastDBId() {
        return this.departmentService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.departmentService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    /**
     * Метод для преобразования моделей DDepartmentModel, которых нет в новой версии, в DepartmentModel.
     *
     * @return Список новых моделей, готовых для сохранения.
     */
    @Override
    public List<DepartmentModel> convertNotExistsFromDB() {
        return this.convertList(this.dDepartmentModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    /**
     * Метод для преобразования одной модели DDepartmentModel в DepartmentModel.
     *
     * @param dDepartmentModel Модель старой версии.
     * @param update           Флаг обновления (true, если модель обновляется, false, если создаётся новая).
     * @return Преобразованная модель новой версии.
     */
    @Override
    public DepartmentModel convertSingle(DDepartmentModel dDepartmentModel, boolean update) {
        if (this.facultyModels == null)
            this.facultyModels = this.facultyModelRepository.findAll();
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setName(dDepartmentModel.getName());
        departmentModel.setShortName(dDepartmentModel.getShortName());
        departmentModel.setRoom(dDepartmentModel.getRoom());
        this.facultyModels.stream().filter(p -> p.getSourceId().equals(dDepartmentModel.getFaculty().getId())).findAny().ifPresent(departmentModel::setFaculty);
        departmentModel.setStatus(EStatus.ACTIVE);
        departmentModel.setSourceId(dDepartmentModel.getId());
        if (!update)
            departmentModel.setCreated(LocalDateTime.now());
        departmentModel.setUpdated(LocalDateTime.now());
        return departmentModel;
    }

    /**
     * Метод для преобразования списка моделей DDepartmentModel в список DepartmentModel.
     *
     * @param t Список моделей старой версии.
     * @return Список преобразованных моделей новой версии.
     */
    @Override
    public List<DepartmentModel> convertList(List<DDepartmentModel> t) {
        List<DepartmentModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));

        this.facultyModels.clear();

        return out;
    }

    /**
     * Метод для сохранения одной модели DepartmentModel в репозитории новой версии.
     *
     * @param t Модель новой версии.
     * @return Сохраненная модель.
     */
    @Override
    public DepartmentModel insertSingle(DepartmentModel t) {
        return this.departmentService.save(t);
    }

    /**
     * Метод для сохранения списка моделей DepartmentModel в репозитории новой версии.
     *
     * @param t Список моделей новой версии.
     * @return Список сохраненных моделей.
     */
    @Override
    public List<DepartmentModel> insertAll(List<DepartmentModel> t) {
        return this.departmentService.saveAll(t);
    }

    /**
     * Метод для выполнения миграции данных.
     * Получает и преобразует несуществующие модели и сохраняет их в репозитории новой версии.
     */
    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.facultyModels = this.facultyModelRepository.findAll();
        this.insertAll(this.convertNotExistsFromDB());
    }
}
