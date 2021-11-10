package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMessage{
    public static void main(String[] args) {
        int port = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                ServerHandler channel = new ServerHandler(clientSocket);
                channel.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}