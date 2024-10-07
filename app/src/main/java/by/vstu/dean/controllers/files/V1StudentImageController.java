package by.vstu.dean.controllers.files;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.controllers.FileController;
import by.vstu.dean.core.services.FileService;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/files/students/")
@Tag(name = "StudentPhoto", description = "Фото студента")
public class V1StudentImageController extends FileController {

    @Value("${app.upload-dir.students}")
    private String studentsUploadDir;

    private final StudentService studentService;
    private final V1StudentMapper studentMapper;

    public V1StudentImageController(FileService fileService, StudentService studentService, V1StudentMapper studentMapper) {
        super(fileService);
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.uploadDir = this.studentsUploadDir;
    }

    @PostConstruct
    public void init() {
        this.uploadDir = this.studentsUploadDir;
    }

    /**
     * Загружает файл на сервер и сохраняет у указанного студента
     *
     * @return DTO студента после сохранения
     */
    @RequestMapping(value = "/apply",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "uploadStudentPhoto", description = "Загружает файл на сервер и сохраняет у указанного студента")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> uploadStudentPhoto(@RequestParam Long studentId, @RequestBody MultipartFile file) {
        ResponseEntity<?> response = this.upload(file);

        if (response.getStatusCode() != HttpStatus.CREATED)
            return response;

        String filename = (String) response.getBody();
        Optional<StudentModel> student = this.studentService.getById(studentId);

        if (student.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");

        student.get().setPhotoUrl("/api/v1/files/students/download?filename=" + filename);

        StudentModel saved = this.studentService.save(student.get());

        if (!saved.getPhotoUrl().equals(student.get().getPhotoUrl())) {
            if (new File(this.studentsUploadDir + "/" + filename).delete())
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Photo url cannot be saved and deleted!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Photo url cannot be saved!");
        }

        V1StudentDTO v1StudentDTO = this.studentMapper.toDto(saved);

        if (v1StudentDTO == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mapper error!");

        return ResponseEntity.ok(v1StudentDTO);
    }

}
