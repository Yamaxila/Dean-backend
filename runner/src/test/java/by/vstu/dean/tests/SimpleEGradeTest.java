package by.vstu.dean.tests;

import by.vstu.dean.enums.EGrade;
import by.vstu.dean.enums.ExamType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
public class SimpleEGradeTest {


    private static int genRandomGrade(int max) {
        boolean isNegative = RandomUtils.nextInt(0, 4) == 0;
        return isNegative ? -RandomUtils.nextInt(1, 4) : RandomUtils.nextInt(1, max + 1);
    }

    private static int genRandomGradeSupport() {
        int g = RandomUtils.nextInt(1, 3);

        return switch (g) {
            case 1 -> 5;
            case 2 -> 10;
            default -> 0;
        };

    }

    private static ExamType getRandomExamType() {
        return RandomUtils.nextInt(0, 3) == 0 ? ExamType.TEST : ExamType.EXAM;
    }


    public static void main(String[] args) throws InterruptedException {
        log.info("GradeType\tGrade\tExamType\tResult");

        for (; ; ) {
            Thread.sleep(100L);
            int gradeType = genRandomGradeSupport();
            int grade = genRandomGrade(gradeType);
            ExamType examType = ExamType.EXAM;

            log.info("{}\t{}\t{}\t{}", gradeType, grade, examType.name(), EGrade.findBy(gradeType, examType, grade).name());
        }

    }


}
