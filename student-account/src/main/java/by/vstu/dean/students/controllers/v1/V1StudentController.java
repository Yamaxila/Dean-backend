package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.core.services.FileService;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.services.students.StudentService;
import by.vstu.dean.students.services.StudentGradeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_GROUP_ELDER')")
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

    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByCaseNo() {
        String caseNo = this.studentGradeService.jwtCustomTokenDecoder("id_from_source");
        return new ResponseEntity<>(this.v1StudentMapper.toDto(this.studentService.findByCaseNo(Long.parseLong(caseNo)).get()), HttpStatus.OK);
    }

    @GetMapping("/photo")
    public ResponseEntity<?> getPhoto() {
        String caseNo = this.studentGradeService.jwtCustomTokenDecoder("id_from_source");
        String photoUrl = studentService.findByCaseNo(Long.parseLong(caseNo)).get().getPhotoUrl();

        if (photoUrl == null)
            photoUrl = "/api/v1/files/students/download?filename=none.jpg";


        return this.fileService.downloadFile(photoUrl.split("filename=")[1], "\\\\192.168.11.252\\c$\\CIT\\photos\\students");
    }
}