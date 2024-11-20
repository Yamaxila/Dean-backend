package by.vstu.dean.timetable.controllers.v1;


import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.timetable.dto.RoomDTO;
import by.vstu.dean.timetable.dto.mapper.LessonMapper;
import by.vstu.dean.timetable.enums.EWeekType;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.repo.LessonModelRepository;
import by.vstu.dean.timetable.service.LessonService;
import by.vstu.dean.timetable.service.common.ExcelService;
import by.vstu.dean.timetable.service.common.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для работы фронта с занятиями.
 */
@RestController
@RequestMapping("/api/v1/rooms/")
@Tag(name = "Rooms", description = "Контроллер для работы фронта с занятиями")
@RequiredArgsConstructor
public class V1RoomController {

    private final LessonModelRepository lessonRepo;
    private final LessonMapper mapper;
    private final LessonService lessonService;
    private final RoomService service;
    private final ExcelService excelService;

    @RequestMapping(
            value = {"/"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getAll", description = "Отправляет все объекты занятий из базы со статусом \"ACTIVE\" в виде RoomDTO. " +
                                                "Дополнительно добавляет в список по одному пустому объекту RoomDTO для каждой аудитории на каждый день.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Занятия найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public ResponseEntity<List<RoomDTO>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/visibility"},
            produces = {"application/json"},
            method = {RequestMethod.POST})
    @PreAuthorize("hasRole('ROLE_SCHEDULE')")
    @Transactional
    @Operation(method = "changeVisibility", description = "Изменяет поле видимости для занятий группы на определённый промежуток дат.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Видимость изменена."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права write у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Расписание для данной группы не найдено", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_SCHEDULE"})
    )
    public ResponseEntity<?> changeVisibility(@RequestParam long groupId, @RequestParam boolean visible, @RequestParam String date1, @RequestParam String date2) {
        List<LessonModel> lessons = this.lessonService.setVisibility(groupId, visible, date1, date2);

        if (lessons == null || lessons.isEmpty())
            return new ResponseEntity<>("Расписание для данной группы не найдено", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(this.mapper.toDto(lessons).stream().map(this.service::convertToDto).toList(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/hiddenGroups"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getHiddenGroups", description = "Отправляет все объекты занятий из базы со статусом \"ACTIVE\"  и полем visible = false.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Занятия найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public ResponseEntity<?> getHiddenGroups() {
        List<LessonModel> lessons = this.lessonService.getHiddenGroups();

        return new ResponseEntity<>(lessons.stream().map(l -> l.getGroup().getName()).distinct().toList(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/currentDate"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Transactional
    @Operation(method = "getAllOnCurrentDate", description = "Отправляет все объекты занятий из базы со статусом \"ACTIVE\"  на сегодняшний день.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Занятия найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public ResponseEntity<List<RoomDTO>> getAllOnCurrentDate() {
        return new ResponseEntity<>(this.mapper.toDto(this.lessonRepo.findByStatusAndDate(EStatus.ACTIVE, LocalDate.now(), LocalDate.now())).stream().map(this.service::convertToDto).toList(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/byRoomAndDate"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Transactional
    @Operation(method = "getAllOnCurrentDateAndClassroom", description = "Отправляет все объекты из базы со статусом \"ACTIVE\" на определённый промежуток дат, " +
                                                                         "отфильтрованные по корпусу, аудиториям, типам недели.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Занятия найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public ResponseEntity<List<RoomDTO>> getAllOnCurrentDateAndClassroom(@RequestParam EFrame frame, @RequestParam String[] roomNumbers, @RequestParam EWeekType[] weekType, @RequestParam String startDate, @RequestParam String endDate) {
        List<LessonModel> lessons = this.lessonRepo.findByRoom_FrameAndRoom_RoomNumberInAndWeekTypeInAndStatus(frame, List.of(roomNumbers), List.of(weekType), EStatus.ACTIVE)
                .stream()
                .filter(l -> !(l.getEndDate().isBefore(LocalDate.parse(startDate)) || l.getStartDate().isAfter(LocalDate.parse(endDate)) || (l.getStartDate().equals(LocalDate.parse(endDate)) && !l.getEndDate().equals(LocalDate.parse(endDate)))))
                .toList();

        return new ResponseEntity<>(this.mapper.toDto(lessons).stream().map(this.service::convertToDto).toList(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/getExcel"},
            produces = {"application/xlsx"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getExcel", description = "Отправляет excel файл с расписанием дневного отделения.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Excel создан."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public void getExcel(HttpServletResponse response, @RequestParam long facultyId, @RequestParam int course, @RequestParam String date1, @RequestParam String date2) throws IOException {
        response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("Ras.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = this.excelService.excelDaytime(facultyId, course, date1, date2);
        OutputStream outputStream = response.getOutputStream();

        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(
            value = {"/daytimeSessionExcel"},
            produces = {"application/xlsx"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getDaytimeSessionExcel", description = "Отправляет excel файл с расписанием сессии дневного отделения.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Excel создан."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public void daytimeSessionExcel(HttpServletResponse response, @RequestParam long[] groupIds, @RequestParam String date1, @RequestParam String date2) throws IOException {
        response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("session.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = this.excelService.excelDaytimeSession(groupIds, date1, date2);
        OutputStream outputStream = response.getOutputStream();

        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @Deprecated
    @RequestMapping(
            value = {"/citExcel"},
            produces = {"application/xlsx"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getCitExcel", description = "Отправляет excel файл с расписанием аудиторий ЦИТ первого корпуса.",
            deprecated = true,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Excel создан."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public void citExcel(HttpServletResponse response, @RequestParam String date1, @RequestParam String date2) throws IOException {
        response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("Cit.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = this.excelService.excelForCit(date1, date2);
        OutputStream outputStream = response.getOutputStream();

        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(
            value = {"/teacherExcel"},
            produces = {"application/xlsx"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getTeacherExcel", description = "Отправляет excel файл с занятостью преподавателей на промежуток дат.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Excel создан."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public void teacherExcel(HttpServletResponse response, @RequestParam long[] teacherIds, @RequestParam String date1, @RequestParam String date2) throws IOException {
        response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("Teacher.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = this.excelService.excelTeacherWorkload(teacherIds, date1, date2);
        OutputStream outputStream = response.getOutputStream();

        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(
            value = {"/classroomExcel"},
            produces = {"application/xlsx"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getClassroomExcel", description = "Отправляет excel файл с занятостью аудиторий на промежуток дат.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Excel создан."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public void classroomExcel(HttpServletResponse response, @RequestParam long[] classroomIds, @RequestParam String date1, @RequestParam String date2) throws IOException {
        response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("classroom.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = this.excelService.excelClassroomWorkload(classroomIds, date1, date2);
        OutputStream outputStream = response.getOutputStream();

        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(
            value = {"/zaochnoeExcel"},
            produces = {"application/xlsx"},
            method = {RequestMethod.GET})
    @PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
    @Operation(method = "getZaochExcel", description = "Отправляет excel файл с расписанием заочного отделения на промежуток дат.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Excel создан."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public void zaochnoeExcel(HttpServletResponse response, @RequestParam long[] groupsIds, @RequestParam String from, @RequestParam String to, @RequestParam(required = false, defaultValue = "") String sessionType) throws IOException {
        response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode("Zaochnoe.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = this.excelService.excelZaoch(groupsIds, from, to, sessionType);
        OutputStream outputStream = response.getOutputStream();

        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(
            value = {"/delete"},
            produces = {"application/json"},
            method = {RequestMethod.DELETE})
    @PreAuthorize("hasRole('ROLE_SCHEDULE')")
    @Transactional
    @Operation(method = "delete", description = "Удаляет запись по id (меняет статус на \"DELETED\").",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запись удалена."),
                    @ApiResponse(responseCode = "400", description = "Запись не найдена."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права write у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_SCHEDULE"})
    )
    public ResponseEntity<LessonModel> delete(@RequestParam Long id) {
        LessonModel l = this.lessonService.delete(id);

        if (l == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/put"},
            produces = {"application/json"},
            method = {RequestMethod.PUT})
    @PreAuthorize("hasRole('ROLE_SCHEDULE')")
    @Operation(method = "put", description = "Добавляет запись в базу / изменяет существующую запись в базе.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запись создана/изменена."),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "409", description = "Конфликт с уже существующими записями.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_SCHEDULE"})
    )
    public ResponseEntity<?> put(@RequestBody RoomDTO room, @RequestParam(required = false, defaultValue = "false") boolean check) {
        if (room.getStartDate() == null && room.getEndDate() != null) room.setStartDate(room.getEndDate());
        if (room.getEndDate() == null && room.getStartDate() != null) room.setEndDate(room.getStartDate());

        if (room.getLessonId() != null) {
            ResponseEntity<?> checkedIfExist = this.service.checkIfExist(room);
            if (checkedIfExist.getStatusCode().equals(HttpStatus.CONFLICT)) return checkedIfExist;
        }

        if (check) {
            ResponseEntity<?> checkedForConflicts = this.service.checkForConflicts(room);
            if (checkedForConflicts.getStatusCode().equals(HttpStatus.CONFLICT)) return checkedForConflicts;
        }

        RoomDTO r = this.service.save(room);

        if (r == null) return new ResponseEntity<>("Некорректные данные", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
