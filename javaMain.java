import GUI.mainFrame;
import IO.TCPClient;
import IO.TCPServer;
import IO.UDPSender;
import IO.UDPReceiver;

import java.io.IOException;

public class javaMain {
    public static void main(String[] args) throws IOException {
        TCPServerThread serverThread = new TCPServerThread();
        serverThread.setPriority(Thread.MAX_PRIORITY);

        TCPClientThread clientThread = new TCPClientThread();
        clientThread.setPriority(Thread.NORM_PRIORITY);

        serverThread.start();
        clientThread.start();

//        UDPServerThread serverThread = new UDPServerThread();
//        serverThread.start();
//
//        UDPClientThread clientThread = new UDPClientThread();
//        clientThread.start();

        mainFrame mFObj = new mainFrame(clientThread.getClient().getclientSocket(), serverThread.getServer().getserverSocket());
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