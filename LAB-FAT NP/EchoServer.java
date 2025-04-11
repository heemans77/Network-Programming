import java.io.*;
import java.net.*;

class EchoHandler extends Thread {
    private Socket clientSocket;

    public EchoHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            clientSocket.setTcpNoDelay(true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7814)) {
            serverSocket.setReuseAddress(true);
            System.out.println("Echo Server");
            System.out.println("Server has successfully started listening on port 7814");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new EchoHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
