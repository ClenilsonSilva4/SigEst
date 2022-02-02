package AplicaçãoEstudantil.dao;

import framework.entities.Recurso;
import framework.exception.ChangeNotMade;
import framework.exception.DBUnavailable;
import framework.exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecursoDAOMySQL {
    private ConexaoSistemaDAO conexaoBD;

    public RecursoDAOMySQL(ConexaoSistemaDAO conexaoBD) {
        this.conexaoBD = conexaoBD;
    }

    public void inserirRecurso(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO usuario (nomeUsuario, emailUsuario, senhaUsuario) VALUES (" +
                    conexaoBD.stringBD(nome) + ", " + conexaoBD.stringBD(email) + ", " + conexaoBD.stringBD(senha) + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a inserção no banco de dados");
            }

            conexaoBD.encerrarConexao();
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public Recurso consultarUsuario(int idUsuario) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idUsuario + ");";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return retornarUsuarioConsulta(resultadoConsulta);
            }
            throw new UserNotFoundException("Os dados inseridos não pertencem a um usuÃ¡rio vÃ¡lido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public Recurso consultarUsuario(String emailUsuario, String senha) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + conexaoBD.stringBD(emailUsuario);

            if (!senha.isEmpty()) {
                sqlComando += " AND senhaUsuario = " + conexaoBD.stringBD(senha);
            }

            sqlComando += ");";
            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            if(resultadoConsulta.next()) {
                return retornarUsuarioConsulta(resultadoConsulta);
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    private Recurso retornarUsuarioConsulta(ResultSet resultadoConsulta) throws SQLException{
        return null;
    }

    public void removerUsuario(int idUsuario) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM usuario WHERE (idUsuario = " + idUsuario + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public void alterarUsuario(Recurso usuarioAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE usuario SET nomeUsuario = " + conexaoBD.stringBD(usuarioAlterado.getNomeUsuario()) +
                    ", emailUsuario = " + conexaoBD.stringBD(usuarioAlterado.getEmailUsuario()) + ", senhaUsuario = " +
                    conexaoBD.stringBD(usuarioAlterado.getSenhaUsuario()) + " WHERE idUsuario = " + usuarioAlterado.getIdUsuario() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
