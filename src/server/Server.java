package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.*;

public class Server{
    public static void main(String[] args) throws Exception{
        //Criação e associação do socket ao endereço do servidor
        ServerSocket serverSocket = new ServerSocket(8080, 100, InetAddress.getByName(getRedeIP()));
        //Mostra as informações do socket do servidor
        System.out.println("Servidor iniciado no endereço " + serverSocket.getInetAddress());
        System.out.println("Agurdando conexão na porta: " + serverSocket.getLocalPort());
        System.out.println();
        
        //Laço infinito para aceitar as conexões ao servidor e inicia uma thread com o cliente
        while(true){
            final Socket activeSocket = serverSocket.accept();
            System.out.println("Conexão recebida de " + activeSocket);

            BufferedReader socketReader = new BufferedReader(new InputStreamReader(activeSocket.getInputStream()));
            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(activeSocket.getOutputStream()));
            socketWriter.write("Conexão aceita");

            String destinationID = socketReader.readLine();
            Socket destinationSocket = null;

            ServerHandler newConnection = new ServerHandler(activeSocket, "teste@gmail.com");
            newConnection.run(); // start a new thread
        }
    }

    //Função para conseguir o endereço IP principal da rede
    public static String getRedeIP() {
        String atualIP = null;

        Enumeration<NetworkInterface> interfacesRede;
            try {
                interfacesRede = NetworkInterface.getNetworkInterfaces();

                //Enquanto a interface tem elementos, testa para ver se encontra um que se adequa
                while (interfacesRede.hasMoreElements()) {
                    NetworkInterface ni = interfacesRede.nextElement();
                    Enumeration<InetAddress> address = ni.getInetAddresses();
                    while (address.hasMoreElements()) {
                        InetAddress addr = address.nextElement();
                        if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
                                && !addr.getHostAddress().contains(":")) {
                            atualIP  = addr.getHostAddress();
                        }
                    }
                }
                //Caso o laço não resulte em nenhum resultado, define o IP como o localhost
                if (atualIP  == null) {
                    atualIP  = "127.0.0.1";
                }

            } catch (SocketException e) {
                //Caso haja alguma exceção, o IP é definido como localhost
                atualIP  = "127.0.0.1";
            }

        //Retorna o IP
        return atualIP ;
    }
}