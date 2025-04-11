import javax.net.ssl.*;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "serverkeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        System.setProperty("javax.net.ssl.trustStore", "servertruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(1234);
        serverSocket.setNeedClientAuth(true);

        System.out.println("Server started and ready to accept clients!");

        try (SSLSocket clientSocket = (SSLSocket) serverSocket.accept()) {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received from client: " + line);
                writer.println("Echo: " + line);
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
