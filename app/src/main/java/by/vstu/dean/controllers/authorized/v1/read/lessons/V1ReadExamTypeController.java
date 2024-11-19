package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.lessons.V1ExamTypeDTO;
import by.vstu.dean.mapper.v1.V1ExamTypeMapper;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import by.vstu.dean.services.ExamTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с типами экзаменов.
 */
@RestController
@RequestMapping("/api/v1/plans/exams/")
@Tag(name = "ExamTypes", description = "Типы экзаменов")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadExamTypeController extends BaseReadController<V1ExamTypeDTO, ExamModel, V1ExamTypeMapper, ExamModelRepository, ExamTypeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис типов экзаменов
     */
    public V1ReadExamTypeController(ExamTypeService service, V1ExamTypeMapper mapper) {
        super(service, mapper);
    }

    /**
     * @param facultyModel Модель типа экзамена
     */

    @Operation(method = "", hidden = true)
    public ResponseEntity<V1ExamTypeDTO> put(V1ExamTypeDTO facultyModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @param id Идентификатор типа экзамена
     */

    @Operation(method = "", hidden = true)
    public ResponseEntity<V1ExamTypeDTO> deleteById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}