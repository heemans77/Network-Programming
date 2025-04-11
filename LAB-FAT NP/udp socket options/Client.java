import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true); 
            socket.setReuseAddress(true);
            socket.setReceiveBufferSize(1024 * 1024);
            socket.setSendBufferSize(1024 * 1024);
            socket.setSoTimeout(5000);

            InetAddress address = InetAddress.getByName(SERVER_IP);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter numbers separated by commas (e.g., 3,5,1,2): ");
            String userInput = reader.readLine();

            byte[] sendData = userInput.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, SERVER_PORT);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                socket.receive(receivePacket);
                String sortedNumbers = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Sorted numbers from server: " + sortedNumbers);
            } catch (SocketTimeoutException ste) {
                System.out.println("Timeout reached, server didn't respond: " + ste.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
