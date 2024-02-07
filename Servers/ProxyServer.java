package Servers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class ProxyServer {

    public static void main(String[] args) {
        final int PROXY_PORT = 8888;

        try (ServerSocket serverSocket = new ServerSocket(PROXY_PORT)) {
            System.out.println("ProxyServer is listening on port " + PROXY_PORT);

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
                InputStream clientInput = clientSocket.getInputStream();
                OutputStream clientOutput = clientSocket.getOutputStream()
        ) {
            // Read the request from the client
            byte[] requestBuffer = new byte[8192];
            int bytesRead = clientInput.read(requestBuffer);

            if (bytesRead > 0) {
                // Parse the request to extract the destination URL
                String request = new String(requestBuffer, 0, bytesRead);
                String[] requestLines = request.split("\r\n");
                String[] requestTokens = requestLines[0].split(" ");
                String destinationURL = requestTokens[1];

                // Forward the request to the destination server
                forwardRequest(destinationURL, clientOutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        }
    }

    private static void forwardRequest(String destinationURL, OutputStream clientOutput) {
        try (Socket destinationSocket = new Socket(new URL(destinationURL).getHost(), 80);
             InputStream destinationInput = destinationSocket.getInputStream();
             OutputStream destinationOutput = destinationSocket.getOutputStream()) {

            // Send the request to the destination server
            destinationOutput.write(destinationURL.getBytes());
            destinationOutput.flush();

            // Forward the response to the client
            byte[] responseBuffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = destinationInput.read(responseBuffer)) != -1) {
                clientOutput.write(responseBuffer, 0, bytesRead);
            }
            clientOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
