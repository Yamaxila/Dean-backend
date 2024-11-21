package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, объединяющее все оценки по зачётным единицам.
 * Т.к. таблица в старом деканате статична, то можно реализовать всё в виде перечисления.
 */
@Getter
public enum EGrade implements BaseEnum<EGrade> {

    UNKNOWN("неизвестно", 0, new ExamType[]{ExamType.UNKNOWN, ExamType.EXAM, ExamType.TEST}, new int[]{0, 5, 10}),
    NOT_ALLOWED("не допущен", 1, new ExamType[]{ExamType.EXAM, ExamType.TEST}, new int[]{0, 5, 10}),
    NO_SHOW("неявка", 2, new ExamType[]{ExamType.EXAM, ExamType.TEST}, new int[]{0, 5, 10}),

    PASS("зачет", 3, new ExamType[]{ExamType.TEST}, new int[]{0, 5, 10}),
    NOT_PASS("незачет", 4, new ExamType[]{ExamType.TEST}, new int[]{0, 5, 10}),

    //Полученный балл на зачетной единице
    ONE("один", 11, new ExamType[]{ExamType.EXAM}, new int[]{5, 10}),
    TWO("два", 12, new ExamType[]{ExamType.EXAM}, new int[]{5, 10}),
    THREE("три", 13, new ExamType[]{ExamType.EXAM}, new int[]{5, 10}),
    FOUR("четыре", 14, new ExamType[]{ExamType.EXAM}, new int[]{5, 10}),
    FIVE("пять", 15, new ExamType[]{ExamType.EXAM}, new int[]{5, 10}),
    SIX("шесть", 16, new ExamType[]{ExamType.EXAM}, new int[]{10}),
    SEVEN("семь", 17, new ExamType[]{ExamType.EXAM}, new int[]{10}),
    EIGHT("восемь", 18, new ExamType[]{ExamType.EXAM}, new int[]{10}),
    NINE("девять", 19, new ExamType[]{ExamType.EXAM}, new int[]{10}),
    TEN("десять", 20, new ExamType[]{ExamType.EXAM}, new int[]{10});

    /*
     * Заголовок оценки
     */
    final String title;
    /*
     * Идентификатор оценки
     */
    final int id;
    /*
     * Поддерживаемые типы зачетной единицы
     */
    final ExamType[] supportExamTypes;
    /*
     * Поддерживаемые виды оценивания
     */
    final int[] supportGradeTypes;

    /**
     * Конструктор перечисления
     *
     * @param title             - заголовок
     * @param id                - идентификатор
     * @param supportExamTypes  - поддерживаемые типы зачетной единицы
     * @param supportGradeTypes - поддерживаемые виды оценивания
     */
    EGrade(String title, int id, ExamType[] supportExamTypes, int[] supportGradeTypes) {
        this.title = title;
        this.id = id;
        this.supportExamTypes = supportExamTypes;
        ;
        this.supportGradeTypes = supportGradeTypes;
    }

    public List<Integer> getSupportGradeTypes() {
        return Arrays.stream(this.supportGradeTypes).boxed().toList();
    }

    public List<ExamType> getSupportExamTypes() {
        return Arrays.stream(this.supportExamTypes).toList();
    }

    @Override
    public List<EGrade> getValues() {
        return Arrays.stream(EGrade.values()).toList();
    }

    public static EGrade findBy(int gradeType, ExamType examType, int grade) {

        //Мы толком не можем определить (кроме текста), что именно произошло
        //Я про неявку или не допуск к экзамену
        //Поэтому используем костыль
        if (grade == -1)
            return EGrade.NOT_ALLOWED;

        if (grade == -2)
            return EGrade.NO_SHOW;

        if (grade == -3)
            return EGrade.UNKNOWN;

        //Если это экзамен, то сразу увеличиваем на 10, чтобы сопоставить оценки с id
        if (examType.equals(ExamType.EXAM))
            grade += 10;

        //Если это зачет, то сразу увеличиваем на 2, чтобы сопоставить оценки с id
        if (examType.equals(ExamType.TEST))
            grade += 2;

        int finalGrade = grade;

        return Arrays.stream(EGrade.values())
                //Сразу фильтруем по типу зачетной единицы
                .filter(p -> p.getSupportExamTypes().contains(examType))
                //Потом фильтруем по виду оценивания
                .filter(p -> p.getSupportGradeTypes().contains(gradeType))
                //Ну и оценку сравниваем с id
                .filter(p -> p.id == finalGrade)
                //Возможно, передали невозможные данные
                .findFirst().orElse(EGrade.UNKNOWN);
    }
}
