package dao.usuario;

import dao.conexao.ConexaoSistemaDAO;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAOMySQL extends UsuarioDAOMySQL {
    @Override
    public void inserirUsuario(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario, tipoUsuario) VALUES (" +
                    stringBD(nome) + ", " + stringBD(email) + ", " + stringBD(senha) + ", 2);";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a inserção no banco de dados");
            }

            encerrarConexao();
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Professor consultarUsuario(int idProfessor) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idProfessor + " AND tipoUsuario = 2);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Professor(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Professor consultarUsuario(String emailProfessor) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + emailProfessor + " AND tipoUsuario = 2);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Professor(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
