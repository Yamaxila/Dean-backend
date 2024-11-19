package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.students.V1CitizenshipDTO;
import by.vstu.dean.mapper.v1.V1CitizenshipMapper;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import by.vstu.dean.services.students.CitizenshipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с гражданствами студентов.
 */
@RestController
@RequestMapping("/api/v1/students/citizenship/")
@Tag(name = "Citizenship's", description = "Гражданства")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadCitizenshipController extends BaseReadController<V1CitizenshipDTO, CitizenshipModel, V1CitizenshipMapper, CitizenshipModelRepository, CitizenshipService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис гражданств
     * @param mapper Маппер
     */
    public V1ReadCitizenshipController(CitizenshipService service, V1CitizenshipMapper mapper) {
        super(service, mapper);
    }
}