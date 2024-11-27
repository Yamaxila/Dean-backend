package by.vstu.photo.dean.services;

import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import by.vstu.photo.dean.models.PhotoDataModel;
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
import java.util.List;
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
        log.info("Photo migration started!");
        this.studentService.getAll().forEach(student -> {
            log.info("{} {}", student.getId(), student.getCaseNo());

            List<PhotoDataModel> photoDataModels = this.photoDataModelRepo.findByCardNumber(String.valueOf(student.getCaseNo()));

            photoDataModels.forEach(photoDataModel -> {
                if (photoDataModel != null && photoDataModel.getPhotoBytes() != null) {
                    try {
                        String filename = this.generateFilename(student);

                        Path wPath = Files.write(Paths.get(this.studentPhotoUploadDir + "/" + filename), photoDataModel.getPhotoBytes());
                        log.info("Wrote image for {} on {}", student.getId(), wPath);

                        student.setPhotoUrl("/api/v1/files/students/download?filename=" + filename);

                        studentService.save(student);

                    } catch (IOException e) {
                        throw new RuntimeException("Cannot write photo to file for id = " + photoDataModel.getId(), e);
                    }
                } else {
                    student.setPhotoUrl("/api/v1/files/students/download?filename=none.jpg");
                    studentService.save(student);
                }

            });
        });
        log.info("Photo migration done!");
    }

    private String generateFilename(StudentModel student) {
        return String.format("%s_%s.jpg", UUID.randomUUID(), student.getCaseNo());
    }

}
