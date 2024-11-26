package by.vstu.dean.controllers.enums.v1;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.enums.ESemester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enums/semester/")
@Tag(name = "ESemester", description = "Семестр")
public class V1EESemesterController extends EnumController<ESemester> {
    public V1EESemesterController() {
        this.eEnum = ESemester.UNKNOWN;
    }

    @RequestMapping(value = "/names",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllNames", description = "Отправляет все названия типов")
    public ResponseEntity<List<String>> getAllNames() {
        return new ResponseEntity<>(this.eEnum.getValues().stream().map(ESemester::getName).toList(), HttpStatus.OK);
    }


}
