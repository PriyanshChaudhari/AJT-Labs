package Listeners;

import GUI.mainFrame;
import IO.TCPClient;
import IO.TCPServer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;

public class CloseWindowListener extends WindowAdapter {
    Socket clientSocket;
    Socket serverSocket;
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("sending file...");
        TCPClient.sendFile(clientSocket);
        try {
            System.out.println("receiving file...");
            TCPServer.receiveFile(serverSocket);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}