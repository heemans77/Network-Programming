import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Q3 {

    public static void main(String[] args) {
        String destinationFile = "destination.txt"; 
        try (FileOutputStream fos = new FileOutputStream(destinationFile);
             Writer writer = new OutputStreamWriter(fos)) {
            
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 7; j++) {

                    writer.write(Integer.toString((j + i) % 10));
                }

                if (i == 0) {
                    writer.write('x');
                }
                writer.write('\n');
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
