import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ByteGen {

    public static void main(String[] args) {
        final int numberOfCharactersPerLine = 72;
        final int start = 33;
        final int end = 126;
        final int iterations = 200; 
        String fileName = "bytes.txt"; 

        writeAsciiArtToFile(fileName, numberOfCharactersPerLine, start, end, iterations);
        readAndPrintFile(fileName);
    }

    private static void writeAsciiArtToFile(String fileName, int n, int start, int end, int iterations) {
        int cs = start;

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (int j = 0; j < iterations; j++) {
                byte[] line = new byte[n];
                for (int i = 0; i < n; i++) {
                    int val = cs + i;
                    if (val > end) val = start + (val - end - 1);
                    line[i] = (byte) val;
                }

                fos.write(line); 
                fos.write('\n'); 

                if (++cs > end) cs = start;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAndPrintFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             OutputStream os = System.out) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}