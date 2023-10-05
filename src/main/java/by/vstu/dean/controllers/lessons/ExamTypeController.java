package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.BaseController;
import by.vstu.dean.future.models.lessons.ExamModel;
import by.vstu.dean.future.repo.ExamModelRepository;
import by.vstu.dean.services.ExamTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans/exams/")
@Api(tags = {"ExamTypes"}, description = "Типы экзаментов")
public class ExamTypeController extends BaseController<ExamModel, ExamModelRepository, ExamTypeService> {
    public ExamTypeController(ExamTypeService service) {
        super(service);
    }

    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<ExamModel> put(ExamModel facultyModel) {
        throw new SecurityException("Can't execute this!");
    }

    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<ExamModel> delete(ExamModel facultyModel) {
        throw new SecurityException("Can't execute this!");
    }

    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<ExamModel> deleteById(Long id) {
        throw new SecurityException("Can't execute this!");
    }
}
