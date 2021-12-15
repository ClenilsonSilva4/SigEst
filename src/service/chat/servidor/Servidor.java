package service.chat.servidor;

import java.net.*;
import java.util.*;

public class Servidor {
    public static void main(String[] args) throws Exception{
        //Criação e associação do socket ao endereço do servidor
        ServerSocket serverSocket = new ServerSocket(8080, 100, InetAddress.getByName(getRedeIP()));
        //Mostra as informaçÃµes do socket do servidor
        System.out.println("Servidor iniciado no endereço " + serverSocket.getInetAddress());
        System.out.println("Agurdando conexão na porta: " + serverSocket.getLocalPort());
        System.out.println();

        Manipulador serverHandler = new Manipulador();
        serverHandler.AcceptConnection(serverSocket);
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
                //Caso haja alguma exceção, o IP Ã© definido como localhost
                atualIP  = "127.0.0.1";
            }

        //Retorna o IP
        return atualIP ;
    }
}