package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIFileServer {
        public RMIFileServer(){
            ReadFileRemote stub = null;
            try {
                stub = new ReadFileRemote();
                System.out.println("File server ready.");
                Naming.rebind("rmi://localhost:5000/readf", stub);
            } catch (RemoteException | MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }

    public static void main(String[] args) {
        RMIFileServer server = new RMIFileServer();
    }
}
