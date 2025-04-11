import java.io.OutputStream;
import java.io.IOException;

public class test {

    public static void main(String[] args) {
        final int n = 72, start = 33, end = 126;
        int cs = start;
        byte[] line = new byte[n];

        try (OutputStream os = System.out) {
            while (true) {
                for (int i = 0; i < n; i++) {
                    int val = cs + i;
                    if (val > end) val = start + (val - end - 1);
                    line[i] = (byte) val;
                }

                os.write(line);
                os.write('\n');

                if (++cs > end) cs = start;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
