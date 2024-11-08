package by.vstu.photo.dean.services;

import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import by.vstu.photo.dean.repo.PhotoDataModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoMigrateService {

    private final PhotoDataModelRepository photoDataModelRepo;
    private final StudentService studentService;

    @Value("${app.upload-dir.students}")
    private String studentPhotoUploadDir;

    @PostConstruct
    public void init() {
        this.photoDataModelRepo.findAll().forEach(photoDataModel -> {
            if (photoDataModel.getCardNumber() == null)
                throw new NullPointerException("Card number is null for id " + photoDataModel.getId());

            Optional<StudentModel> oStudent = this.studentService.findByCaseNo(photoDataModel.getCardNumber());

            if (oStudent.isEmpty())
                throw new NullPointerException("Student not found for id " + photoDataModel.getId());

            StudentModel studentModel = oStudent.get();

            try {
                String filename = this.generateFilename(studentModel);
                Path wPath = Files.write(Paths.get(this.studentPhotoUploadDir + "/" + filename), photoDataModel.getPhotoBytes());
                log.debug("Wrote image for {} on {}", studentModel.getId(), wPath);
                studentModel.setPhotoUrl("/api/v1/files/students/download?filename=" + filename);
                this.studentService.save(studentModel);
            } catch (IOException e) {
                throw new RuntimeException("Cannot write photo to file for id = " + photoDataModel.getId(), e);
            }
        });
    }

    private String generateFilename(StudentModel student) {
        return String.format("%s_%s.jpg", UUID.randomUUID(), student.getCaseNo());
    }

}
