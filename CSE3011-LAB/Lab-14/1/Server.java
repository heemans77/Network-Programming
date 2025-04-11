import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP Echo Server is running on port " + PORT);
            
            while (true) {
                try {
                    byte[] buffer = new byte[1024];
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    socket.receive(request);
                    
                    String received = new String(request.getData(), 0, request.getLength());
                    System.out.println("Received: " + received);
                    
                    DatagramPacket response = new DatagramPacket(
                        request.getData(),
                        request.getLength(),
                        request.getAddress(),
                        request.getPort()
                    );
                    
                    socket.send(response);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Server failed to open local port " + PORT);
        }
    }
}
