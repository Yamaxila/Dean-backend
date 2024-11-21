package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.core.services.FileService;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.services.students.StudentService;
import by.vstu.dean.students.services.StudentGradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/student")
@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_GROUP_ELDER')")
@Tag(name = "StudentAccountController", description = "Данные студента для личного кабинета")
public class V1StudentController {

    private final StudentService studentService;
    private final V1StudentMapper v1StudentMapper;
    private final StudentGradeService studentGradeService;
    private final FileService fileService;

    @Value("${app.upload-dir.students}")
    private String pathUploadDir;

    public V1StudentController(StudentService studentService, V1StudentMapper v1StudentMapper, StudentGradeService studentGradeService, FileService fileService) {
        this.studentService = studentService;
        this.v1StudentMapper = v1StudentMapper;
        this.studentGradeService = studentGradeService;
        this.fileService = fileService;
    }

    /**
     * Получение информации о студенте.
     *
     * @return Студент
     */
    @Operation(description = "Получение информации о студенте")
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<V1StudentDTO> getStudentByCaseNo() {
        String caseNo = this.studentGradeService.jwtCustomTokenDecoder("id_from_source");
        return new ResponseEntity<>(this.v1StudentMapper.toDto(this.studentService.findByCaseNo(Long.parseLong(caseNo)).orElse(null)), HttpStatus.OK);
    }

    /**
     * Получение фото студента.
     *
     * @return фото
     */
    @Operation(method = "photo", description = "Получение фото студента")
    @GetMapping("/photo")
    public ResponseEntity<?> getPhoto() {
        String caseNo = this.studentGradeService.jwtCustomTokenDecoder("id_from_source");
        String photoUrl = studentService.findByCaseNo(Long.parseLong(caseNo)).orElseThrow().getPhotoUrl();

        if (photoUrl == null)
            photoUrl = "/api/v1/files/students/download?filename=none.jpg";


        Resource photo = (Resource) this.fileService.downloadFile(photoUrl.split("filename=")[1], pathUploadDir).getBody();
        try {
            assert photo != null;
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getFilename() + "\"")
                    .body(Base64.getEncoder().encode(FileCopyUtils.copyToByteArray(photo.getInputStream())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
