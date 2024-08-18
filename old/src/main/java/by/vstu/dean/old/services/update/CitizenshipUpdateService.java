package by.vstu.dean.old.services.update;

import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.dto.mapper.CitizenshipMapper;
import by.vstu.dean.models.students.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import by.vstu.dean.old.models.DCitizenshipModel;
import by.vstu.dean.old.repo.DCitizenshipModelRepository;
import by.vstu.dean.services.CitizenshipService;
import by.vstu.dean.old.services.migrate.CitizenshipMigrateService;
import org.springframework.stereotype.Service;

/**
 * Сервис обновления данных гражданства.
 * Расширяет базовый класс BaseUpdateService, предоставляя функциональность для сравнения и обновления данных гражданства.
 */
@Service
public class CitizenshipUpdateService extends BaseUpdateService<CitizenshipDTO, DCitizenshipModel, DCitizenshipModelRepository, CitizenshipModel, CitizenshipMapper, CitizenshipModelRepository, CitizenshipService, CitizenshipMigrateService> {

    /**
     * Конструктор для класса CitizenshipUpdateService.
     *
     * @param repo               Репозиторий моделей будущей версии.
     * @param dRepo              Репозиторий моделей старой версии.
     * @param baseMigrateService Сервис миграции данных между старой и будущей версией моделей.
     * @param service            Сервис для работы с моделями будущей версии.
     * @param updateService      Главная служба обновления, в которой регистрируется данный сервис.
     */
    public CitizenshipUpdateService(CitizenshipModelRepository repo, DCitizenshipModelRepository dRepo, CitizenshipMigrateService baseMigrateService, CitizenshipService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}

