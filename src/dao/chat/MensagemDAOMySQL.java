package dao.chat;

import service.chat.message.Mensagem;

import java.sql.*;
import java.util.HashMap;

public class MensagemDAOMySQL {
    private Connection connection;
    private Statement command;

    //Abre a conexão com o BD
    public void connect() throws SQLException {
        connection = ConnectionChatDAO.connect();
        command = connection.createStatement();
        System.out.println("Conectado");
    }

    //Fecha a conexão com o BD
    private void close() {
        try {
            command.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Salva a mensagem recebida no BD e retorna o ID da Mensagem
    public int saveMessage(Mensagem saveMessage) throws SQLException {
        connect();

        String sqlCommand = "INSERT INTO MENSAGEM (dataMensagem, conteudoMensagem, idRemetente, emailRemetente) VALUES ("
                + saveMessage.getDataMessage() + saveMessage.getTexto() + saveMessage.getIdRemetente() + saveMessage.getEmailRemetente() + ")";

        System.out.println("SQL inserido: " + sqlCommand);

        command.executeUpdate(sqlCommand);

        sqlCommand = "SELECT idMensagem from MENSAGEM WHERE dataMensagem = " + saveMessage.getDataMessage() +
                " AND idRemetente = " + saveMessage.getIdRemetente();
        ResultSet queryResult = command.executeQuery(sqlCommand);
        int idMensagem = 0;

        if(queryResult.next()) {
            idMensagem = Integer.parseInt(queryResult.getString("idMensagem"));
        }
        close();

        return idMensagem;
    }

    //TODO Concluir função para pegar as mensagens
    public HashMap<String, Mensagem> getMessages(String idRemetente, String idDestinatario) {
        HashMap<String, Mensagem> messageList = new HashMap<>();

        StringBuilder sqlCommand = new StringBuilder();

        return messageList;
    }

    //Função para atribuir a mensagem ao usuário de destino
    public void forwardMessage (Mensagem sendMessage) throws SQLException {
        connect();

        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append("INSERT INTO ENVIA (idMensagem, idRemetente, emailRemetente, ");

        if(sendMessage.isPertenceGrupo()) {
            sqlCommand.append("idGrupo");
        } else {
            sqlCommand.append("idDestinatario, emailDestinatario)");
        }

        sqlCommand.append(" VALUES (");
        sqlCommand.append(sendMessage.getIdMensagem()).append(sendMessage.getIdRemetente()).
                append(sendMessage.getEmailRemetente()).append(sendMessage.getIdDestinatario());

        if(!sendMessage.isPertenceGrupo()) {
            sqlCommand.append(sendMessage.getEmailDestinatario());
        }

        command.executeUpdate(sqlCommand.toString());

        close();
    }
}