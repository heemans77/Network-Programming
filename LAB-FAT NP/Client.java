import java.io.*;
import java.net.*;

class EchoClient implements Runnable {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            // Example message to send
            out.println("Hello Server!");

            String response = in.readLine();
            System.out.println("Received echo: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Client {
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 7814;

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new EchoClient(host, port));
            thread.start();
        }
    }
}
