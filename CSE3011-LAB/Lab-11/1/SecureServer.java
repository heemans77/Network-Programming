import javax.net.ssl.*;
import java.io.*;
import java.net.*;

public class SecureServer {
    public static void main(String[] args) {
        int port = 1234;

        System.setProperty("javax.net.ssl.keyStore", "serverkeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");

        try {
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(port);

            System.out.println("Secure server is listening on port " + port);

            while (true) {
                try (SSLSocket clientSocket = (SSLSocket) serverSocket.accept()) {
                    clientSocket.setReceiveBufferSize(1024 * 1024); 
                    clientSocket.setSendBufferSize(1024 * 1024); 
                    clientSocket.setSoTimeout(5000); 
                    clientSocket.setSoLinger(true, 10); 
                    clientSocket.setKeepAlive(true);
                    clientSocket.setOOBInline(true);
                    clientSocket.setTrafficClass(0x04);

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        out.println(inputLine.toUpperCase()); // Echo back in upper case
                    }
                } catch (IOException e) {
                    System.out.println("Exception in communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
