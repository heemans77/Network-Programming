import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.*;

class PrimeCallable implements Callable<String> {
    private Socket socket = null;

    public PrimeCallable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String call() throws Exception {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BigInteger prime = BigInteger.probablePrime(64, new SecureRandom());
            out.println(prime);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Done";
    }
}

public class PrimeServerCallable {
    private static final int NTHREADS = 10;
    private static final ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Callable<String> task = new PrimeCallable(socket);
                executor.submit(task);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
