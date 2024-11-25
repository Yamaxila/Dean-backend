package by.vstu.dean.tests;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DeanBackendApplication.class)
public class NotSimpleRepoTest {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        List<StudentModel> studentModels = this.studentService.getAll();
        System.out.println(studentModels);
        this.studentService.parallelSaveAll(studentModels);
    }

}
