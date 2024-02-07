package Servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {

    private static final int PORT = 8080;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ChatServer is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a new thread to handle each client
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            // Add client's PrintWriter to the set
            clientWriters.add(writer);

            // Welcome message to the client
            writer.println("Welcome to the ChatServer!");

            // Broadcast messages to all clients
            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                broadcastMessage(clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remove client's PrintWriter when the client disconnects
            clientWriters.remove(clientSocket);
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        }
    }

    private static void broadcastMessage(String message) {
        System.out.println("Broadcasting: " + message);

        // Send the message to all connected clients
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }
}
