package RMI.SystemInfo;

import java.rmi.Naming;

public class SysInfoClient {

    public SysInfoClient() {
        try {
            SystemInfo stub = (SystemInfo) Naming.lookup("rmi://localhost:5000/sysInfo");

            System.out.println("OS Version: " + stub.getOSVersion());
            System.out.println("Total Disk Space: " + stub.getTotalDiskSpace() + " bytes");
            System.out.println("Available Disk Space: " + stub.getAvailableDiskSpace() + " bytes");
            System.out.println("Total Memory: " + stub.getTotalMemory() + " bytes");
            System.out.println("Used Memory: " + stub.getUsedMemory() + " bytes");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        SysInfoClient sysInfoClient = new SysInfoClient();
    }
}
