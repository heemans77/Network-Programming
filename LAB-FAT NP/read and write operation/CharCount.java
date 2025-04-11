import java.io.FileReader;
import java.io.IOException;

public class CharCount {
    public static void main(String[] args) {
        int characterCount = 0;
        try (FileReader reader = new FileReader("test.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                characterCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of chars : " + characterCount);
    }
}
