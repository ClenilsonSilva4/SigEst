package dao.estudante;

import dao.conexao.ConexaoSistemaDAO;
import exception.UserNotFoundException;
import service.estudante.Estudante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstudanteDAOMySQL implements EstudanteDAO{
    private Connection conexaoBD;
    private Statement comandos;

    private void conectar() throws SQLException {
        this.conexaoBD = ConexaoSistemaDAO.conectar();
        this.comandos = conexaoBD.createStatement();
    }

    private void encerrarConexao() throws SQLException {
        conexaoBD.close();
        comandos.close();
    }

    @Override
    public void inserirEstudante(String nome, String email, String senha) throws SQLException {
        conectar();

        String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario) VALUES (" +
                stringBD(nome) + ", " + stringBD(email) + ", " + stringBD(senha) + ");";

        comandos.executeUpdate(sqlComando);

        encerrarConexao();
    }

    @Override
    public Estudante consultarEstudante(int idEstudante) throws UserNotFoundException {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idEstudante + ");";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Estudante(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            } else {
                throw new UserNotFoundException("O ID não pertence a um usuário válido");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removerEstudante(int idEstudante) {
        try {
            conectar();
            String sqlComando = "DELETE * FROM usuario WHERE (idUsuario = " + idEstudante + ");";

            comandos.executeQuery(sqlComando);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterarEstudante(Estudante estudanteAlterado) {
        //TODO Acesso ao BD
    }

    private String stringBD(String valor) {
        if (valor != null && !"".equals(valor)) {
            valor = "'" + valor + "'";
        } else {
            valor = "'"+"'";
        }
        return valor;
    }
}
