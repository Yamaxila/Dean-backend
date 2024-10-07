package by.vstu.dean.controllers.files;

import by.vstu.dean.core.controllers.FileController;
import by.vstu.dean.core.services.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/files/students/")
@Tag(name = "StudentPhoto", description = "Фото студента")
public class V1StudentImageController extends FileController {

    @Value("${app.upload-dir.students}")
    private String studentsUploadDir;

    public V1StudentImageController(FileService fileService) {
        super(fileService);
        this.uploadDir = this.studentsUploadDir;
    }

    @PostConstruct
    public void init() {
        this.uploadDir = this.studentsUploadDir;
    }

}
