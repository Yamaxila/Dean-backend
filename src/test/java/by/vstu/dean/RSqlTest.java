package by.vstu.dean;

import by.vstu.dean.services.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RSqlTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    void contextLoad() {
        System.out.println(teacherService.rsql("id>0"));
    }
}
