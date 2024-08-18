package by.vstu.dean.core.sockets;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Getter
public abstract class SocketServerRunnable implements Runnable {

//    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("yamaxila.sockets.debug", "false"));
//    private static final boolean DEBUG = true;

    private final Logger logger = LoggerFactory.getLogger(SocketServerRunnable.class);

    private ServerSocket serverSocket;
    @Setter
    private Socket socket;

    @Setter
    private String key;
    @Setter
    private IBaseSocketService service;

    private BufferedReader br;
    private PrintWriter out;


    @Override
    public void run() {
        this.logger.debug("Connected: {}", socket);

        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream());

            String line;
            do {
                line = this.readRequest(br);

                if(line == null)
                    break;

                Object obj = this.parse(line);
                String response;
                if(obj instanceof String string)
                    response = string;
                else
                    response = this.service.handleRequest(this, obj);

                this.sendMessage(response);
            } while (this.socket.isConnected());

            br.close();
            socket.close();

            this.onError();
            this.logger.debug("Connection closed for {}", this.socket);
        } catch (Exception ignored) {
            this.onError();
        }
    }

    public void sendMessage(String message) {
        this.out.println(SocketSecurity.encrypt(message, this.key));
        this.out.flush();
    }

    private String readRequest(BufferedReader din) {
        String line;

        try {
            line = din.readLine();
        } catch (IOException ignored) {
            this.onError();
            return null;
        }




        return line == null ? null :     SocketSecurity.decrypt(line, this.key);
    }

    public abstract Object parse(String message);
    public abstract void onError();
}
