package AplicacaoEstudantil.dao.chat;

import AplicacaoEstudantil.dao.ConexaoChatDAO;
import framework.service.chat.mensagem.Mensagem;

import java.sql.*;
import java.util.HashMap;

public class MensagemDAOMySQL {
    private Connection conexaoBD;
    private Statement comandos;

    //Abre a conexão com o BD
    public void conectar() throws SQLException {
        conexaoBD = ConexaoChatDAO.conectar();
        comandos = conexaoBD.createStatement();
    }

    //Fecha a conexão com o BD
    private void encerrarConexao() {
        try {
            comandos.close();
            conexaoBD.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Salva a mensagem recebida no BD e retorna o ID da Mensagem
    public int saveMessage(Mensagem saveMessage) throws SQLException {
        conectar();

        String sqlCommand = "INSERT INTO MENSAGEM (dataMensagem, conteudoMensagem, idRemetente, emailRemetente) VALUES ("
                + saveMessage.getDataMessage() + saveMessage.getTexto() + saveMessage.getIdRemetente() + saveMessage.getEmailRemetente() + ")";

        System.out.println("SQL inserido: " + sqlCommand);

        comandos.executeUpdate(sqlCommand);

        sqlCommand = "SELECT idMensagem from MENSAGEM WHERE dataMensagem = " + saveMessage.getDataMessage() +
                " AND idRemetente = " + saveMessage.getIdRemetente();
        ResultSet queryResult = comandos.executeQuery(sqlCommand);
        int idMensagem = 0;

        if(queryResult.next()) {
            idMensagem = Integer.parseInt(queryResult.getString("idMensagem"));
        }
        encerrarConexao();

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
        conectar();

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

        comandos.executeUpdate(sqlCommand.toString());

        encerrarConexao();
    }
}