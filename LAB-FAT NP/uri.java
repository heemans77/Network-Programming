import java.net.URL;
import java.net.URLConnection;

public class uri {

    public static void configConn(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            

            System.out.println("Initial configurations:");
            System.out.println("AllowUserInteraction: " + conn.getAllowUserInteraction());
            System.out.println("DoInput: " + conn.getDoInput());
            System.out.println("DoOutput: " + conn.getDoOutput());
            System.out.println("UseCaches: " + conn.getUseCaches());
            System.out.println("ConnectTimeout: " + conn.getConnectTimeout());
            System.out.println("ReadTimeout: " + conn.getReadTimeout());
            
            conn.setAllowUserInteraction(true);
            conn.setDoInput(false); 
            conn.setDoOutput(true); 
            conn.setUseCaches(false);
            conn.setConnectTimeout(5000000);
            conn.setReadTimeout(2000000);
            
            // Print connection configurations after setting
            System.out.println("\nConfigurations after setting:");
            System.out.println("AllowUserInteraction: " + conn.getAllowUserInteraction());
            System.out.println("DoInput: " + conn.getDoInput());
            System.out.println("DoOutput: " + conn.getDoOutput());
            System.out.println("UseCaches: " + conn.getUseCaches());
            System.out.println("ConnectTimeout: " + conn.getConnectTimeout());
            System.out.println("ReadTimeout: " + conn.getReadTimeout());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "http://example.com";
        configConn(url);
    }
}
