package dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSistemaDAO {
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/sigest?useTimezone=true&serverTimezone=America/Fortaleza", "root", "root");
    }
}
