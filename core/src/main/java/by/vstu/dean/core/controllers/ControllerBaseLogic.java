package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.trowable.BadRequestException;
import by.vstu.dean.core.trowable.DatabaseFetchException;
import by.vstu.dean.core.trowable.EntityNotFoundException;
import by.vstu.dean.core.trowable.MappingException;
import by.vstu.dean.core.utils.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

/**
 * Базовая реализация для всех контроллеров.
 * В т.ч. и WebSocket
 */
@Slf4j
@RequiredArgsConstructor
public class ControllerBaseLogic<D extends PublicDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

    /**
     * Сервис для работы с объектами.
     */
    protected final S service;

    /**
     * Маппер.
     */
    protected final M mapper;

    /**
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param dto Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @Operation(hidden = true)
    @PreAuthorize("hasAnyRole('HIDDEN_API') && isAnonymous()")
    protected D rawPut(D dto) {
        if (dto == null) {
            log.warn("DTO is empty!");
            throw new BadRequestException();
        }
        return this.mapper.toDto(this.service.save(this.mapper.toEntity(dto)));
    }

    /**
     * Получает все объекты из базы данных.
     *
     * @return Список активных объектов
     */
    @Operation(hidden = true)
    @PreAuthorize("hasAnyRole('HIDDEN_API') && isAnonymous()")
    protected List<D> rawGetAll() {

        List<O> tempO = this.service.getAll();

        if (tempO == null) {
            log.error("Can't get data from database!");
            throw new DatabaseFetchException();
        }

        List<D> tempD = this.mapper.toDto(tempO);

        if (tempD == null) {
            log.error("List mapping error!");
            throw new MappingException();
        }

        return tempD;
    }

    /**
     * Получает все активные объекты из базы данных.
     *
     * @return Список активных объектов
     */
    @Operation(hidden = true)
    @PreAuthorize("hasAnyRole('HIDDEN_API') && isAnonymous()")
    protected List<D> rawGetAllActive(Boolean is) {
        List<O> tempO = this.service.getAllActive(is);

        if (!ValidationUtils.isObjectValid(tempO)) {
            log.error("Can't get active={} data from database!", is);
            throw new DatabaseFetchException();
        }

        List<D> tempD = this.mapper.toDto(tempO);

        if (!ValidationUtils.isObjectValid(tempO)) {
            log.error("List mapping error! active={}", is);
            throw new MappingException();
        }

        return tempD;
    }

    /**
     * Получает объект по его id из базы данных.
     *
     * @param id Идентификатор объекта
     * @return Объект с заданным id
     */
    @Operation(hidden = true)
    @PreAuthorize("hasAnyRole('HIDDEN_API') && isAnonymous()")
    protected D rawGetById(Long id) {
        Optional<O> byId = this.service.getById(id);
        return byId.map(this.mapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param model Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @Operation(hidden = true)
    @PreAuthorize("hasAnyRole('HIDDEN_API') && isAnonymous()")
    protected O rawPutModel(O model) {
        if (model == null) {
            log.warn("entity is empty!");
            throw new BadRequestException();
        }
        return this.service.save(model);
    }

    /**
     * Помечает объект в базе данных по его id как удаленный и возвращает его с установленным статусом DELETED.
     *
     * @param id Идентификатор объекта для удаления
     * @return Объект с установленным статусом DELETED
     */
    @Operation(hidden = true)
    @PreAuthorize("hasAnyRole('HIDDEN_API') && isAnonymous()")
    protected D rawDeleteById(Long id) {
        if (id == null) {
            log.warn("id is null!");
            throw new BadRequestException();
        }
        Optional<O> byId = this.service.getById(id);

        if (byId.isEmpty())
            log.warn("Cannot to find entity with id {}", id);

        return byId.map(this.mapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

}
