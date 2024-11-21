package by.vstu.dean.core.sockets;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SocketServer {

    private String key;
    private IBaseSocketService service;
    private ExecutorService pool;
    private boolean isRunning = true;

    public void init(SocketServerRunnable runnable, IBaseSocketService service, int port, String key) {
        new Thread(() -> {
            Thread.currentThread().setName("SocketServer");

            try (ServerSocket listener = new ServerSocket(port)) {
                System.out.println("Socket server started on port " + port              );
                this.pool = Executors.newFixedThreadPool(300);

                //External Loop
                while (this.isRunning) {
                    runnable.setKey(key);
                    runnable.setService(service);
                    runnable.setSocket(listener.accept());
                    this.pool.execute(runnable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent(ContextClosedEvent contextClosedEvent) {
        this.isRunning = false;
        this.pool.shutdown();
    }
}
