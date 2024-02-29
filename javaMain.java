import GUI.mainFrame;
import IO.*;

import java.io.IOException;

public class javaMain {
    public static void main(String[] args) throws IOException {
//        TCPServerThread serverThread = new TCPServerThread();
//        serverThread.setPriority(Thread.MAX_PRIORITY);
//
//        TCPClientThread clientThread = new TCPClientThread();
//        clientThread.setPriority(Thread.NORM_PRIORITY);
//
//        serverThread.start();
//        clientThread.start();

//        UDPServerThread serverThread = new UDPServerThread();
//        serverThread.start();
//
//        UDPClientThread clientThread = new UDPClientThread();
//        clientThread.start();

        EncryptedServerThread encryptedServerThread = new EncryptedServerThread();
        encryptedServerThread.setPriority(Thread.MAX_PRIORITY);

        EncryptedClientThread encryptedClientThread = new EncryptedClientThread();
        encryptedClientThread.setPriority(Thread.NORM_PRIORITY);

        encryptedClientThread.start();
        encryptedServerThread.start();

//        mainFrame mFObj = new mainFrame(clientThread.getClient().getclientSocket(), serverThread.getServer().getserverSocket());
        mainFrame mFObj = new mainFrame(encryptedClientThread.getClient().getclientSocket(), encryptedServerThread.getServer().getserverSocket(), true);
//        mainFrame mFObj = new mainFrame(clientThread.getClient().getclientDSocket());

    }

    static class TCPServerThread extends Thread{
        TCPServer server;

        @Override
        public void run() {
            try {
                server = new TCPServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public TCPServer getServer() {
            return server;
        }
    }

    static class TCPClientThread extends Thread{
        TCPClient client;

        @Override
        public void run() {
            try {
                client = new TCPClient();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public TCPClient getClient() {
            return client;
        }
    }

    static class EncryptedServerThread extends Thread{
        EncryptedServer server;

        @Override
        public void run() {
            try {
                server = new EncryptedServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public EncryptedServer getServer() {
            return server;
        }
    }

    static class EncryptedClientThread extends Thread{
        EncryptedClient client;

        @Override
        public void run() {
            try {
                client = new EncryptedClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public EncryptedClient getClient() {
            return client;
        }
    }

    static class UDPServerThread extends Thread{
        UDPReceiver server;

        @Override
        public void run() {
            try {
                server = new UDPReceiver();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public UDPReceiver getServer() {
            return server;
        }
    }

    static class UDPClientThread extends Thread{
        UDPSender client;

        @Override
        public void run() {
            try {
                client = new UDPSender();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public UDPSender getClient() {
            return client;
        }
    }
}