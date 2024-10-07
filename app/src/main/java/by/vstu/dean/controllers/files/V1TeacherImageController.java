package by.vstu.dean.controllers.files;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.controllers.FileController;
import by.vstu.dean.core.services.FileService;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.mapper.v1.V1TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.services.TeacherService;
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
@RequestMapping("/api/v1/files/teachers/")
@Tag(name = "TeacherPhoto", description = "Фото преподавателя")
public class V1TeacherImageController extends FileController {

    @Value("${app.upload-dir.students}")
    private String teachersUploadDir;

    private final TeacherService teacherService;
    private final V1TeacherMapper teacherMapper;

    public V1TeacherImageController(FileService fileService, TeacherService teacherService, V1TeacherMapper teacherMapper) {
        super(fileService);
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }

    @PostConstruct
    public void init() {
        this.uploadDir = this.teachersUploadDir;
    }

    /**
     * Загружает файл на сервер и сохраняет у указанного преподавателя
     *
     * @return DTO преподавателя после сохранения
     */
    @RequestMapping(value = "/apply",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "uploadTeacherPhoto", description = "Загружает файл на сервер и сохраняет у указанного преподавателя")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> uploadTeacherPhoto(@RequestParam Long teacherId, @RequestBody MultipartFile file) {
        ResponseEntity<?> response = this.upload(file);

        if (response.getStatusCode() != HttpStatus.CREATED)
            return response;

        String filename = (String) response.getBody();
        Optional<TeacherModel> oTeacher = this.teacherService.getById(teacherId);

        if (oTeacher.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");

        oTeacher.get().setPhotoUrl("/api/v1/files/teachers/download?filename=" + filename);

        TeacherModel saved = this.teacherService.save(oTeacher.get());

        if (!saved.getPhotoUrl().equals(oTeacher.get().getPhotoUrl())) {
            if (new File(this.teachersUploadDir + "/" + filename).delete())
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Photo url cannot be saved and deleted!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Photo url cannot be saved!");
        }

        V1TeacherDTO v1TeacherDTO = this.teacherMapper.toDto(saved);

        if (v1TeacherDTO == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mapper error!");

        return ResponseEntity.ok(v1TeacherDTO);
    }


}
