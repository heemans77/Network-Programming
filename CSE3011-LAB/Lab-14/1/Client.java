import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(3000); 
            InetAddress address = InetAddress.getByName(SERVER_IP);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while (true) {
                System.out.print("Enter text: ");
                line = reader.readLine();
                if ("quit".equalsIgnoreCase(line)) {
                    break;
                }

                byte[] sendData = line.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, SERVER_PORT);
                socket.send(sendPacket);
                
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try {
                    socket.receive(receivePacket);
                    String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Server echoed: " + received);
                } catch (SocketTimeoutException ste) {
                    System.out.println("Timeout reached: " + ste.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
