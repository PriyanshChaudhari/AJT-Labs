package RMI.SystemInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SystemInfo extends Remote {
    public String getOSVersion() throws RemoteException;

    public long getTotalDiskSpace() throws RemoteException;

    public long getAvailableDiskSpace() throws RemoteException;

    public long getTotalMemory() throws RemoteException;

    public long getUsedMemory() throws RemoteException;
}
