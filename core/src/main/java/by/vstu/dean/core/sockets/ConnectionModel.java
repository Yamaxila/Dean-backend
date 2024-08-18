package by.vstu.dean.core.sockets;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectionModel<S> {

    protected String method;

    protected transient SocketServerRunnable runnable;
    protected String id;
    protected S object;

    protected transient LocalDateTime created;
    protected transient LocalDateTime updated;


}
