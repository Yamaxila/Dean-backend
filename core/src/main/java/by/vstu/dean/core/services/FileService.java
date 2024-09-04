package by.vstu.dean.core.services;

import by.vstu.dean.core.utils.StringUtils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class FileService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ResponseEntity<?> uploadFile(@NotNull MultipartFile file) {

        if (file.getContentType() == null)
            return new ResponseEntity<>("content-type is required!", HttpStatus.BAD_REQUEST);

        if(!file.getContentType().contains("image/")
                && !file.getName().endsWith(".png")
                && !file.getName().endsWith(".jpg")
                && !file.getName().endsWith(".jpeg")
                &&       !file.getName().endsWith(".gif")
        ) return new ResponseEntity<>("only images can be uploaded!", HttpStatus.BAD_REQUEST);


        if(!new File(this.uploadDir).exists())
            try {
                Files.createDirectories(Paths.get(this.uploadDir));
            } catch (IOException e) {
                log.error("cannot create directory", e);
                return new ResponseEntity<>("cannot create directory", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        if(Objects.requireNonNull(file.getName()).length() > 30) {
            log.error("file name too long");
            return new ResponseEntity<>("file name too long", HttpStatus.BAD_REQUEST);
        }


        String filename = file.getOriginalFilename() == null ? file.getName() : file.getOriginalFilename();
        filename = UUID.randomUUID()  + "_" + filename;
        Path path = Paths.get(this.uploadDir + "/" + filename);

        try {
            Files.write(path, file.getBytes());

            return new ResponseEntity<>(filename, HttpStatus.CREATED);
        } catch (IOException e) {
            log.error("cannot write file", e);
            return new ResponseEntity<>("cannot write file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> downloadFile(@NotNull String filename) {
        filename = StringUtils.safeTrim(filename); // нам может прийти всё, что угодно
        // Определяем путь к файлу
        Path filePath = Paths.get(this.uploadDir).resolve(filename).normalize();
        Resource file;
        try {
            file = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Возвращаем файл клиенту
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
