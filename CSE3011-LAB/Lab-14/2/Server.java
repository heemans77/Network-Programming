import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP Sorting Server is running on port " + PORT);
            
            byte[] buf = new byte[256]; 
            
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String received = new String(packet.getData(), 0, packet.getLength());
                    int[] numbers = Arrays.stream(received.split(","))
                                          .mapToInt(Integer::parseInt)
                                          .toArray();
                    Arrays.sort(numbers);
                    String sortedNumbers = Arrays.toString(numbers);

                    byte[] sendData = sortedNumbers.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length, packet.getAddress(), packet.getPort()
                    );
                    socket.send(sendPacket);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Server failed to open local port " + PORT);
        }
    }
}
