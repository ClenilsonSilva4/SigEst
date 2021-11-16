package server;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import message.Message;
import message.MessageJDBC;

public class ServerHandler implements Runnable{
    private final Socket clientSocket;
    private String idRemetente;
    private final String idDestinario;
    private final String emailRemetente;
    private final SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");

    public ServerHandler(Socket clientSocket, String emailRemetente, String idDestinatario) {
        this.clientSocket = clientSocket;
        this.emailRemetente = emailRemetente;
        this.idDestinario = idDestinatario;
    }

    @Override
    public void run() {
        handleClientRequest();
    }

    //TODO Criar função para checar no BD os IDs dos receptores, caso seja um grupo,
    // para enviar isso para a função que mandará as mensagens para eles.

    //TODO Criar função para checar mensagens recebidas pelo cliente quando estava offline

    //Função para lidar com a conexão do cliente
    public void handleClientRequest(){
        String inMsg, outMsg, identifier = clientSocket.getInetAddress().toString();
        BufferedReader socketReader;
        BufferedWriter socketWriter;

        try{
            //Inicialização dos buffers de leitura e escrita para se comunicar com o cliente
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Date tempoLocal;

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
                    Message forwardMessage = new Message(idRemetente, inMsg, ft.format(tempoLocal), emailRemetente, idDestinario);
                }
            }
            clientSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void forwardMessage (Message message) throws SQLException {
        MessageJDBC newMessage = new MessageJDBC();
        String idMensagem = newMessage.saveMessage(message);
        message.setIdMensagem(idMensagem);

    }
}
