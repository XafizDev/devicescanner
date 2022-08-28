package xafiz.network.devicescanner;

import xafiz.network.devicescanner.utils.Device;
import xafiz.network.devicescanner.utils.Logger;
import xafiz.network.devicescanner.utils.XUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Logger.info("Device scanner");
        Logger.info("Developer: Xafiz\n");
        if(args.length == 0) {
            Logger.error("Please pass a gateway address as argument");
            return;
        }
        String gateway = args[0];
        if(!XUtil.isValidIpv4(gateway)) {
            Logger.error("Please pass a valid gateway address as argument");
            return;
        }
        InetAddress host = XUtil.getHost(gateway);
        if(host == null) {
            Logger.error("Failed to scan on " + gateway);
            return;
        }
        List<Device> devices = new ArrayList<>();
        byte[] address = host.getAddress();
        Logger.info("Generating ips...");
        for(int i = 1; i < 255; i++) {
            address[3] = (byte) i;
            InetAddress device = XUtil.getHost(address);
            if(device != null) {
                devices.add(new Device(device.getHostAddress()));
            }
        }
        Logger.info("Scanning ips...");
        List<Device> activeDevices = devices.parallelStream().filter(Device::isReachable).toList();
        Logger.success("Found " + activeDevices.size() + " devices on " + gateway + "\n");
        for(Device device : activeDevices) {
            String spaces = " ".repeat(Math.abs(device.getHost().length() - (gateway.substring(0, gateway.lastIndexOf(".")).length() + 4)));
            System.out.printf("%s%s - %s\n", device.getHost(), spaces, device.getName());
        }
    }
}
