import javax.net.ssl.*;
import java.io.*;

public class SecureClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "clienttruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(HOST, PORT);

            String[] enabledCipherSuites = {
                "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
                "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
                "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
                "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
                "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                "TLS_RSA_WITH_AES_128_CBC_SHA"
            };
            socket.setEnabledCipherSuites(enabledCipherSuites);

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
