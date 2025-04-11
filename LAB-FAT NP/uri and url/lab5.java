import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class lab5 {
    public static void main(String[] args) throws Exception {
        // URI and URL example
        URL url = new URL("http://www.example.com");
        URI uri = url.toURI();
        System.out.println("URL: " + url);
        System.out.println("URI: " + uri);

        // Convert URL to URI
        URI convertedUri = convertUrlToUri(url);
        System.out.println("Converted URI: " + convertedUri);

        // Find network interfaces in the specified IP range
        List<NetworkInterface> interfacesInRange = findInterfacesInRange("192.168.1.1", "192.168.1.150");
        System.out.println("Interfaces in range:");
        interfacesInRange.forEach(intf -> System.out.println(intf.getName()));

        // Explore NetworkInterface class methods
        exploreNetworkInterfaceMethods();
    }

    public static URI convertUrlToUri(URL url) throws URISyntaxException {
        return url.toURI();
    }

    public static List<NetworkInterface> findInterfacesInRange(String startIp, String endIp) throws SocketException {
        long start = ipToLong(startIp);
        long end = ipToLong(endIp);

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        List<NetworkInterface> result = new ArrayList<>();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet4Address) {
                    long ip = ipToLong(address.getHostAddress());
                    if (ip >= start && ip <= end) {
                        result.add(networkInterface);
                        break;
                    }
                }
            }
        }

        return result;
    }

    private static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }

    public static void exploreNetworkInterfaceMethods() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        if (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            System.out.println("Exploring methods of NetworkInterface: " + networkInterface.getName());
            System.out.println("Name: " + networkInterface.getName());
            System.out.println("Display name: " + networkInterface.getDisplayName());
            System.out.println("MTU: " + networkInterface.getMTU());
            System.out.println("Loopback: " + networkInterface.isLoopback());
            System.out.println("Up: " + networkInterface.isUp());
            System.out.println("Virtual: " + networkInterface.isVirtual());
            System.out.println("Supports multicast: " + networkInterface.supportsMulticast());
            System.out.println("Hardware address (MAC): " + Arrays.toString(networkInterface.getHardwareAddress()));
            
        } else {
            System.out.println("No network interfaces available.");
        }
    }
}
