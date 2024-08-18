package by.vstu.dean.old.services.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainUpdateService {

    private final List<IUpdateExecutor> services = new ArrayList<>();

    public void update() {


        System.err.println("Update started!");
        Thread.currentThread().setName("Update");

        services.forEach(IUpdateExecutor::update);


    }

    public void registerService(IUpdateExecutor executor) {
        this.services.add(executor);
    }

}
