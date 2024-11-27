package by.vstu.old.dean.services.update;

import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import by.vstu.dean.services.students.CitizenshipService;
import by.vstu.old.dean.models.DCitizenshipModel;
import by.vstu.old.dean.repo.DCitizenshipModelRepository;
import by.vstu.old.dean.services.migrate.CitizenshipMigrateService;
import org.springframework.stereotype.Service;

/**
 * Сервис обновления данных гражданства.
 * Расширяет базовый класс BaseUpdateService, предоставляя функциональность для сравнения и обновления данных гражданства.
 */
@Service
public class CitizenshipUpdateService extends BaseUpdateService<DCitizenshipModel, DCitizenshipModelRepository, CitizenshipModel, CitizenshipModelRepository, CitizenshipService, CitizenshipMigrateService> {

    /**
     * Конструктор для класса CitizenshipUpdateService.
     *
     * @param repo               Репозиторий моделей будущей версии.
     * @param dRepo              Репозиторий моделей старой версии.
     * @param baseMigrateService Сервис миграции данных между старой и будущей версией моделей.
     * @param service            Сервис для работы с моделями будущей версии.
     * @param updateService      Главная служба обновления, в которой регистрируется данный сервис.
     */
    public CitizenshipUpdateService(CitizenshipModelRepository repo, DCitizenshipModelRepository dRepo, CitizenshipMigrateService baseMigrateService, CitizenshipService service) {
        super(repo, dRepo, baseMigrateService, service);
    }

}

