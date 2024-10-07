package by.vstu.dean.controllers.files;

import by.vstu.dean.core.controllers.FileController;
import by.vstu.dean.core.services.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/files/teachers/")
@Tag(name = "TeacherPhoto", description = "Фото преподавателя")
public class V1TeacherImageController extends FileController {

    @Value("${app.upload-dir.students}")
    private String teachersUploadDir;

    public V1TeacherImageController(FileService fileService) {
        super(fileService);
    }

    @PostConstruct
    public void init() {
        this.uploadDir = this.teachersUploadDir;
    }

}
