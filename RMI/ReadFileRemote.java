package RMI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReadFileRemote extends UnicastRemoteObject implements ReadFile {
    ReadFileRemote()throws RemoteException {
        super();
    }

    public byte[] readFile(String fileName){
        File f1 = new File(fileName);
        byte[] data = new byte[(int) f1.length()];
        try {
            if(!f1.exists()){
                System.out.println("File doesn't exists");
            }
            FileInputStream fis = new FileInputStream(f1);
            fis.read(data);
            fis.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
