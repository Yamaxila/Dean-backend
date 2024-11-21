package by.vstu.dean.tests;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DeanBackendApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LazyTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void contextLoad() {

        StudentModel s1 = this.studentService.getById(1L).orElse(null);
        StudentModel s2 = this.studentService.getRepo().findById(1L).orElse(null);

        System.out.println(s1);
        System.out.println(s2);

    }
}
