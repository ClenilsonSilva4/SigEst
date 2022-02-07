package AplicacaoEstudantil.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSistemaDAO {
    protected Connection conexao;
    protected Statement comandos;

    public void conectar() throws SQLException {
        this.conexao = DriverManager.getConnection(
                "jdbc:mysql://localhost/sigest?useTimezone=true&serverTimezone=America/Fortaleza", "root", "root");
        this.comandos = conexao.createStatement();
    }

    protected void encerrarConexao() throws SQLException {
        conexao.close();
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
