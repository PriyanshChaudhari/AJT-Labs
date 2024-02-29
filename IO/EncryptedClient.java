package IO;

import java.io.*;
import java.net.Socket;

public class EncryptedClient {
    private static Socket s;

    public EncryptedClient() {
        try {
            s = new Socket("localhost", 4999);
            receiveFile(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Socket getclientSocket() {
        return s;
    }

    public static void sendFile(Socket cs) {
                File f1 = new File("IO/ReceivedBooks.dat");
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

            OutputStream os = cs.getOutputStream();
            os.write(encryptedInput.getBytes());
            os.flush();
            os.close();
            fis.close();
            System.out.println("File sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveFile(Socket s) throws IOException{
        File f1 = new File("IO/ReceivedBooks.dat");
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
            //is.close();
            System.out.println("File Received from server.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            s = new Socket("localhost", 4999);
//            sendFile(s);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
