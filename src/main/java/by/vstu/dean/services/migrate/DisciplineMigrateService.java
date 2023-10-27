package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import by.vstu.dean.old.models.DDisciplineModel;
import by.vstu.dean.old.repo.DDisciplineModelRepository;
import by.vstu.dean.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис миграции данных для моделей DisciplineModel и DDisciplineModel.
 * Этот сервис выполняет миграцию данных из старой версии моделей (DDisciplineModel) в новую версию (DisciplineModel)
 * и сохраняет их в репозитории новой версии.
 */
@Service
@RequiredArgsConstructor
public class DisciplineMigrateService extends BaseMigrateService<DisciplineModel, DDisciplineModel> {

    private final DDisciplineModelRepository dDisciplineModelRepository;
    private final DisciplineModelRepository disciplineModelRepository;
    private final DepartmentModelRepository departmentModelRepository;

    private List<DepartmentModel> departments;

    /**
     * Метод для получения идентификатора последней записи в репозитории новой версии модели.
     *
     * @return Идентификатор последней записи или 0, если репозиторий пуст.
     */
    @Override
    public Long getLastDBId() {
        return this.disciplineModelRepository.findTopByOrderByIdDesc() == null ? 0 : this.disciplineModelRepository.findTopByOrderByIdDesc().getSourceId();
    }

    /**
     * Метод для преобразования моделей DDisciplineModel, которых нет в новой версии, в DisciplineModel.
     *
     * @return Список новых моделей, готовых для сохранения.
     */
    @Override
    public List<DisciplineModel> convertNotExistsFromDB() {
        return this.convertList(this.dDisciplineModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    /**
     * Метод для преобразования одной модели DDisciplineModel в DisciplineModel.
     *
     * @param dDisciplineModel Модель старой версии.
     * @param update           Флаг обновления (true, если модель обновляется, false, если создаётся новая).
     * @return Преобразованная модель новой версии.
     */
    @Override
    public DisciplineModel convertSingle(DDisciplineModel dDisciplineModel, boolean update) {
        if (this.departments == null)
            this.departments = this.departmentModelRepository.findAll();

        DisciplineModel disciplineModel = new DisciplineModel();
        disciplineModel.setName(StringUtils.safeTrim(dDisciplineModel.getName()));
        disciplineModel.setShortName(StringUtils.safeTrim(dDisciplineModel.getShortName()));

        this.departments
                .stream()
                .filter(p ->
                        dDisciplineModel.getDkafId() != null
                                && p.getSourceId().equals(Long.valueOf(dDisciplineModel.getDkafId()))
                ).findAny().ifPresent(disciplineModel::setDepartment);
        disciplineModel.setStatus(EStatus.ACTIVE);
        disciplineModel.setSourceId(dDisciplineModel.getId());
        if (!update)
            disciplineModel.setCreated(LocalDateTime.now());
        disciplineModel.setUpdated(LocalDateTime.now());
        return disciplineModel;
    }

    /**
     * Метод для исправления данных, если это необходимо.
     * Если кафедра в модели DisciplineModel не установлена, устанавливает кафедру и сохраняет модель.
     * Если кафедра уже установлена, но отличается от новой, обновляет кафедру и сохраняет модель.
     *
     * @param disciplineModel Модель новой версии DisciplineModel.
     * @param departmentModel Модель кафедры.
     */
    public void fixIfNeeded(DisciplineModel disciplineModel, DepartmentModel departmentModel) {

        if (disciplineModel.getDepartment() == null) {
            disciplineModel.setDepartment(departmentModel);
            this.insertSingle(disciplineModel);
            return;
        }

        if (!disciplineModel.getDepartment().getId().equals(departmentModel.getId())) {
            disciplineModel.setDepartment(departmentModel);
            this.insertSingle(disciplineModel);
        }
    }

    /**
     * Метод для преобразования списка моделей DDisciplineModel в список DisciplineModel.
     *
     * @param t Список моделей старой версии.
     * @return Список преобразованных моделей новой версии.
     */
    @Override
    public List<DisciplineModel> convertList(List<DDisciplineModel> t) {
        List<DisciplineModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    /**
     * Метод для сохранения одной модели DisciplineModel в репозитории новой версии.
     *
     * @param t Модель новой версии.
     * @return Сохраненная модель.
     */
    @Override
    public DisciplineModel insertSingle(DisciplineModel t) {
        return this.disciplineModelRepository.saveAndFlush(t);
    }

    /**
     * Метод для сохранения списка моделей DisciplineModel в репозитории новой версии.
     *
     * @param t Список моделей новой версии.
     * @return Список сохраненных моделей.
     */
    @Override
    public List<DisciplineModel> insertAll(List<DisciplineModel> t) {
        return this.disciplineModelRepository.saveAllAndFlush(t);
    }

    /**
     * Метод для выполнения миграции данных.
     * Получает и преобразует несуществующие модели и сохраняет их в репозитории новой версии.
     */
    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.departments = this.departmentModelRepository.findAll();
        this.insertAll(this.convertNotExistsFromDB());
    }
}
