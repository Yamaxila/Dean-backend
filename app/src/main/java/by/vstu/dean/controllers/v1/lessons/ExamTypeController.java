package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.lessons.ExamTypeDTO;
import by.vstu.dean.dto.mapper.ExamTypeMapper;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import by.vstu.dean.services.ExamTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с типами экзаменов.
 */
@RestController
@RequestMapping("/api/v1/plans/exams/")
@Api(tags = {"ExamTypes"}, description = "Типы экзаментов")
public class ExamTypeController extends BaseController<ExamTypeDTO, ExamModel, ExamTypeMapper, ExamModelRepository, ExamTypeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис типов экзаменов
     */
    public ExamTypeController(ExamTypeService service) {
        super(service);
    }

    /**
     * @param facultyModel Модель типа экзамена
     * @throws SecurityException Can't execute this!
     */
    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<ExamTypeDTO> put(ExamTypeDTO facultyModel) {
        throw new SecurityException("Can't execute this!");
    }

    /**
     * @param id Идентификатор типа экзамена
     * @throws SecurityException Can't execute this!
     */
    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<ExamTypeDTO> deleteById(Long id) {
        throw new SecurityException("Can't execute this!");
    }
}