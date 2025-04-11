import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    // Set socket options
                    clientSocket.setReceiveBufferSize(1024 * 1024); // 1MB
                    clientSocket.setSendBufferSize(1024 * 1024); // 1MB
                    clientSocket.setSoTimeout(5000); // 5 seconds
                    clientSocket.setSoLinger(true, 10); // 10 seconds
                    clientSocket.setKeepAlive(true);
                    clientSocket.setOOBInline(true);
                    clientSocket.setTrafficClass(0x04); // IPTOS_RELIABILITY

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        out.println(inputLine);
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port " + port);
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
