package IO;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    private Socket s;

    public TCPClient() throws IOException {
        this.s = new Socket("localhost", 4999);
        receiveFile(s);
    }

    public Socket getclientSocket() {
        return s;
    }

    public static void receiveFile(Socket s) throws IOException{
        File f1  = new File("IO/ReceivedBooks.dat");
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        byte[] fileBytes = new byte[8192];
        int bytesRead;
        FileOutputStream fos = new FileOutputStream(f1);
        while (s.getInputStream().available() != 0) {
            bytesRead = s.getInputStream().read(fileBytes);
            fos.write(fileBytes, 0, bytesRead);
        }
        fos.close();
//		while ((bytesRead = s.getInputStream().read(fileBytes)) > 0) {
//			fos.write(fileBytes, 0, bytesRead);
//		}
        System.out.println("File received from server.");
    }

    public static void sendFile(Socket cs) {
        try {
            DataInputStream dis = new DataInputStream(cs.getInputStream());
            File f1 = new File("IO/ReceivedBooks.dat");
            if (f1.exists()) {
                long fileSize = f1.length();
                byte[] fileBytes = new byte[(int) fileSize];
                FileInputStream fis = new FileInputStream(f1);
                fis.read(fileBytes);

                cs.getOutputStream().write(fileBytes);
                fis.close();
                dis.close();
                System.out.println("File sent to server.");
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
