import java.io.FileOutputStream;
import java.io.IOException;

public class Performance {

    public static void main(String[] args) {
        final int nCharsPerLine = 72;
        final int start = 33;
        final int end = 126;
        final int iters = 200; 
        String fileByteByByte = "bytes.txt";
        String fileByteArray = "chars.txt";

        long startTimeBB = System.currentTimeMillis();
        writeAsciiArtBB(fileByteByByte, nCharsPerLine, start, end, iters);
        long endTimeBB = System.currentTimeMillis();
        long durationBB = endTimeBB - startTimeBB;

        long startTimeBA = System.currentTimeMillis();
        writeAsciiArtBA(fileByteArray, nCharsPerLine, start, end, iters);
        long endTimeBA = System.currentTimeMillis();
        long durationBA = endTimeBA - startTimeBA;

        System.out.println("Write Int method took: " + durationBB + " ms");
        System.out.println("Byte method took: " + durationBA + " ms");
    }

    private static void writeAsciiArtBB(String file, int n, int start, int end, int iters) {
        int cs = start;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (int j = 0; j < iters; j++) {
                for (int i = 0; i < n; i++) {
                    int val = cs + i;
                    if (val > end) val = start + (val - end - 1);
                    fos.write(val);
                }
                fos.write('\n'); 
                if (++cs > end) cs = start;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeAsciiArtBA(String file, int n, int start, int end, int iters) {
        int cs = start;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (int j = 0; j < iters; j++) {
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
}
