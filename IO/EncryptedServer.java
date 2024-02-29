package IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EncryptedServer {
    private static Socket cs;
    private static ServerSocket ss;

    public EncryptedServer() {
        try {
            ss = new ServerSocket(4999);
            cs = ss.accept();
            sendFile(cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getserverSocket() {
        return cs;
    }

    public static void receiveFile(Socket s) throws IOException{
        File f1 = new File("IO/SendBooks.dat");
        try(InputStream is = s.getInputStream();
            FileOutputStream fos = new FileOutputStream(f1);
        ){
            Decrypt decrypt = new Decrypt();
            String encryptedInput;
            String decryptedOutput;

            int bytesRead;
            byte[] fileBytes = is.readAllBytes();
            encryptedInput = new String(fileBytes);

            decryptedOutput = decrypt.decryptData(encryptedInput, 2);

            System.out.println("Decrypted Data: "+ decryptedOutput);

            fos.write(decryptedOutput.getBytes());
            fos.close();
            is.close();
            System.out.println("File Received from client.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void sendFile(Socket cs) {
        File f1 = new File("IO/SendBooks.dat");
        try (FileInputStream fis = new FileInputStream(f1);){
            Encrypt encrypt = new Encrypt();
            String input;
            String encryptedInput;

            if (!f1.exists()){
                System.out.println("File not found");
            }

            byte[] fileBytes = fis.readAllBytes();
            input = new String(fileBytes);

            encryptedInput = encrypt.encryptData(input, 2);

            System.out.println("Encrypted Data: " + encryptedInput);

            fis.close();
            OutputStream os = cs.getOutputStream();
            os.write(encryptedInput.getBytes());
            os.flush();
            os.close();
            System.out.println("File sent to client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            ss = new ServerSocket(4999);
//            cs = ss.accept();
//            receiveFile(cs);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
