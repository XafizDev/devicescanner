package xafiz.network.devicescanner.utils;

import java.io.IOException;
import java.net.InetAddress;

public class Device {

    private final String host;
    private InetAddress address;

    public Device(String host) {
        this.host = host;
    }

    public boolean isReachable() {
        try {
            this.address = XUtil.getHost(this.host);
            if(this.address != null) {
                return this.address.isReachable(100);
            }
        }catch(IOException ignored) {

        }
        return false;
    }

    public String getHost() {
        return this.address.getHostAddress();
    }

    public String getName() {
        return this.address.getHostName();
    }
}
