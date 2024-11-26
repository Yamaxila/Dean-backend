package by.vstu.old.dean.services.update;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainUpdateService {

    @Autowired
    private final List<IUpdateExecutor> services = new ArrayList<>();

    public void update() {
        System.err.println("Update started!");
        Thread.currentThread().setName("Update");

        services.forEach(IUpdateExecutor::update);
    }

    public void registerService(IUpdateExecutor executor) {
        if (services.contains(executor))
            return;
        this.services.add(executor);
    }

}
