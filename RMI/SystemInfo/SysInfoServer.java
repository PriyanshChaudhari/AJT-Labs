package RMI.SystemInfo;

import java.rmi.Naming;

public class SysInfoServer {
    public SysInfoServer() {
        try {
            SystemInfo stub = new SystemInfoRemote();
            System.out.println("System Info server ready.");
            Naming.rebind("rmi://localhost:5000/sysInfo", stub);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        SysInfoServer sysInfoServer = new SysInfoServer();
    }
}
