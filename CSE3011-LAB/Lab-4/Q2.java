import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Q2 {
    public static void main(String[] args) {
        // File paths
        String sourceFile = "file1.txt";
        String destinationFile = "file2.txt";

        try (FileReader reader = new FileReader(sourceFile);
             FileWriter writer = new FileWriter(destinationFile)) {

            reader.skip(10);

            char[] buffer = new char[40]; // 50 - 10
            int charactersRead = reader.read(buffer, 0, 40); 

            for (int i = 0; i < 5; i++) {
                writer.write(buffer, 0, charactersRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
