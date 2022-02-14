package AplicacaoControleQualidade.service.chat.servidor;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;

public class Servidor {
    public static void main(String[] args) throws Exception{
        //Cria��o e associa��o do socket ao endere�o do servidor
        ServerSocket serverSocket = new ServerSocket(8080, 100, InetAddress.getByName(getRedeIP()));
        //Mostra as informa�ões do socket do servidor
        System.out.println("Servidor iniciado no endere�o " + serverSocket.getInetAddress());
        System.out.println("Agurdando conex�o na porta: " + serverSocket.getLocalPort());
        System.out.println();

        Manipulador serverHandler = new Manipulador();
        serverHandler.AcceptConnection(serverSocket);
    }

    //Fun��o para conseguir o endere�o IP principal da rede
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
                //Caso o la�o n�o resulte em nenhum resultado, define o IP como o localhost
                if (atualIP  == null) {
                    atualIP  = "127.0.0.1";
                }

            } catch (SocketException e) {
                //Caso haja alguma exce��o, o IP é definido como localhost
                atualIP  = "127.0.0.1";
            }

        //Retorna o IP
        return atualIP ;
    }
}