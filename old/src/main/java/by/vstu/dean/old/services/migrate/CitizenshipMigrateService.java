package by.vstu.dean.old.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.students.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import by.vstu.dean.old.models.DCitizenshipModel;
import by.vstu.dean.old.repo.DCitizenshipModelRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис миграции данных для модели CitizenshipModel.
 */
@ApiModel(description = "Сервис миграции данных для модели CitizenshipModel")
@Service
@RequiredArgsConstructor
public class CitizenshipMigrateService extends BaseMigrateService<CitizenshipModel, DCitizenshipModel> {

    private final DCitizenshipModelRepository dCitizenshipRepository;
    private final CitizenshipModelRepository citizenshipRepository;

    /**
     * Получить ID последней записи в базе данных.
     *
     * @return ID последней записи в базе данных.
     */
    @ApiModelProperty(value = "Метод для получения ID последней записи в базе данных.")
    @Override
    public Long getLastDBId() {
        return this.citizenshipRepository.findTopByOrderByIdDesc() == null ? 0 : this.citizenshipRepository.findTopByOrderByIdDesc().getSourceId();
    }

    /**
     * Преобразовать неприсутствующие данные из базы данных.
     *
     * @return Список CitizenshipModel, которые не существуют в базе данных.
     */
    @ApiModelProperty(value = "Метод для преобразования неприсутствующих данных из базы данных.")
    @Override
    public List<CitizenshipModel> convertNotExistsFromDB() {
        return this.convertList(this.dCitizenshipRepository.findAllByIdAfter(this.getLastDBId()));
    }

    /**
     * Преобразовать одну запись из базы данных.
     *
     * @param dCitizenshipModel Исходная запись из базы данных.
     * @param update            Флаг обновления записи.
     * @return Преобразованная запись CitizenshipModel.
     */
    @ApiModelProperty(value = "Метод для преобразования одной записи из базы данных.")
    @Override
    public CitizenshipModel convertSingle(DCitizenshipModel dCitizenshipModel, boolean update) {
        CitizenshipModel citizenshipModel = new CitizenshipModel(dCitizenshipModel.getName());
        citizenshipModel.setStatus(EStatus.ACTIVE);
        citizenshipModel.setSourceId(dCitizenshipModel.getId());
        if (!update)
            citizenshipModel.setCreated(LocalDateTime.now());
        citizenshipModel.setUpdated(LocalDateTime.now());
        return citizenshipModel;
    }

    /**
     * Преобразовать список записей из базы данных.
     *
     * @param t Список исходных записей из базы данных.
     * @return Список преобразованных записей CitizenshipModel.
     */
    @ApiModelProperty(value = "Метод для преобразования списка записей из базы данных.")
    @Override
    public List<CitizenshipModel> convertList(List<DCitizenshipModel> t) {
        List<CitizenshipModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    /**
     * Вставить одну запись CitizenshipModel в базу данных.
     *
     * @param t Преобразованная запись CitizenshipModel.
     * @return Вставленная запись CitizenshipModel.
     */
    @ApiModelProperty(value = "Метод для вставки одной записи CitizenshipModel в базу данных.")
    @Override
    public CitizenshipModel insertSingle(CitizenshipModel t) {
        return this.citizenshipRepository.saveAndFlush(t);
    }

    /**
     * Вставить все записи CitizenshipModel в базу данных.
     *
     * @param t Список преобразованных записей CitizenshipModel.
     * @return Список вставленных записей CitizenshipModel.
     */
    @ApiModelProperty(value = "Метод для вставки всех записей CitizenshipModel в базу данных.")
    @Override
    public List<CitizenshipModel> insertAll(List<CitizenshipModel> t) {
        return this.citizenshipRepository.saveAllAndFlush(t);
    }

    /**
     * Метод для выполнения миграции данных.
     */
    @ApiModelProperty(value = "Метод для выполнения миграции данных.")
    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
