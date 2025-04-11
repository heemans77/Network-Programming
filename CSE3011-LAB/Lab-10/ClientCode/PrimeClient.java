import java.io.*;
import java.net.*;

public class PrimeClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 12345;

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        ) {
            System.out.println("Server says: " + in.readLine());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}
