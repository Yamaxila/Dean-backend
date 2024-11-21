package by.vstu.dean.timetable.service.common;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.services.ClassroomService;
import by.vstu.dean.services.DisciplineService;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.TeacherService;
import by.vstu.dean.timetable.dto.LessonDTO;
import by.vstu.dean.timetable.dto.RoomDTO;
import by.vstu.dean.timetable.dto.mapper.LessonMapper;
import by.vstu.dean.timetable.enums.EWeekType;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.repo.LessonModelRepository;
import by.vstu.dean.timetable.service.LessonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RoomService {

    private final LessonService lessonService;
    private final GroupService groupService;
    private final ClassroomService classroomService;
    private final TeacherService teacherService;
    private final DisciplineService disciplineService;
    private final LessonMapper lessonMapper;
    private final LessonModelRepository lessonRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.journal}")
    private String journalUrl;
    @Value("${api.auth}")
    private String authUrl;

    public RoomService(LessonMapper lessonMapper, LessonService lessonService, ClassroomService classroomService, GroupService groupService, TeacherService teacherService, DisciplineService disciplineService, LessonModelRepository lessonRepo) {
        this.lessonService = lessonService;
        this.classroomService = classroomService;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.disciplineService = disciplineService;
        this.lessonRepo = lessonRepo;
        this.lessonMapper = lessonMapper;
    }

    public List<RoomDTO> getAll() {

        List<RoomDTO> rooms = new ArrayList<>();
        List<LessonDTO> lessonDTOs = this.lessonMapper.toDto(lessonService.getAllActive(true));

        lessonDTOs.forEach(lessonDTO -> rooms.add(this.convertToDto(lessonDTO)));

        this.classroomService.getAll().stream().filter(c -> c.getStatus().equals(EStatus.ACTIVE)).distinct().forEach(room -> {
            for (int d = 0; d < 7; d++) {
                RoomDTO roomDTO = createEmptyRoomDTO(room, (short) d);
                rooms.add(roomDTO);
            }
        });

        return rooms.stream().sorted(Comparator.comparing(RoomDTO::getRoomNumber).thenComparing(RoomDTO::getDay).thenComparing(RoomDTO::getLessonNumber).thenComparing(RoomDTO::getWeekType)).toList();
    }

    private RoomDTO createEmptyRoomDTO(ClassroomModel room, short day) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setRoomId(room.getId());
        roomDTO.setFrame(String.valueOf(room.getFrame()));
        roomDTO.setRoomType(String.valueOf(room.getRoomType()));
        roomDTO.setDay(day);
        roomDTO.setRoomId(room.getId());
        roomDTO.setWeekType(String.valueOf(EWeekType.ALWAYS));
        roomDTO.setLessonNumber((short) 20);
        roomDTO.setLessonType(String.valueOf(ELessonType.UNKNOWN));
        return roomDTO;
    }

    public RoomDTO convertToDto(LessonDTO lessonDTO) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(lessonDTO.getId());
        roomDTO.setUpdated(lessonDTO.getUpdated());
        roomDTO.setStatus(lessonDTO.getStatus());
        roomDTO.setRoomId(lessonDTO.getRoom().getId());
        roomDTO.setLessonId(lessonDTO.getId());
        roomDTO.setGroupId(lessonDTO.getGroup().getId());
        roomDTO.setTeacherId(lessonDTO.getTeacher().getId());
        roomDTO.setDisciplineId(lessonDTO.getDiscipline().getId());
        roomDTO.setRoomNumber(lessonDTO.getRoom().getRoomNumber());
        roomDTO.setGroup(lessonDTO.getGroup().getName());
        roomDTO.setLessonType(String.valueOf(lessonDTO.getLessonType()));
        roomDTO.setWeekType(String.valueOf(lessonDTO.getWeekType()));
        roomDTO.setRoomType(String.valueOf(lessonDTO.getRoom().getRoomType()));
        roomDTO.setFrame(String.valueOf(lessonDTO.getRoom().getFrame()));
        roomDTO.setLessonNumber(lessonDTO.getLessonNumber());
        roomDTO.setDisciplineName(lessonDTO.getDiscipline().getName());
        roomDTO.setDay(lessonDTO.getDay());
        roomDTO.setSubGroup(lessonDTO.getSubGroup());
        roomDTO.setTeacherFullName(String.format("%s %s %s", lessonDTO.getTeacher().getSurname(), lessonDTO.getTeacher().getName(), lessonDTO.getTeacher().getPatronymic()));
        roomDTO.setStartDate(lessonDTO.getStartDate());
        roomDTO.setEndDate(lessonDTO.getEndDate());

        return roomDTO;
    }

    public RoomDTO save(RoomDTO roomDTO) {

        if (roomDTO.getId() == null) {
            LessonModel l = this.lessonService.create(roomDTO);

            if (l != null) {
                LessonModel lessonModel = this.lessonService.save(l);
                HttpEntity<Object> request = newRequest(List.of(lessonModel));

                restTemplate.exchange(journalUrl + "utils", HttpMethod.POST, request, new ParameterizedTypeReference<List<LessonModel>>() {
                });
                return convertToDto(this.lessonMapper.toDto(lessonModel));
            } else
                return null;
        }

        Optional<LessonModel> o = this.lessonService.getById(roomDTO.getId());

        return o.map(lessonModel -> convertToDto(
                        this.lessonMapper.toDto(this.lessonService.save(this.lessonService.update(lessonModel, roomDTO))))
                )
                .orElse(null);
    }

    public ResponseEntity<?> checkIfExist(RoomDTO roomDTO) {
        if (roomDTO.getId() == null) {
            if (this.lessonRepo.existsByRoomIdAndTeacherIdAndDisciplineIdAndGroupIdAndSubGroupAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndStatusAndStartDateAndEndDate(
                    roomDTO.getRoomId(),
                    roomDTO.getTeacherId(),
                    roomDTO.getDisciplineId(),
                    roomDTO.getGroupId(),
                    roomDTO.getSubGroup(),
                    roomDTO.getDay(),
                    roomDTO.getLessonNumber(),
                    ELessonType.valueOf(roomDTO.getLessonType()),
                    EWeekType.valueOf(roomDTO.getWeekType()),
                    EStatus.ACTIVE,
                    roomDTO.getStartDate(),
                    roomDTO.getEndDate()))
                return new ResponseEntity<>("Ошибка: Такая запись уже существует.", HttpStatus.CONFLICT);
            else return new ResponseEntity<>(HttpStatus.OK);
        }

        Optional<LessonModel> oLesson = lessonRepo.findById(roomDTO.getId());

        if (oLesson.isPresent()) {
            LessonModel lesson = oLesson.get();

            if (roomDTO.getGroupId() != null)
                this.groupService.getById(roomDTO.getGroupId()).ifPresent(lesson::setGroup);
            if (roomDTO.getTeacherId() != null)
                this.teacherService.getById(roomDTO.getTeacherId()).ifPresent(lesson::setTeacher);
            if (roomDTO.getDisciplineId() != null)
                this.disciplineService.getById(roomDTO.getDisciplineId()).ifPresent(lesson::setDiscipline);
            if (roomDTO.getRoomId() != null)
                this.classroomService.getById(roomDTO.getRoomId()).ifPresent(lesson::setRoom);
            if (roomDTO.getLessonNumber() != null)
                lesson.setLessonNumber(roomDTO.getLessonNumber());
            if (roomDTO.getDay() != null)
                lesson.setDay(roomDTO.getDay());
            if (roomDTO.getLessonType() != null)
                lesson.setLessonType(ELessonType.valueOf(roomDTO.getLessonType()));
            if (roomDTO.getWeekType() != null)
                lesson.setWeekType(EWeekType.valueOf(roomDTO.getWeekType()));
            if (roomDTO.getSubGroup() != null)
                lesson.setSubGroup(roomDTO.getSubGroup());
            if (roomDTO.getStartDate() != null)
                lesson.setStartDate(roomDTO.getStartDate());
            if (roomDTO.getEndDate() != null)
                lesson.setEndDate(roomDTO.getEndDate());

            if (this.lessonRepo.existsByRoomIdAndTeacherIdAndDisciplineIdAndGroupIdAndSubGroupAndDayAndLessonNumberAndLessonTypeAndWeekTypeAndStatusAndStartDateAndEndDateAndLessonIdNot(
                    lesson.getRoom().getId(),
                    lesson.getTeacher().getId(),
                    lesson.getDiscipline().getId(),
                    lesson.getGroup().getId(),
                    lesson.getSubGroup(),
                    lesson.getDay(),
                    lesson.getLessonNumber(),
                    lesson.getLessonType(),
                    lesson.getWeekType(),
                    EStatus.ACTIVE,
                    lesson.getStartDate(),
                    lesson.getEndDate(),
                    lesson.getId()))
                return new ResponseEntity<>("Ошибка: Такая запись уже существует.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Проверка на конфликты новой пары и уже добавленные, проходящие в то же время в том же месте,
     * проверка на конфликт нескольких пар в одно время у одного препода.<br/>
     * P.S. Добавил говна, потому что сказали =))
     *
     * @param room Новое занятие
     * @return {@link ResponseEntity} со статусом {@link HttpStatus#CONFLICT}, если новая запись конфликтует с какими-то существующими, и
     * {@link HttpStatus#OK}, если конфликтов нет.
     */
    public ResponseEntity<?> checkForConflicts(RoomDTO room) {
        switch (EWeekType.valueOf(room.getWeekType())) {
            case ALWAYS:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);


                break;

            case NUMERATOR:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.FIRST, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.THIRD, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.FIRST, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.THIRD, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.FIRST, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.THIRD, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                break;

            case DENOMINATOR:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.SECOND, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.FOURTH, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.SECOND, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.FOURTH, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.SECOND, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.FOURTH, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                break;

            case FIRST:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.FIRST, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.FIRST, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.FIRST, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                break;

            case SECOND:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.SECOND, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.SECOND, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.SECOND, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                break;

            case THIRD:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.THIRD, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.THIRD, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.NUMERATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.THIRD, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                break;

            case FOURTH:
                if (this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByRoomIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getRoomId(), room.getLessonNumber(), room.getDay(), EWeekType.FOURTH, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: В аудитории " + room.getRoomNumber() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByTeacherIdAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getTeacherId(), room.getLessonNumber(), room.getDay(), EWeekType.FOURTH, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У преподавателя " + room.getTeacherFullName() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                if (this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.ALWAYS, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.DENOMINATOR, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()) ||
                    this.lessonRepo.existsByGroupIdAndSubGroupAndLessonNumberAndDayAndWeekTypeAndStatusAndBetweenDates(room.getGroupId(), room.getSubGroup(), room.getLessonNumber(), room.getDay(), EWeekType.FOURTH, EStatus.ACTIVE, room.getStartDate(), room.getEndDate()))
                    return new ResponseEntity<>("Конфликт: У группы " + room.getGroup() + " уже есть пара на это время.", HttpStatus.CONFLICT);

                break;
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private HttpEntity<Object> newRequest(Object body) {
        HttpHeaders headers = new HttpHeaders();
        String token = getTokenFromAuthService();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    private String getTokenFromAuthService() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", "admin@gmail.com");
        body.add("password", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + "VlNUVV9FTEVDVFJPTklDSk9VUk5BTF9DTElFTlQ6VlNUVV9FTEVDVFJPTklDSk9VUk5BTF9DTElFTlQ=");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(authUrl + "token?grant_type=password",
                HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });
        return Objects.requireNonNull(response.getBody()).split("\"")[3];
    }
}
