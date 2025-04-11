import java.io.*;
import java.net.*;

public class FileSenderClient {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int port = 1234;
        String filePath = "destination.txt";

        try (Socket socket = new Socket(hostName, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String content = readLastNChars(filePath, 500);
            if (content != null) {
                out.println(content);
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        }
    }


    private static String readLastNChars(String filePath, int n) {
        File file = new File(filePath);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long length = file.length();
            if (length < n) {
                n = (int) length;
            }
            randomAccessFile.seek(length - n);
            byte[] bytes = new byte[n];
            randomAccessFile.readFully(bytes);
            return new String(bytes);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
