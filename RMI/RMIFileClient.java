package RMI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;

public class RMIFileClient {

    File f1 = new File("IO/Books.dat");

    public RMIFileClient(){
        try{
            ReadFile stub=(ReadFile) Naming.lookup("rmi://localhost:5000/readf");
            byte[] fileData = stub.readFile("./RMI/Data.txt");
            System.out.println(new String(fileData));
            writeFile(fileData);
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void writeFile(byte[] data){
        if (!f1.exists()){
            System.out.println("File doesn't exist.");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(f1);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        RMIFileClient client = new RMIFileClient();
    }
}
