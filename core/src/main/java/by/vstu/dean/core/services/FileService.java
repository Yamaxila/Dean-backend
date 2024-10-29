package by.vstu.dean.core.services;

import by.vstu.dean.core.utils.StringUtils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class FileService {


    public ResponseEntity<?> uploadFile(@NotNull MultipartFile file, String uploadDir, String[] allowedContent, String[] allowedExtensions) {

        if (file.getContentType() == null)
            return new ResponseEntity<>("content-type is required!", HttpStatus.BAD_REQUEST);

        if (Arrays.stream(allowedContent).noneMatch(p -> file.getContentType().contains(p)))
            return new ResponseEntity<>("content-type not allowed!", HttpStatus.BAD_REQUEST);

        if (!new File(uploadDir).exists())
            try {
                Files.createDirectories(Paths.get(uploadDir));
            } catch (IOException e) {
                log.error("cannot create directory {}", uploadDir, e);
                return new ResponseEntity<>("cannot create directory", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        if(Objects.requireNonNull(file.getName()).length() > 30) {
            log.error("file name too long");
            return new ResponseEntity<>("file name too long", HttpStatus.BAD_REQUEST);
        }

        String filename = file.getOriginalFilename() == null ? file.getName() : file.getOriginalFilename();
        filename = UUID.randomUUID()  + "_" + filename;
        Path path = Paths.get(uploadDir + "/" + filename);

        if (Arrays.stream(allowedExtensions).noneMatch(filename::endsWith))
            return new ResponseEntity<>("content-type not allowed!", HttpStatus.BAD_REQUEST);

        try {
            Files.write(path, file.getBytes());

            return new ResponseEntity<>(filename, HttpStatus.CREATED);
        } catch (IOException e) {
            log.error("cannot write file", e);
            return new ResponseEntity<>("cannot write file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> downloadFile(@NotNull String filename, String uploadDir) {
        filename = StringUtils.safeTrim(filename); // нам может прийти всё, что угодно
        // Определяем путь к файлу
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        Resource file;
        try {
            file = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            return new ResponseEntity<>("Cannot to create resource!", HttpStatus.INTERNAL_SERVER_ERROR);
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
