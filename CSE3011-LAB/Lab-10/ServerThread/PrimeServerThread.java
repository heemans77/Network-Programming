import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.security.SecureRandom;

class PrimeThread extends Thread {
    private Socket socket = null;

    public PrimeThread(Socket socket) {
        super("PrimeThread");
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BigInteger prime = BigInteger.probablePrime(64, new SecureRandom());
            out.println(prime);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class PrimeServerThread {
    public static void main(String[] args) throws IOException {
        int portNumber = 12345;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new PrimeThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
