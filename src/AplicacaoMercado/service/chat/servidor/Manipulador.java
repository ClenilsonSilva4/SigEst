package AplicacaoMercado.service.chat.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import framework.service.chat.mensagem.Mensagem;
import AplicacaoEstudantil.dao.chat.MensagemDAOMySQL;

public class Manipulador {
    private int idRemetente;
    private int idDestinario;
    private String emailRemetente;
    private final SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private Map<Socket, String> connectedSockets = new HashMap <> ();

    public void AcceptConnection(ServerSocket serverSocket) throws IOException {
        //La�o infinito para aceitar as conex�es ao servidor e inicia uma thread com o cliente
        while(true){
            final Socket activeSocket = serverSocket.accept();
            System.out.println("Conexão recebida de " + activeSocket);

            socketReader = new BufferedReader(new InputStreamReader(activeSocket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(activeSocket.getOutputStream()));
            socketWriter.write("Conexão aceita");

            Runnable runnable = () -> handleClientRequest(activeSocket);
            new Thread(runnable).start();
            connectedSockets.remove(activeSocket);
        }
    }

    //TODO Criar fun��o para checar no BD os IDs dos receptores, caso seja um grupo,
    // para enviar isso para a fun��o que mandar� as mensagens para eles.

    //TODO Criar fun��o para checar mensagens recebidas pelo cliente quando estava offline

    //Fun��o para lidar com a conex�o do cliente
    private void handleClientRequest(Socket clientSocket){
        String inMsg, outMsg, identifier = clientSocket.getInetAddress().toString();

        try{
            //Inicializa��o dos buffers de leitura e escrita para se comunicar com o cliente
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            //Pergunta ao cliente como ele gostaria de ser identificado no servidor e recebe a mensagem
            socketWriter.write("Como gostaria de ser identificado?\n");
            socketWriter.flush();
            outMsg = socketReader.readLine();

            if(!outMsg.equalsIgnoreCase("null")){
                identifier = outMsg;
            }

            Date tempoLocal;

            //Adicionando o cliente na lista de clientes
            connectedSockets.put(clientSocket, identifier);
            System.out.println(identifier + " se conectou.");

            //La�o para ficar lendo as mensagens do cliente da thread e transmitir para os demais clientes conectados
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

                    //Adicionar fun��o para poder encerrar a conex�o com o cliente.
                    if(inMsg.equalsIgnoreCase("sair")){
                        System.out.println("Conexão encerrada pelo cliente");
                        break;
                    }

                    System.out.println(identifier + ": " + inMsg);

                    //Transmite a mensagem recebida do cliente para os demais
                    for(Map.Entry <Socket, String> it : connectedSockets.entrySet()){
                        if(it.getKey() != clientSocket){
                            socketWriter = new BufferedWriter(new OutputStreamWriter(it.getKey().getOutputStream()));
                            socketWriter.write(identifier + ": " + inMsg);
                            socketWriter.write("\n");
                            socketWriter.flush();
                        }
                    }

                    //Salvar a mensagem no BD para os usu�rios receberem
                    //Message forwardMessage = new Message(idRemetente, inMsg, ft.format(tempoLocal), emailRemetente, idDestinario);
                }
            }
            clientSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void forwardMessage (Mensagem mensagem) throws SQLException {
        MensagemDAOMySQL newMessage = new MensagemDAOMySQL();
        int idMensagem = newMessage.saveMessage(mensagem);
        mensagem.setIdMensagem(idMensagem);

        newMessage.forwardMessage(mensagem);
    }
}
