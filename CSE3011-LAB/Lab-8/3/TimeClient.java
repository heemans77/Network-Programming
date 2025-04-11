import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int port = 1234;

        try (Socket socket = new Socket(hostName, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverTime = in.readLine();
            System.out.println("Server time: " + serverTime);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
