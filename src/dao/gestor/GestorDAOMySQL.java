package dao.gestor;

import dao.conexao.ConexaoSistemaDAO;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Gestor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorDAOMySQL extends ConexaoSistemaDAO implements GestorDAO {
    @Override
    public void inserirGestor(String nome, String email, String senha) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario, tipoUsuario) VALUES (" +
                    stringBD(nome) + ", " + stringBD(email) + ", " + stringBD(senha) + ", 3);";

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
    public Gestor consultarGestor(int idGestor) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (idUsuario = " + idGestor + " AND tipoUsuario = 3);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Gestor(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Gestor consultarGestor(String emailGestor) throws UserNotFoundException, DBUnavailable {
        try {
            conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + emailGestor + " AND tipoUsuario = 3);";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                return new Gestor(Integer.parseInt(resultadoConsulta.getString("idUsuario")),
                        resultadoConsulta.getString("nomeUsuario"), resultadoConsulta.getString("emailUsuario"),
                        resultadoConsulta.getString("senhaUsuario"));
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerGestor(int idGestor) throws ChangeNotMade {
        try {
            conectar();
            String sqlComando = "DELETE FROM usuario WHERE (idUsuario = " + idGestor + ");";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterarGestor(Gestor gestorAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "UPDATE usuario SET nomeUsuario = " + gestorAlterado.getNomeUsuario() +
                    ", usuario.emailUsuario = " + gestorAlterado.getEmailUsuario() + ", usuario.senhaUsuario = " +
                    gestorAlterado.getSenhaUsuario() + " WHERE idUsuario = " + gestorAlterado.getIdUsuario() + ";";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
