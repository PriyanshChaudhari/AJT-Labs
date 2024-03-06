package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReadFile extends Remote {

    public byte[] readFile(String fileName) throws RemoteException;
}
