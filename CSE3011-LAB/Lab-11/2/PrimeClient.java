import javax.net.ssl.*;
import java.io.*;

public class PrimeClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "clienttruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try (SSLSocket socket = (SSLSocket) factory.createSocket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("get_prime");
            String response = in.readLine();
            System.out.println("Received prime number: " + response);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
