CREATE TABLE dean_absences
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id      BIGINT,
    status         SMALLINT,
    created        TIMESTAMP WITHOUT TIME ZONE,
    updated        TIMESTAMP WITHOUT TIME ZONE,
    date           date,
    discipline_id  BIGINT                                  NOT NULL,
    department_id  BIGINT                                  NOT NULL,
    teacher_id     BIGINT                                  NOT NULL,
    lesson_type    SMALLINT                                NOT NULL,
    lesson_number  INTEGER                                 NOT NULL,
    student_id     BIGINT                                  NOT NULL,
    absence_time   DOUBLE PRECISION                        NOT NULL,
    hour_price     DOUBLE PRECISION                        NOT NULL,
    payment_date   date,
    date_completed date,
    date_print     date,
    reason_msg     VARCHAR(100),
    date_erip      date,
    printed        BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_dean_absences PRIMARY KEY (id)
);

CREATE TABLE dean_addresses
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id  BIGINT,
    status     SMALLINT,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    address    VARCHAR(255),
    country    VARCHAR(255),
    post_index VARCHAR(255),
    state      VARCHAR(255),
    region     VARCHAR(255),
    city       VARCHAR(255),
    street     VARCHAR(255),
    house      VARCHAR(255),
    house_part VARCHAR(255),
    flat       VARCHAR(255),
    CONSTRAINT pk_dean_addresses PRIMARY KEY (id)
);

CREATE TABLE dean_citizenship
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id BIGINT,
    status    SMALLINT,
    created   TIMESTAMP WITHOUT TIME ZONE,
    updated   TIMESTAMP WITHOUT TIME ZONE,
    name      VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_dean_citizenship PRIMARY KEY (id)
);

CREATE TABLE dean_departments
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id  BIGINT,
    status     SMALLINT,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255)                            NOT NULL,
    short_name VARCHAR(255)                            NOT NULL,
    room       VARCHAR(255)                            NOT NULL,
    faculty_id BIGINT,
    CONSTRAINT pk_dean_departments PRIMARY KEY (id)
);

CREATE TABLE dean_deviations
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id        BIGINT,
    status           SMALLINT,
    created          TIMESTAMP WITHOUT TIME ZONE,
    updated          TIMESTAMP WITHOUT TIME ZONE,
    student_id       BIGINT,
    msg_type         VARCHAR(255),
    expelled         VARCHAR(255),
    reason_number    INTEGER,
    deviation_msg    VARCHAR(255),
    msg_date         TIMESTAMP WITHOUT TIME ZONE,
    date_start       TIMESTAMP WITHOUT TIME ZONE,
    date_end         TIMESTAMP WITHOUT TIME ZONE,
    last_name_new    VARCHAR(255),
    group_name_new   VARCHAR(255),
    msg1             VARCHAR(255),
    msg2             VARCHAR(255),
    command_msg1     VARCHAR(255),
    command_msg      VARCHAR(255),
    date_liquidation TIMESTAMP WITHOUT TIME ZONE,
    penalty          INTEGER,
    CONSTRAINT pk_dean_deviations PRIMARY KEY (id)
);

CREATE TABLE dean_disciplines
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id     BIGINT,
    status        SMALLINT,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    name          VARCHAR(255)                            NOT NULL,
    short_name    VARCHAR(255)                            NOT NULL,
    department_id BIGINT,
    CONSTRAINT pk_dean_disciplines PRIMARY KEY (id)
);

CREATE TABLE dean_educations
(
    id                        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id                 BIGINT,
    status                    SMALLINT,
    created                   TIMESTAMP WITHOUT TIME ZONE,
    updated                   TIMESTAMP WITHOUT TIME ZONE,
    student_id                BIGINT                                  NOT NULL,
    education                 VARCHAR(255)                            NOT NULL,
    education_document_type   VARCHAR(255)                            NOT NULL,
    education_document_serial VARCHAR(255)                            NOT NULL,
    education_document_number VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_dean_educations PRIMARY KEY (id)
);

CREATE TABLE dean_exams
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id BIGINT,
    status    SMALLINT,
    created   TIMESTAMP WITHOUT TIME ZONE,
    updated   TIMESTAMP WITHOUT TIME ZONE,
    name      VARCHAR(255)                            NOT NULL,
    type      SMALLINT                                NOT NULL,
    CONSTRAINT pk_dean_exams PRIMARY KEY (id)
);

CREATE TABLE dean_faculties
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id            BIGINT,
    status               SMALLINT,
    created              TIMESTAMP WITHOUT TIME ZONE,
    updated              TIMESTAMP WITHOUT TIME ZONE,
    short_name           VARCHAR(255)                            NOT NULL,
    name                 VARCHAR(255)                            NOT NULL,
    name_genitive        VARCHAR(255),
    name_dative          VARCHAR(255),
    rector_name          VARCHAR(255),
    dean                 VARCHAR(255),
    clerk_name           VARCHAR(255),
    enroll_msg_paid      VARCHAR(255),
    enroll_date_paid     date,
    enroll_msg_not_paid  VARCHAR(255),
    enroll_date_not_paid date,
    journal_type         INTEGER,
    faculty_type         INTEGER,
    semester_start_year  INTEGER,
    semester_end_year    INTEGER,
    semester             VARCHAR(255),
    move_msg_number      VARCHAR(255),
    move_msg_date        date,
    education_type       INTEGER,
    CONSTRAINT pk_dean_faculties PRIMARY KEY (id)
);

CREATE TABLE dean_gratitude
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id    BIGINT,
    status       SMALLINT,
    created      TIMESTAMP WITHOUT TIME ZONE,
    updated      TIMESTAMP WITHOUT TIME ZONE,
    student_id   BIGINT,
    order_number VARCHAR(255),
    order_text   VARCHAR(255),
    CONSTRAINT pk_dean_gratitude PRIMARY KEY (id)
);

CREATE TABLE dean_groups
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id      BIGINT,
    status         SMALLINT,
    created        TIMESTAMP WITHOUT TIME ZONE,
    updated        TIMESTAMP WITHOUT TIME ZONE,
    name           VARCHAR(255)                            NOT NULL,
    spec_id        BIGINT,
    faculty_id     BIGINT                                  NOT NULL,
    year_start     INTEGER,
    end_semester   SMALLINT,
    year_end       INTEGER,
    date_start     date,
    date_end       date,
    current_course INTEGER,
    score          DOUBLE PRECISION,
    CONSTRAINT pk_dean_groups PRIMARY KEY (id)
);

CREATE TABLE dean_hostel_rooms
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id   BIGINT,
    status      SMALLINT,
    created     TIMESTAMP WITHOUT TIME ZONE,
    updated     TIMESTAMP WITHOUT TIME ZONE,
    room_number INTEGER                                 NOT NULL,
    room_type   SMALLINT                                NOT NULL,
    floor       INTEGER                                 NOT NULL,
    hostel      SMALLINT                                NOT NULL,
    CONSTRAINT pk_dean_hostel_rooms PRIMARY KEY (id)
);

CREATE TABLE dean_institutions
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id  BIGINT,
    status     SMALLINT,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    full_name  VARCHAR(255)                            NOT NULL,
    short_name VARCHAR(255),
    CONSTRAINT pk_dean_institutions PRIMARY KEY (id)
);

CREATE TABLE dean_internal_messages
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id       BIGINT,
    status          SMALLINT,
    created         TIMESTAMP WITHOUT TIME ZONE,
    updated         TIMESTAMP WITHOUT TIME ZONE,
    title           VARCHAR(255)                            NOT NULL,
    message         VARCHAR(4096)                           NOT NULL,
    inactive        BOOLEAN                                 NOT NULL,
    cron_expression VARCHAR(255),
    message_type    SMALLINT                                NOT NULL,
    CONSTRAINT pk_dean_internal_messages PRIMARY KEY (id)
);

CREATE TABLE dean_parents
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id  BIGINT,
    status     SMALLINT,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    student_id BIGINT,
    surname    VARCHAR(255),
    name       VARCHAR(255),
    patronymic VARCHAR(255),
    job        VARCHAR(255),
    phone_id   BIGINT,
    CONSTRAINT pk_dean_parents PRIMARY KEY (id)
);

CREATE TABLE dean_passports
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id             BIGINT,
    status                SMALLINT,
    created               TIMESTAMP WITHOUT TIME ZONE,
    updated               TIMESTAMP WITHOUT TIME ZONE,
    passport_type         SMALLINT                                NOT NULL,
    passport_serial       VARCHAR(255)                            NOT NULL,
    passport_number       VARCHAR(255)                            NOT NULL,
    passport_issue_date   date                                    NOT NULL,
    passport_issue_string VARCHAR(255)                            NOT NULL,
    passport_id           VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_dean_passports PRIMARY KEY (id)
);

CREATE TABLE dean_penalties
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id        BIGINT,
    status           SMALLINT,
    created          TIMESTAMP WITHOUT TIME ZONE,
    updated          TIMESTAMP WITHOUT TIME ZONE,
    student_id       BIGINT,
    date_liquidation TIMESTAMP WITHOUT TIME ZONE,
    reason_id        BIGINT,
    order_number     VARCHAR(255),
    order_text       VARCHAR(255),
    CONSTRAINT pk_dean_penalties PRIMARY KEY (id)
);

CREATE TABLE dean_phones
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id BIGINT,
    status    SMALLINT,
    created   TIMESTAMP WITHOUT TIME ZONE,
    updated   TIMESTAMP WITHOUT TIME ZONE,
    type      SMALLINT,
    phone     VARCHAR(255),
    CONSTRAINT pk_dean_phones PRIMARY KEY (id)
);

CREATE TABLE dean_qualifications
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id     BIGINT,
    status        SMALLINT,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    name          VARCHAR(255)                            NOT NULL,
    name_genitive VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_dean_qualifications PRIMARY KEY (id)
);

CREATE TABLE dean_reasons
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id BIGINT,
    status    SMALLINT,
    created   TIMESTAMP WITHOUT TIME ZONE,
    updated   TIMESTAMP WITHOUT TIME ZONE,
    name      VARCHAR(255),
    CONSTRAINT pk_dean_reasons PRIMARY KEY (id)
);

CREATE TABLE dean_rooms
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id     BIGINT,
    status        SMALLINT,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    frame         SMALLINT                                NOT NULL,
    room_type     SMALLINT                                NOT NULL,
    department_id BIGINT,
    room_number   VARCHAR(255)                            NOT NULL,
    seats_number  INTEGER,
    square        DOUBLE PRECISION,
    CONSTRAINT pk_dean_rooms PRIMARY KEY (id)
);

CREATE TABLE dean_specialities
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id     BIGINT,
    status        SMALLINT,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    name          VARCHAR(255)                            NOT NULL,
    short_name    VARCHAR(255)                            NOT NULL,
    spec_code     VARCHAR(255)                            NOT NULL,
    department_id BIGINT,
    CONSTRAINT pk_dean_specialities PRIMARY KEY (id)
);

CREATE TABLE dean_specializations
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id        BIGINT,
    status           SMALLINT,
    created          TIMESTAMP WITHOUT TIME ZONE,
    updated          TIMESTAMP WITHOUT TIME ZONE,
    name             VARCHAR(255)                            NOT NULL,
    short_name       VARCHAR(255)                            NOT NULL,
    spez_code        VARCHAR(255)                            NOT NULL,
    spec_id          BIGINT,
    qualification_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_dean_specializations PRIMARY KEY (id)
);

CREATE TABLE dean_statement_student_merge
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id      BIGINT,
    status         SMALLINT,
    created        TIMESTAMP WITHOUT TIME ZONE,
    updated        TIMESTAMP WITHOUT TIME ZONE,
    statement_id   BIGINT,
    pass_date      date,
    sheet_number   INTEGER,
    student_id     BIGINT                                  NOT NULL,
    grade          VARCHAR(255),
    teacher_id     BIGINT,
    retake         BOOLEAN DEFAULT FALSE,
    attempt_number INTEGER,
    CONSTRAINT pk_dean_statement_student_merge PRIMARY KEY (id)
);

CREATE TABLE dean_statements
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id              BIGINT,
    status                 SMALLINT,
    created                TIMESTAMP WITHOUT TIME ZONE,
    updated                TIMESTAMP WITHOUT TIME ZONE,
    study_plan_id          BIGINT                                  NOT NULL,
    group_statement_number INTEGER,
    statement_date date,
    semester_type          SMALLINT,
    course                 INTEGER,
    CONSTRAINT pk_dean_statements PRIMARY KEY (id)
);

CREATE TABLE dean_student_changes
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id           BIGINT,
    status              SMALLINT,
    created             TIMESTAMP WITHOUT TIME ZONE,
    updated             TIMESTAMP WITHOUT TIME ZONE,
    student_id          BIGINT,
    student_change_type SMALLINT,
    reason_id           BIGINT,
    order_number        VARCHAR(255),
    order_text          VARCHAR(255),
    msg                 VARCHAR(2048),
    msg_date            TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_dean_student_changes PRIMARY KEY (id)
);

CREATE TABLE dean_student_field_changes
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id         BIGINT,
    status            SMALLINT,
    created           TIMESTAMP WITHOUT TIME ZONE,
    updated           TIMESTAMP WITHOUT TIME ZONE,
    student_change_id BIGINT,
    changed_field     VARCHAR(255),
    old_value         VARCHAR(255),
    new_value         VARCHAR(255),
    CONSTRAINT pk_dean_student_field_changes PRIMARY KEY (id)
);

CREATE TABLE dean_student_language
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id BIGINT,
    status    SMALLINT,
    created   TIMESTAMP WITHOUT TIME ZONE,
    updated   TIMESTAMP WITHOUT TIME ZONE,
    name      VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_dean_student_language PRIMARY KEY (id)
);

CREATE TABLE dean_students
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id           BIGINT,
    status              SMALLINT,
    created             TIMESTAMP WITHOUT TIME ZONE,
    updated             TIMESTAMP WITHOUT TIME ZONE,
    surname    VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    payment_type        SMALLINT,
    sex                 INTEGER,
    phone_id            BIGINT,
    email               VARCHAR(255),
    birth_date          date                                    NOT NULL,
    education_string    VARCHAR(255)                            NOT NULL,
    institution         BIGINT,
    education_year_end  INTEGER,
    job                 VARCHAR(255),
    job_experience      DOUBLE PRECISION,
    enrollment_date     date,
    re_enroll           BOOLEAN                                 NOT NULL,
    unbound             VARCHAR(255),
    state_support       BOOLEAN                                 NOT NULL,
    move                BOOLEAN                                 NOT NULL,
    enroll_date         date,
    full_namel          VARCHAR(255)                            NOT NULL,
    first_namel         VARCHAR(255)                            NOT NULL,
    maiden_name         VARCHAR(255),
    case_no             BIGINT,
    document_number     BIGINT,
    address_id          BIGINT,
    reg_address_id      BIGINT,
    citizenship_id      BIGINT                                  NOT NULL,
    student_language_id BIGINT                                  NOT NULL,
    passport_id         BIGINT,
    birth_place         VARCHAR(255)                            NOT NULL,
    benefits            VARCHAR(255),
    spez_id             BIGINT,
    group_id            BIGINT                                  NOT NULL,
    need_hostel         BOOLEAN                                 NOT NULL,
    hostel_room_id      BIGINT,
    check_in_date       date,
    eviction_date       date,
    approved            BOOLEAN DEFAULT FALSE                   NOT NULL,
    photo_url           VARCHAR(255),
    CONSTRAINT pk_dean_students PRIMARY KEY (id)
);

CREATE TABLE dean_study_plan
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id       BIGINT,
    status          SMALLINT,
    created         TIMESTAMP WITHOUT TIME ZONE,
    updated         TIMESTAMP WITHOUT TIME ZONE,
    group_id        BIGINT                                  NOT NULL,
    year_start      INTEGER,
    year_end        INTEGER,
    semester_number INTEGER,
    semester        VARCHAR(255),
    course          INTEGER,
    exam_type_id  BIGINT,
    discipline_id BIGINT,
    teacher_id    BIGINT,
    hours         INTEGER,
    test_points   DECIMAL,
    CONSTRAINT pk_dean_study_plan PRIMARY KEY (id)
);

CREATE TABLE dean_teacher_degrees
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id  BIGINT,
    status     SMALLINT,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255)                            NOT NULL,
    hour_price DOUBLE PRECISION,
    CONSTRAINT pk_dean_teacher_degrees PRIMARY KEY (id)
);

CREATE TABLE dean_teacher_department_merge
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id     BIGINT,
    status        SMALLINT,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    teacher_id    BIGINT                                  NOT NULL,
    department_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_dean_teacher_department_merge PRIMARY KEY (id)
);

CREATE TABLE dean_teachers
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    source_id  BIGINT,
    status     SMALLINT,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    surname    VARCHAR(255)                            NOT NULL,
    name       VARCHAR(255)                            NOT NULL,
    patronymic VARCHAR(255)                            NOT NULL,
    degree_id  BIGINT,
    photo_url  VARCHAR(255),
    CONSTRAINT pk_dean_teachers PRIMARY KEY (id)
);

ALTER TABLE dean_parents
    ADD CONSTRAINT uc_dean_parents_phone UNIQUE (phone_id);

ALTER TABLE dean_students
    ADD CONSTRAINT uc_dean_students_phone UNIQUE (phone_id);

ALTER TABLE dean_absences
    ADD CONSTRAINT FK_DEAN_ABSENCES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES dean_departments (id);

ALTER TABLE dean_absences
    ADD CONSTRAINT FK_DEAN_ABSENCES_ON_DISCIPLINE FOREIGN KEY (discipline_id) REFERENCES dean_disciplines (id);

ALTER TABLE dean_absences
    ADD CONSTRAINT FK_DEAN_ABSENCES_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_absences
    ADD CONSTRAINT FK_DEAN_ABSENCES_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES dean_teachers (id);

ALTER TABLE dean_departments
    ADD CONSTRAINT FK_DEAN_DEPARTMENTS_ON_FACULTY FOREIGN KEY (faculty_id) REFERENCES dean_faculties (id);

ALTER TABLE dean_deviations
    ADD CONSTRAINT FK_DEAN_DEVIATIONS_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_disciplines
    ADD CONSTRAINT FK_DEAN_DISCIPLINES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES dean_departments (id);

ALTER TABLE dean_educations
    ADD CONSTRAINT FK_DEAN_EDUCATIONS_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_gratitude
    ADD CONSTRAINT FK_DEAN_GRATITUDE_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_groups
    ADD CONSTRAINT FK_DEAN_GROUPS_ON_FACULTY FOREIGN KEY (faculty_id) REFERENCES dean_faculties (id);

ALTER TABLE dean_groups
    ADD CONSTRAINT FK_DEAN_GROUPS_ON_SPEC FOREIGN KEY (spec_id) REFERENCES dean_specialities (id);

ALTER TABLE dean_parents
    ADD CONSTRAINT FK_DEAN_PARENTS_ON_PHONE FOREIGN KEY (phone_id) REFERENCES dean_phones (id);

ALTER TABLE dean_parents
    ADD CONSTRAINT FK_DEAN_PARENTS_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_penalties
    ADD CONSTRAINT FK_DEAN_PENALTIES_ON_REASON FOREIGN KEY (reason_id) REFERENCES dean_reasons (id);

ALTER TABLE dean_penalties
    ADD CONSTRAINT FK_DEAN_PENALTIES_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_rooms
    ADD CONSTRAINT FK_DEAN_ROOMS_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES dean_departments (id);

ALTER TABLE dean_specialities
    ADD CONSTRAINT FK_DEAN_SPECIALITIES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES dean_departments (id);

ALTER TABLE dean_specializations
    ADD CONSTRAINT FK_DEAN_SPECIALIZATIONS_ON_QUALIFICATION FOREIGN KEY (qualification_id) REFERENCES dean_qualifications (id);

ALTER TABLE dean_specializations
    ADD CONSTRAINT FK_DEAN_SPECIALIZATIONS_ON_SPEC FOREIGN KEY (spec_id) REFERENCES dean_specialities (id);

ALTER TABLE dean_statements
    ADD CONSTRAINT FK_DEAN_STATEMENTS_ON_STUDY_PLAN FOREIGN KEY (study_plan_id) REFERENCES dean_study_plan (id);

ALTER TABLE dean_statement_student_merge
    ADD CONSTRAINT FK_DEAN_STATEMENT_STUDENT_MERGE_ON_STATEMENT FOREIGN KEY (statement_id) REFERENCES dean_statements (id);

ALTER TABLE dean_statement_student_merge
    ADD CONSTRAINT FK_DEAN_STATEMENT_STUDENT_MERGE_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_statement_student_merge
    ADD CONSTRAINT FK_DEAN_STATEMENT_STUDENT_MERGE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES dean_teachers (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES dean_addresses (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_CITIZENSHIP FOREIGN KEY (citizenship_id) REFERENCES dean_citizenship (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_GROUP FOREIGN KEY (group_id) REFERENCES dean_groups (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_HOSTEL_ROOM FOREIGN KEY (hostel_room_id) REFERENCES dean_hostel_rooms (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_INSTITUTION FOREIGN KEY (institution) REFERENCES dean_institutions (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_PASSPORT FOREIGN KEY (passport_id) REFERENCES dean_passports (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_PHONE FOREIGN KEY (phone_id) REFERENCES dean_phones (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_REG_ADDRESS FOREIGN KEY (reg_address_id) REFERENCES dean_addresses (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_SPEZ FOREIGN KEY (spez_id) REFERENCES dean_specializations (id);

ALTER TABLE dean_students
    ADD CONSTRAINT FK_DEAN_STUDENTS_ON_STUDENT_LANGUAGE FOREIGN KEY (student_language_id) REFERENCES dean_student_language (id);

ALTER TABLE dean_student_changes
    ADD CONSTRAINT FK_DEAN_STUDENT_CHANGES_ON_REASON FOREIGN KEY (reason_id) REFERENCES dean_reasons (id);

ALTER TABLE dean_student_changes
    ADD CONSTRAINT FK_DEAN_STUDENT_CHANGES_ON_STUDENT FOREIGN KEY (student_id) REFERENCES dean_students (id);

ALTER TABLE dean_student_field_changes
    ADD CONSTRAINT FK_DEAN_STUDENT_FIELD_CHANGES_ON_ID FOREIGN KEY (id) REFERENCES dean_student_changes (id);

ALTER TABLE dean_student_field_changes
    ADD CONSTRAINT FK_DEAN_STUDENT_FIELD_CHANGES_ON_STUDENT_CHANGE FOREIGN KEY (student_change_id) REFERENCES dean_student_changes (id);

ALTER TABLE dean_study_plan
    ADD CONSTRAINT FK_DEAN_STUDY_PLAN_ON_DISCIPLINE FOREIGN KEY (discipline_id) REFERENCES dean_disciplines (id);

ALTER TABLE dean_study_plan
    ADD CONSTRAINT FK_DEAN_STUDY_PLAN_ON_EXAM_TYPE FOREIGN KEY (exam_type_id) REFERENCES dean_exams (id);

ALTER TABLE dean_study_plan
    ADD CONSTRAINT FK_DEAN_STUDY_PLAN_ON_GROUP FOREIGN KEY (group_id) REFERENCES dean_groups (id);

ALTER TABLE dean_study_plan
    ADD CONSTRAINT FK_DEAN_STUDY_PLAN_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES dean_teachers (id);

ALTER TABLE dean_teachers
    ADD CONSTRAINT FK_DEAN_TEACHERS_ON_DEGREE FOREIGN KEY (degree_id) REFERENCES dean_teacher_degrees (id);

ALTER TABLE dean_teacher_department_merge
    ADD CONSTRAINT FK_DEAN_TEACHER_DEPARTMENT_MERGE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES dean_departments (id);

ALTER TABLE dean_teacher_department_merge
    ADD CONSTRAINT FK_DEAN_TEACHER_DEPARTMENT_MERGE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES dean_teachers (id);