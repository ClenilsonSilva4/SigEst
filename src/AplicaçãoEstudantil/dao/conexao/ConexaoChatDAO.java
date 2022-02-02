package AplicaçãoEstudantil.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoChatDAO {
    private static String nome;
    private static String senha;
    private static String url;

    public ConexaoChatDAO() {
        url = "jdbc:mysql://localhost/chatbd?useTimezone=true&serverTimezone=America/Fortaleza";
        nome = "root";
        senha = "root";
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, nome, senha);
    }
}
