package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.utils.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class RepoController<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

    /**
     * Сервис для работы с объектами.
     */
    protected final S service;

    /**
     * Маппер.
     */
    protected final M mapper;


    /**
     * Получает объекты из базы данных через rsql-запрос.
     *
     * @return Список объектов
     */
    @RequestMapping(value = "/rsql",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('rsql') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "rsql", description = "Получает объекты из базы данных через rsql-запрос",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает прав rsql у клиента или роли ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"rsql", "ROLE_ADMIN"})
    )
    public ResponseEntity<List<O>> getAllRSql(@RequestParam(required = false, defaultValue = "id>0") String sql) {
        try {
            return new ResponseEntity<>(this.service.rsql(sql), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Can't execute rsql!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Получает объекты из базы данных через rsql-запрос.
     *
     * @return Список объектов
     */
    @RequestMapping(value = "/dto_rsql",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('rsql') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "dto_rsql", description = "Получает DTO из базы данных через rsql-запрос",
            responses = {
                    @ApiResponse(responseCode = "200", description = "DTO найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права rsql у клиента или роли ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"rsql", "ROLE_ADMIN"})
    )
    public ResponseEntity<List<D>> getAllRSqlDTO(@RequestParam(required = false, defaultValue = "id>0") String sql) {
        try {

            List<O> tempO = this.service.rsql(sql);

            if (!ValidationUtils.isObjectValid(tempO)) {
                log.error("Can't execute rsql before dto mapping!");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<D> tempD = this.mapper.toDto(tempO);

            if (!ValidationUtils.isObjectValid(tempO)) {
                log.error("List from rsql request mapping error!");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(tempD, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Can't execute rsql!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
