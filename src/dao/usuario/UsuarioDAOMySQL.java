package dao.usuario;

import dao.conexao.ConexaoSistemaDAO;
import entities.Estudante;
import entities.Gestor;
import entities.Professor;
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

            String sqlComando = "INSERT INTO usuario (nomeUsuario, emailUsuario, senhaUsuario) VALUES (" +
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
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idUsuario + ");";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return retornarUsuarioConsulta(resultadoConsulta);
            }
            throw new UserNotFoundException("Os dados inseridos não pertencem a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Usuario consultarUsuario(String emailUsuario, String senha) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + stringBD(emailUsuario);

            if (!senha.isEmpty()) {
                sqlComando += " AND senhaUsuario = " + stringBD(senha);
            }

            sqlComando += ");";
            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);
            if(resultadoConsulta.next()) {
                return retornarUsuarioConsulta(resultadoConsulta);
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    private Usuario retornarUsuarioConsulta(ResultSet resultadoConsulta) throws SQLException{
        switch (resultadoConsulta.getString("tipoUsuario")) {
            case "1":
                return new Estudante(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            case "2":
                return new Professor(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            case "3":
                return new Gestor(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            default:
                return null;
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
            String sqlComando = "UPDATE usuario SET nomeUsuario = " + stringBD(usuarioAlterado.getNomeUsuario()) +
                    ", emailUsuario = " + stringBD(usuarioAlterado.getEmailUsuario()) + ", senhaUsuario = " +
                    stringBD(usuarioAlterado.getSenhaUsuario()) + " WHERE idUsuario = " + usuarioAlterado.getIdUsuario() + ";";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
