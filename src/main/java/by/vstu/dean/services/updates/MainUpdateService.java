package by.vstu.dean.services.updates;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainUpdateService {

    private final List<IUpdateExecutor> services = new ArrayList<>();

    @PostConstruct
    @Order(12)
    public void update() {


        new Thread(() -> {
            System.err.println("Update started!");
            Thread.currentThread().setName("Update");

            while(Thread.getAllStackTraces().keySet().stream().anyMatch(p -> p.getName().contains("Migration"))) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            services.forEach(IUpdateExecutor::update);

            System.err.println("Update done!");

        }).start();


    }

    public void registerService(IUpdateExecutor executor) {
        this.services.add(executor);
    }

}
