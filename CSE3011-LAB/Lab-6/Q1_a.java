import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Q1_a {

    // Method using URL.openStream()
    public static void stream(String urlStr) {
        try {
            URL url = new URL(urlStr);
            try (InputStream is = url.openStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String ln;
                while ((ln = br.readLine()) != null) {
                    System.out.println(ln);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method using URL.openConnection()
    public static void conn(String urlStr) {
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            try (InputStream is = con.getInputStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String ln;
                while ((ln = br.readLine()) != null) {
                    System.out.println(ln);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "http://example.com";
        stream(url);
        System.out.println("--------------------------------------------");
        conn(url);
    }
}
