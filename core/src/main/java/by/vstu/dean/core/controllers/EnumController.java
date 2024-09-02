package by.vstu.dean.core.controllers;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequiredArgsConstructor
public abstract class EnumController<E extends BaseEnum<E>>{

    protected BaseEnum<E> eEnum;

    @RequestMapping(value = "",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER'))")
    @Operation(method = "getAll", description = "Отправляет все типы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER"})
    public ResponseEntity<List<E>> getAll() {
        return new ResponseEntity<>(this.eEnum.getValues(), HttpStatus.OK);
    }


}
