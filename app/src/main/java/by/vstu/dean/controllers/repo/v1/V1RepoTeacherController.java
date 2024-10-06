package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.mapper.v1.V1TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.repo.TeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/teachers/")
@Tag(name = "Teacher", description = "Преподаватели")
public class V1RepoTeacherController extends RepoController<V1TeacherDTO, TeacherModel, V1TeacherMapper, TeacherModelRepository, TeacherService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис
     * @param mapper  Маппер
     */
    public V1RepoTeacherController(TeacherService service, V1TeacherMapper mapper) {
        super(service, mapper);
    }

}
