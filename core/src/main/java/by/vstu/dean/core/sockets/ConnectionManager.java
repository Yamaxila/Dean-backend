package by.vstu.dean.core.sockets;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings({"LombokGetterMayBeUsed", "unused"})
public class ConnectionManager<S extends ConnectionModel> {

    @Getter
    private final List<S> connections = new ArrayList<>();

    public boolean register(SocketServerRunnable runnable, S connectionModel) {
        if(this.hasRegistered(connectionModel.getId()))
            return false;

        connectionModel.setRunnable(runnable);
        connectionModel.setCreated(LocalDateTime.now());
        connectionModel.setUpdated(LocalDateTime.now());

        this.connections.add(connectionModel);

        return true;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean unregister(S connectionModel) {
        if(!this.hasRegistered(connectionModel.getId()))
            return false;

        this.connections.remove(connectionModel);

        return true;
    }

    public boolean unregisterByRunnable(SocketServerRunnable runnable) {
        S model = this.connections.stream().filter(p -> p.getRunnable().equals(runnable)).findFirst().orElse(null);

        if(model == null)
            return false;

        this.unregister(model);

        return true;
    }

    public void send(S connectionModel, String message) {
        connectionModel.getRunnable().sendMessage(message);
    }

    public void send2All(String message) {
        this.connections.forEach(conn -> conn.getRunnable().sendMessage(message));
    }

    public S findById(String id) {
        return this.connections.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean hasRegistered(String id) {
        return this.findById(id) != null;
    }


}
