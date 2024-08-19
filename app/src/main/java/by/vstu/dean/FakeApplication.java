package by.vstu.dean;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FakeApplication {

    public static void main(String[] args) {
        throw new RuntimeException("Use real main-class for running");
    }

}
