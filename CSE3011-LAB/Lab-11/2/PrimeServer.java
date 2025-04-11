import javax.net.ssl.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.util.concurrent.*;

public class PrimeServer {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "serverkeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");

        ExecutorService pool = Executors.newFixedThreadPool(10);
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try (SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(PORT)) {
            System.out.println("Prime Server is listening on port " + PORT);

            while (true) {
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                pool.submit(new PrimeTask(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }

    private static class PrimeTask implements Callable<Void> {
        private SSLSocket socket;

        public PrimeTask(SSLSocket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws Exception {
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String inputLine = in.readLine(); // Read request from client
                if ("get_prime".equals(inputLine)) {
                    BigInteger prime = BigInteger.probablePrime(64, new SecureRandom());
                    out.println(prime.toString());
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
            return null;
        }
    }
}
