package by.vstu.dean.manager;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.core.sockets.SocketClient;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ServiceManagerSupport {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${manager.host}")
    private String managerHost;
    @Value("${manager.port}")
    private Integer managerPort;
    @Value("${manager.secretKey}")
    private String managerSecretKey;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private SocketClient socketClient;

//    @PostConstruct
    public void init() {
        log.info("Starting ServiceManager support component.");
        this.socketClient = new SocketClient(this.managerHost, this.managerPort, this.managerSecretKey, true);
        try {
            log.info("Connecting to {}:{}", this.managerHost, this.managerPort);
            this.socketClient.connect();
            log.debug("Connected! Sending hello message...");
            this.processWatcher.start();
        } catch (IOException e) {
            log.error("Error while connecting!", e);
        }
    }

    public void restart() {
        ApplicationArguments args = this.applicationContext.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            this.applicationContext.close();
            this.applicationContext = SpringApplication.run(DeanBackendApplication.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }

    private ManagerProcessConfiguration createConfiguration() {
        return new ManagerProcessConfiguration(this.appName);
    }

    public void handleRequest(String request) {

        switch (request) {
            case "status":
                this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("update", "", this.createConfiguration())));
                break;
            case "stop":
                log.info("Got shutdown request!");
                this.applicationContext.close();
                this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("stop", "true", this.createConfiguration())));
                this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("unregister", "", this.createConfiguration())));
                try {
                    this.socketClient.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "start":
                this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("start", "false", this.createConfiguration())));
                break;
            case "restart":
                log.info("Got restart request!");
                this.restart();
                this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("restart", "true", this.createConfiguration())));
                this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("un register", "", this.createConfiguration())));
                try {
                    this.socketClient.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }

    private final Thread processWatcher = new Thread(() -> {
        this.socketClient.sendMessage(new Gson().toJson(new ManagerRequestModel("register", "", this.createConfiguration())));
        this.socketClient.setCallback((s) -> {
            this.handleRequest(s);
            return "";
        });

    });

}
