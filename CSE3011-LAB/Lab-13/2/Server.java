import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "serverkeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        System.setProperty("javax.net.ssl.trustStore", "servertruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust thread pool size as necessary

        try {
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(PORT);

            System.out.println("SSL Server started on port " + PORT);
            System.out.println("Waiting for clients...");

            while (true) {
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                executor.submit(() -> handleClient(clientSocket));
            }
        } catch (Exception e) {
           
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
    private static void handleClient(SSLSocket clientSocket) {
        try {
            System.out.println("Connected to client " + clientSocket.getRemoteSocketAddress());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            String line;
            while ((line = reader.readLine()) != null) {
                        System.out.println("Received from client: " + line);
                        writer.println("Echo: " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                } finally {
                    try {
                        clientSocket.close();
                        System.out.println("Client connection closed: " + clientSocket.getRemoteSocketAddress());
                    } catch (Exception e) {
                        System.err.println("Error closing client socket: " + e.getMessage());
                    }
                }
            }
            }
            
            