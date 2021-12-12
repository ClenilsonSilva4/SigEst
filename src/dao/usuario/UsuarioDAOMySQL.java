package dao.usuario;

import dao.conexao.ConexaoSistemaDAO;
import entities.Usuario;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOMySQL extends ConexaoSistemaDAO implements UsuarioDAO {
    @Override
    public void inserirUsuario(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario) VALUES (" +
                    stringBD(nome) + ", " + stringBD(email) + ", " + stringBD(senha) + ");";

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
    public Usuario consultarUsuario(int idUsuario) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idUsuario + " AND tipoUsuario = 1);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Usuario(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Usuario consultarUsuario(String emailUsuario) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + emailUsuario + " AND tipoUsuario = 1);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Usuario(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerUsuario(int idUsuario) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "DELETE FROM usuario WHERE (idUsuario = " + idUsuario + ");";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarUsuario(Usuario usuarioAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "UPDATE usuario SET nomeUsuario = " + usuarioAlterado.getNomeUsuario() +
                    ", usuario.emailUsuario = " + usuarioAlterado.getEmailUsuario() + ", usuario.senhaUsuario = " +
                    usuarioAlterado.getSenhaUsuario() + " WHERE idUsuario = " + usuarioAlterado.getIdUsuario() + ";";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
