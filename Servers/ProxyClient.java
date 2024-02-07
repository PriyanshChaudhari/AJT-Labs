package Servers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProxyClient {
    public static void main(String[] args) {
        final String PROXY_HOST = "localhost";
        final int PROXY_PORT = 8888;

        try (Socket socket = new Socket(PROXY_HOST, PROXY_PORT);
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream()) {

            // Send a request to the proxy server
            String request = "GET http://www.example.com HTTP/1.1\r\n" +
                    "Host: www.example.com\r\n" +
                    "\r\n";
            outputStream.write(request.getBytes());
            outputStream.flush();

            // Receive and print the response from the proxy server
            byte[] responseBuffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(responseBuffer)) != -1) {
                System.out.write(responseBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
