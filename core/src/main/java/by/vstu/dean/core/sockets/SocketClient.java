package by.vstu.dean.core.sockets;

import java.io.*;
import java.net.Socket;
import java.util.function.Function;

@SuppressWarnings({"unused", "CallToPrintStackTrace", "BusyWait"})
public class SocketClient {
    private final String serverAddress;
    private final int port;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private final boolean reconnect;
    private final long reconnectTimeout = 5000;
    private final String key;
    private boolean running = true;

    public SocketClient(String serverAddress, int port, String key, boolean reconnect) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.key = key;
        this.reconnect = reconnect;
    }

    public void connect() throws IOException {
        this.running = true;
        socket = new Socket(serverAddress, port);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void disconnect() throws IOException {
        this.running = false;
        if (reader != null) reader.close();
        if (writer != null) writer.close();
        if (socket != null) socket.close();
    }

    public void sendUnsafeMessage(String message) {
        this.writer.println(message);
        this.writer.flush();
    }

    public void sendMessage(String message) {
        this.writer.println(SocketSecurity.encrypt(message, this.key));
        this.writer.flush();
    }

    public void sendFile(File file) throws IOException {
        this.sendUnsafeMessage("file:start");
        FileInputStream fileInputStream = new FileInputStream(file);
        DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());

        dos.writeLong(file.length());

        byte[] buffer = new byte[4 * 1024];
        while (fileInputStream.read(buffer) != -1) {
            byte[] temp = SocketSecurity.encrypt(buffer, this.key);

            dos.write(temp, 0, temp.length);
            dos.flush();
        }
            fileInputStream.close();
        dos.flush();
    }

    public void setCallback(Function<String, String> function) {
        new Thread(() -> {
            Thread.currentThread().setName("Callback thread");
            String line;
            while (this.running) {
                try {
                    line = this.reader.readLine();
                    if(line != null) {
                        line = SocketSecurity.decrypt(line, this.key);
                        function.apply(line);
                        if(line.equalsIgnoreCase("kill"))
                            break;
                    } else {
                        if(this.reconnect) {
                            int attempt = 0;
                            do {
                                Thread.sleep(this.reconnectTimeout);
                                System.out.println("SocketClient: reconnecting to server... Attempt: " + ++attempt);
                                this.connect();
                            } while(!this.socket.isConnected() && this.running);
                        }
                        break; // Handle server shutdown
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }


}
