package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import lombok.extern.slf4j.Slf4j;

/**
 * Базовый контроллер для работы с объектами базы данных.
 *
 * @param <D> DTO
 * @param <O> Тип объекта
 * @param <M> Маппер
 * @param <R> Тип репозитория
 * @param <S> Тип сервиса
 */
@Slf4j
public abstract class BaseController<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>>
        extends PublicController<D, O, M, R, S> {

    public BaseController(S service, M mapper) {
        super(service, mapper);
    }



}