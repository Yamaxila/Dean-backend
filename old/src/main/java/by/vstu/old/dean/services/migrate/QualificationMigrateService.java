package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.services.QualificationService;
import by.vstu.old.dean.models.DQualificationModel;
import by.vstu.old.dean.repo.DQualificationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис миграции данных для моделей QualificationModel и DQualificationModel.
 * Этот сервис выполняет миграцию данных из старой версии моделей (DQualificationModel) в новую версию (QualificationModel)
 * и сохраняет их в репозитории новой версии.
 */
@Service
@RequiredArgsConstructor
public class QualificationMigrateService extends BaseMigrateService<QualificationModel, DQualificationModel> {

    private final DQualificationModelRepository dQualificationRepo;
    private final QualificationService qualificationService;

    /**
     * Метод для получения идентификатора последней записи в репозитории новой версии модели.
     *
     * @return Идентификатор последней записи или 0, если репозиторий пуст.
     */
    @Override
    public Long getLastDBId() {
        return this.qualificationService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.qualificationService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<QualificationModel> convertNotExistsFromDB() {
        return this.convertList(this.dQualificationRepo.findAllByIdAfter(this.getLastDBId()));
    }

    /**
     * Метод для преобразования одной модели DQualificationModel в QualificationModel.
     *
     * @param dQualificationModel Модель старой версии.
     * @param update              Флаг обновления (true, если модель обновляется, false, если создаётся новая).
     * @return Преобразованная модель новой версии.
     */
    @Override
    public QualificationModel convertSingle(DQualificationModel dQualificationModel, boolean update) {

        QualificationModel qualificationModel = new QualificationModel();
        qualificationModel.setName(StringUtils.safeTrim(dQualificationModel.getNamePart1()));
        qualificationModel.setNameGenitive(dQualificationModel.getNamePart2() == null ? "" : StringUtils.safeTrim(dQualificationModel.getNamePart2()));
        qualificationModel.setStatus(EStatus.ACTIVE);
        qualificationModel.setSourceId(dQualificationModel.getId());
        qualificationModel.setUpdated(LocalDateTime.now());

        if (update)
            qualificationModel.setId(dQualificationModel.getId());
        if (!update)
            qualificationModel.setCreated(LocalDateTime.now());


        return qualificationModel;
    }

    /**
     * Метод для преобразования списка моделей DQualificationModel в список QualificationModel.
     *
     * @param t Список моделей старой версии.
     * @return Список преобразованных моделей новой версии.
     */
    @Override
    public List<QualificationModel> convertList(List<DQualificationModel> t) {
        return t.stream().map(this::convertSingle).toList();
    }

    /**
     * Метод для сохранения одной модели QualificationModel в репозитории новой версии.
     *
     * @param t Модель новой версии.
     * @return Сохраненная модель.
     */
    @Override
    public QualificationModel insertSingle(QualificationModel t) {
        return this.qualificationService.save(t);
    }

    /**
     * Метод для сохранения списка моделей QualificationModel в репозитории новой версии.
     *
     * @param t Список моделей новой версии.
     * @return Список сохраненных моделей.
     */
    @Override
    public List<QualificationModel> insertAll(List<QualificationModel> t) {
        return this.qualificationService.saveAll(t);
    }

    /**
     * Метод для выполнения миграции данных.
     * Получает и преобразует несуществующие модели и сохраняет их в репозитории новой версии.
     */
    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
