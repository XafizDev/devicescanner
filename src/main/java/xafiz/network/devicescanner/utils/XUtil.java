package xafiz.network.devicescanner.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class XUtil {

    public static InetAddress getHost(String name) {
        try {
            return InetAddress.getByName(name);
        }catch(UnknownHostException ignored) {

        }
        return null;
    }

    public static InetAddress getHost(byte[] address) {
        try {
            return InetAddress.getByAddress(address);
        }catch(UnknownHostException ignored) {

        }
        return null;
    }

    public static boolean isValidIpv4(String ip) {
        InetAddress address = getHost(ip);
        return address != null && address.getHostAddress().equals(ip);
    }
}
