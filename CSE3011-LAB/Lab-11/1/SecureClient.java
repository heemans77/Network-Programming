import javax.net.ssl.*;
import java.io.*;

public class SecureClient {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int port = 1234;

        System.setProperty("javax.net.ssl.trustStore", "clienttruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(hostName, port);

            socket.setTcpNoDelay(true);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter text (type 'exit' to quit):");
            String userInput;
            while (!(userInput = stdIn.readLine()).equalsIgnoreCase("exit")) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }

            socket.close();
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
