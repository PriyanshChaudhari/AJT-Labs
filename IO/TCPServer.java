package IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private Socket cs;
    private ServerSocket ss;

    public TCPServer() throws IOException {
        this.ss = new ServerSocket(4999);
        this.cs = ss.accept();
        sendfile(cs);
    }

    public Socket getserverSocket() {
        return cs;
    }

    public static void sendfile(Socket cs){
        try {
            DataInputStream dis = new DataInputStream(cs.getInputStream());
            File f1 = new File("IO/SendBooks.dat");
            if (f1.exists()) {
                long fileSize = f1.length();
                byte[] fileBytes = new byte[(int) fileSize];
                FileInputStream fis = new FileInputStream(f1);
                fis.read(fileBytes);

                cs.getOutputStream().write(fileBytes);
                fis.close();
                System.out.println("File sent to client.");
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void receiveFile(Socket s) throws IOException{
        File f1  = new File("IO/SendBooks.dat");
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        byte[] fileBytes = new byte[8192];
        int bytesRead;
        FileOutputStream fos = new FileOutputStream(f1);
        while (s.getInputStream().available() != 0) {
            bytesRead = s.getInputStream().read(fileBytes);
            fos.write(fileBytes, 0, bytesRead);
        }
//		while ((bytesRead = s.getInputStream().read(fileBytes)) > 0) {
//			fos.write(fileBytes, 0, bytesRead);
//		}
        fos.close();
        dos.close();
        System.out.println("File received from client.");
    }
}
