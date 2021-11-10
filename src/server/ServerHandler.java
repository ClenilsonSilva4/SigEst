package server;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerHandler implements Runnable{
    private final Socket clientSocket;
    private Date tempoLocal;
    private final SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
    private String destinationID;

    public ServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        handleClientRequest();
    }

    //Função para lidar com a conexão do cliente
    public void handleClientRequest(){
        String inMsg, outMsg, identifier = clientSocket.getInetAddress().toString();
        BufferedReader socketReader;
        BufferedWriter socketWriter;

        try{
            //Inicialização dos buffers de leitura e escrita para se comunicar com o cliente
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            tempoLocal = new Date();

            //Adicionando o cliente na lista de clientes
            System.out.println(identifier + " se conectou.");

            //Laço para ficar lendo as mensagens do cliente da thread e transmitir para os demais clientes conectados
            while(true){
                inMsg = socketReader.readLine();
                tempoLocal = new Date();

                if(!(clientSocket.isConnected())){
                    throw new Exception();
                }else if(!(inMsg.equalsIgnoreCase("null"))){
                    socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    outMsg = "Servidor: mensagem " + inMsg + " recebida às: " + ft.format(tempoLocal);
                    socketWriter.write(outMsg);
                    socketWriter.write("\n");
                    socketWriter.flush();

                    //Adicionar função para poder encerrar a conexão com o cliente.
                    if(inMsg.equalsIgnoreCase("sair")){
                        System.out.println("Conexão encerrada pelo cliente");
                        break;
                    }

                    System.out.println(identifier + ": " + inMsg);

                    //Salvar a mensagem no BD para os usuários receberem

                }
            }
            clientSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
