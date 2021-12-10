package dao.estudante;

import dao.conexao.ConexaoSistemaDAO;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Estudante;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudanteDAOMySQL extends ConexaoSistemaDAO implements EstudanteDAO{

    @Override
    public void inserirEstudante(String nome, String email, String senha) throws ChangeNotMade {
        try {
            conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario, tipoUsuario) VALUES (" +
                    stringBD(nome) + ", " + stringBD(email) + ", " + stringBD(senha) + ", 1);";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a inserção no banco de dados");
            }

            encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Estudante consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idEstudante + " AND tipoUsuario = 1);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Estudante(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Estudante consultarEstudante(String emailEstudante) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + emailEstudante + " AND tipoUsuario = 1);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Estudante(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerEstudante(int idEstudante) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "DELETE FROM usuario WHERE (idUsuario = " + idEstudante + ");";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarEstudante(Estudante estudanteAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "UPDATE usuario SET nomeUsuario = " + estudanteAlterado.getNomeUsuario() +
                    ", usuario.emailUsuario = " + estudanteAlterado.getEmailUsuario() + ", usuario.senhaUsuario = " +
                    estudanteAlterado.getSenhaUsuario() + " WHERE idUsuario = " + estudanteAlterado.getIdUsuario() + ";";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
