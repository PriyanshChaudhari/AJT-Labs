package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;

public class UDPSender {
    private DatagramSocket ds;

    InetAddress ia;

    int PORT;
    private DatagramPacket dp;
    public UDPSender() throws SocketException, UnknownHostException {
        this.ds = new DatagramSocket();
        this.ia = InetAddress.getByName("localhost");
        this.PORT = 4999;
        sendFile(ds, ia, PORT);
    }

    public DatagramSocket getclientDSocket() {
        return ds;
    }

    public static void sendFile(DatagramSocket ds, InetAddress ia, int PORT){
        try{
        File f1 = new File("IO/SendBooks.dat");
        if (f1.exists()){
            long fileSize = f1.length();
            byte[] fileBytes = new byte[(int) fileSize];
            FileInputStream fis = new FileInputStream(f1);
            fis.read(fileBytes);

            DatagramPacket dp = new DatagramPacket(fileBytes, fileBytes.length, ia, PORT);
            ds.send(dp);
            System.out.println("File sent...");
        }else{
            System.out.println("File not found.");
        }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
