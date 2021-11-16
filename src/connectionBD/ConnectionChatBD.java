package connectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionChatBD {
    private static String nome;
    private static String senha;
    private static String url;

    public ConnectionChatBD() {
        url = "jdbc:mysql://localhost/chatbd?useTimezone=true&serverTimezone=America/Fortaleza";
        nome = "root";
        senha = "N1mu3@36";
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, nome, senha);
    }
}
