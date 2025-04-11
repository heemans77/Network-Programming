import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();

            String newtext = oldtext.replaceAll("happy", "sad");

            BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
            writer.write(newtext);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
