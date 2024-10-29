package by.vstu.dean.core.controllers;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class FileController {

    protected String uploadDir;
    private final FileService fileService;

    private final String[] allowedContent = new String[]{"image/png", "image/jpeg"};
    private final String[] allowedExtensions = new String[]{"png", "jpg", "jpeg"};

    /**
     * Загружает файл на сервер
     *
     * @return Имя файла на сервере
     */
    @RequestMapping(value = "/upload",
            produces = {"text/plain"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "upload", description = "Загружает файл на сервер")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> upload(@RequestBody MultipartFile file) {
        if(file == null)
            return new ResponseEntity<>("file cannot be null!", HttpStatus.BAD_REQUEST);
        return this.fileService.uploadFile(file, uploadDir, allowedContent, allowedExtensions);
    }

    /**
     * Отдает файл с сервера
     *
     * @return Запрашиваемый файл
     */
    @RequestMapping(value = "/download",
            produces = {"image/jpeg", "image/png"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "download", description = "Отдает файл с сервера")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> download(@RequestBody String filename) {
        if(filename == null)
            return new ResponseEntity<>("filename cannot be null!", HttpStatus.BAD_REQUEST);
        return this.fileService.downloadFile(filename, uploadDir);
    }

}
