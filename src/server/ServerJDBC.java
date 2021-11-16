package server;

import message.Message;
import java.sql.*;

public class ServerJDBC {
    private String nome;
    private String senha;
    private String url;

    private Connection connection;
    private Statement command;

    public ServerJDBC(String server, String user, String password) {
        this.url = server;
        this.nome = user;
        this.senha = password;
    }

    public ServerJDBC() {
        this.url = "jdbc:mysql://localhost/chatbd?useTimezone=true&serverTimezone=America/Fortaleza";
        this.nome = "root";
        this.senha = "N1mu3@36";
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, nome, senha);
        command = connection.createStatement();
        System.out.println("Conectado");
    }

    private void close() {
        try {
            command.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMessage(Message saveMessage) throws SQLException {
        connect();

        String sqlCommand = "INSERT INTO MENSAGEM (dataMensagem, conteudoMensagem, idRemetente, emailRemetente) VALUES ("
                + saveMessage.getDateMessage() + saveMessage.getTexto() + saveMessage.getIdRemetente() + saveMessage.getEmail() + ")";

        System.out.println("SQL inserido: " + sqlCommand);

        command.executeUpdate(sqlCommand);
        close();
    }
}