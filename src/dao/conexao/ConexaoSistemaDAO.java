package dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSistemaDAO {
    protected Connection conexaoBD;
    protected Statement comandos;

    protected void conectar() throws SQLException {
        this.conexaoBD = DriverManager.getConnection(
                "jdbc:mysql://localhost/sigest?useTimezone=true&serverTimezone=America/Fortaleza", "root", "admin");
        this.comandos = conexaoBD.createStatement();
    }

    protected void encerrarConexao() throws SQLException {
        conexaoBD.close();
        comandos.close();
    }

    protected String stringBD(String valor) {
        if (valor != null && !"".equals(valor)) {
            valor = "'" + valor + "'";
        } else {
            valor = "'"+"'";
        }
        return valor;
    }
}
