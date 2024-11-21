package by.vstu.dean.core.sockets;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Getter
@Slf4j
public abstract class SocketServerRunnable implements Runnable {

    @Setter
    private Socket socket;

    @Setter
    private String key;
    @Setter
    private IBaseSocketService service;

    private BufferedReader br;
    private OutputStream out;

    private boolean bytesTransfer = false;

    @Override
    public void run() {
        log.debug("Connected: {}", socket);

        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = this.socket.getOutputStream();

            String line;
            File tempFile;
            do {

                if(this.bytesTransfer) {

                    tempFile = this.readRequest(new DataInputStream(this.socket.getInputStream()));
                    if(!tempFile.exists()) {
                        log.error("file does not exist: {}", tempFile.getAbsolutePath());
                        continue;
                    }

                    this.service.handleRequest(this, tempFile);
                    this.bytesTransfer = false;
                    continue;
                }

                line = this.readRequest(br);

                if(line == null)
                    break;

                if(line.startsWith("file:start")) {
                    this.bytesTransfer = true;
                    continue;
                }

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
            log.debug("Connection closed for {}", this.socket);
        } catch (Exception ignored) {
            this.onError();
        }
    }

    public void sendMessage(String message) {
        try {
            this.out.write(SocketSecurity.encrypt(message, this.key).getBytes(StandardCharsets.UTF_8));
            this.out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private File readRequest(DataInputStream din) throws IOException {

        FileOutputStream fos = new FileOutputStream("./tempfile");

        int bytes;
        long size = din.readLong();
        byte[] buffer = new byte[4 * 1024];
        while (size > 0
                && (bytes = din.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {

            fos.write(buffer, 0, bytes);
            size -= bytes;
        }
        fos.close();
        return new File("./tempfile") ;
    }


    private String readRequest(BufferedReader din) {
        String line;

        try {
            line = din.readLine();
        } catch (IOException ignored) {
            this.onError();
            return null;
        }

        return line == null ? null : SocketSecurity.decrypt(line, this.key);
    }

    public abstract Object parse(String message);
    public abstract void onError();
}
