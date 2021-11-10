package server;

import java.io.*;
import java.net.Socket;

public class ServerHandler implements Runnable{
    private final Socket clientSocket;

    public ServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        PrintWriter messageWriter = new PrintWriter(clientSocket.getOutputStream());
        BufferedReader messageReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        while ((line = messageReader.readLine()) != null) {
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }

            //function to save the message to the DB
        }
        clientSocket.close();
    }
}
