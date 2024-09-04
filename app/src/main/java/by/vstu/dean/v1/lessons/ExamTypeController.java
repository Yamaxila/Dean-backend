package by.vstu.dean.v1.lessons;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.lessons.ExamTypeDTO;
import by.vstu.dean.mapper.v1.ExamTypeMapper;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import by.vstu.dean.services.ExamTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с типами экзаменов.
 */
@RestController
@RequestMapping("/api/v1/plans/exams/")
@Tag(name = "ExamTypes", description = "Типы экзаменов")
public class ExamTypeController extends BaseController<ExamTypeDTO, ExamModel, ExamTypeMapper, ExamModelRepository, ExamTypeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис типов экзаменов
     */
    public ExamTypeController(ExamTypeService service, ExamTypeMapper mapper) {
        super(service, mapper);
    }

    /**
     * @param facultyModel Модель типа экзамена
     */
    @Override
    @Operation(method = "", hidden = true)
    public ResponseEntity<ExamTypeDTO> put(ExamTypeDTO facultyModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @param id Идентификатор типа экзамена
     */
    @Override
    @Operation(method = "", hidden = true)
    public ResponseEntity<ExamTypeDTO> deleteById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}