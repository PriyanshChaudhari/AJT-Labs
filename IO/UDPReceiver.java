package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver {
    private DatagramSocket ds;
    private DatagramPacket dp;
    int PORT;
    public UDPReceiver() throws SocketException {
        this.PORT = 4999;
        this.ds = new DatagramSocket(PORT);
        receiveFile(ds);
    }

    public DatagramSocket getserverDSocket() {
        return ds;
    }

    public static void receiveFile(DatagramSocket ds){
        try {
        File f1 = new File("IO/ReceivedBooks.dat");
        byte[] fileBytes = new byte[8192];
        FileOutputStream fos = new FileOutputStream(f1);
        //DatagramPacket dp = new DatagramPacket(fileBytes, fileBytes.length);
        //ds.receive(dp);
//        int dataLength = dp.getData().length;
        int bytesRead;
            boolean fileReceived = false;

            while (!fileReceived) {
                DatagramPacket p = new DatagramPacket(fileBytes, fileBytes.length);
                ds.receive(p);

                // Check if the received packet contains the end-of-file marker
                String receivedData = new String(p.getData(), 0, p.getLength());
                if (receivedData.equals("EOF")) {
                    fileReceived = true;
                    break;
                }

                // Write the received data to the file
                fos.write(p.getData(), 0, p.getLength());
            }
            fos.close();
            System.out.println("File Received...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
