package AplicacaoAplicacaoControleQualidade.service.chat.cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception{
        //Cria��o do socket e chamada de fun��o para realizar a conex�o com o servidor
        Socket serverSocket = connectServer();
        System.out.println("Enviando dados para o endere�o IP: " + serverSocket.getInetAddress());
        
        //Cria��o dos buffers de escrita e leitura para usar na conex�o
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String outMsg;
        
        try{
            //Cria��o de um thread para ficar sempre ouvindo em busca de mensagens
            Runnable receiver = () -> receiveData(serverSocket);
            new Thread(receiver).start();
            
            //Respondendo ao servidor com a identifica��o pedida
            outMsg = consoleReader.readLine();
            socketWriter.write(outMsg + "\n");
            socketWriter.flush();
            System.out.println();
            
            System.out.println("Digite \"sair\" para encerrar o bate-papo.");
            //La�o para receber e enviar mensagens ao servidor
            while(true){
                if(!(serverSocket.isConnected())){
                    throw new Exception();
                }
                outMsg = consoleReader.readLine();
                socketWriter.write(outMsg + "\n");
                socketWriter.flush();
            }
        }catch(Exception e){
            //Exce��o para quando o servidor encerra a conex�o sem avisar
            System.out.println("A conex�o com o servidor foi encerrada.");
            System.out.println("A aplica��o ser� encerrada.");
            serverSocket.close();
        }
    }
    
    //Func��o para ficar ouvindo atÃ© receber uma mensagem e a imprime na tela
    public static void receiveData(Socket socket_){
        String inMsg;
        try{
            //Cria��o do buffer de leitura do socket
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
            while(true){
                inMsg = socketReader.readLine();
                //Condicional para receber mensagem de saida do servidor e encerrar a conex�o.
                if (inMsg.equalsIgnoreCase("sair")) {
                    System.out.println("Conex�o encerrada.");
                    socket_.close();
                    System.exit(0);
                }
                System.out.println(inMsg);
            }
        }catch(Exception e){
            System.out.println("Houve um erro com a conex�o");
            System.out.println("A aplica��o ser� encerrada");
            System.exit(0);
        }
    }
    
    //Fun��o para realizar a conex�o ao servidor
    public static Socket connectServer(){
        Socket socket = null;
        boolean condicao = true, excecao = true;
        Scanner leitor = new Scanner(System.in);
        String[] sepSocket;
        String getSocket;
        
        //La�o para receber as informa��es para tentar se conectar ao servidor
        do{
            System.out.println("Digite o IP do servidor seguido da porta: (x.y.w.z 00000).");
            getSocket = leitor.nextLine();
            sepSocket = getSocket.split(" ");
            
            //Condicional para verificar se recebeu a quantidade correta de dados.
            if(sepSocket.length == 2){
                do{
                    //Tenta conectar ao servidor com as informa��es que foram passadas
                    //Caso n�o consiga, d� op��es ao cliente
                    try{
                        socket = new Socket(sepSocket[0], Integer.parseInt(sepSocket[1]));
                        System.out.println("\nConex�o ao servidor realizada com sucesso." + "\n");
                        excecao = condicao = false;
                    } catch (Exception e){
                        System.out.println("\n\nN�o foi poss�vel se conectar a esse servidor.");
                        System.out.println("Quer tentar se conectar novamente?");
                        System.out.println("Digite \"sim\" para digitar o IP e porta novamente.");
                        getSocket = leitor.nextLine();
                        if(!getSocket.equalsIgnoreCase("sim")){
                            excecao = false;
                            System.out.println("\nN�o foi poss�vel estabelecer conex�o com o servidor.");
                            System.out.println("A aplica��o ser� encerrada.");
                            System.exit(0);
                        }
                    }
                }while(excecao);
            }else{
                //Mensagem de erro caso receba um valor de dados diferentes de dois
                System.out.println("Certifique-se de digitar o IP juntamente com a porta.");
            }
        }while(condicao);
        
        return socket;
    }
}