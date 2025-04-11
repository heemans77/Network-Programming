import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
 
public class EnableCipherSuites {
    public static void main(String[] args) throws Exception {
        // 1. Create SSLContext
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);
 
        // 2. Obtain SSLSocketFactory
        SSLSocketFactory factory = context.getSocketFactory();
 
        // 3. Create SSLSocket
        SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 443);
 
        // 4. Specify the cipher suites to be enabled
        String[] enabledCipherSuites = {
            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
            "TLS_RSA_WITH_AES_128_CBC_SHA"
        };
 
        // 5. Set the enabled cipher suites for this socket
        socket.setEnabledCipherSuites(enabledCipherSuites);
    }
}
 