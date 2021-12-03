package dao.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionChatDAO {
    private static String nome;
    private static String senha;
    private static String url;

    public ConnectionChatDAO() {
        url = "jdbc:mysql://localhost/chatbd?useTimezone=true&serverTimezone=America/Fortaleza";
        nome = "root";
        senha = "root";
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, nome, senha);
    }
}
