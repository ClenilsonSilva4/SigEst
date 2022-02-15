package AplicacaoControleQualidade.dao;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.EmailAlreadyInUse;
import exception.UserNotFoundException;
import framework.domain.Gestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorDAOMySQL implements framework.dao.GestorDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public GestorDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    @Override
    public void adicionarGestor(Gestor novoGestor) throws DBUnavailable, ChangeNotMade {
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario) VALUES (" +
                    conexaoBD.stringBD(novoGestor.getEmail()) + ", " + conexaoBD.stringBD(novoGestor.getEmail()) +
                    ", " + conexaoBD.stringBD(novoGestor.getSenha()) + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a inserção no banco de dados");
            }

            conexaoBD.encerrarConexao();
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerGestor(long gestorRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM avaliador WHERE (idUsuario = " + gestorRemovido + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarGestor(Gestor gestorAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE gestor SET nomeUsuario = " + conexaoBD.stringBD(gestorAlterado.getNome()) +
                    ", emailUsuario = " + conexaoBD.stringBD(gestorAlterado.getEmail()) + ", senhaUsuario = " +
                    conexaoBD.stringBD(gestorAlterado.getSenha()) + " WHERE idUsuario = " + gestorAlterado.getId() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Gestor buscarGestorPorID(long gestorID) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM gestor WHERE id = " + gestorID + ";";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if (resultadoConsulta.next()) {
                return new Gestor(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<Gestor> listarGestores() throws DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM gestor";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            List<Gestor> todosGestores = new ArrayList<>();

            while (resultadoConsulta.next()) {
                todosGestores.add(new Gestor(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha")));
            }

            return todosGestores;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public void checarEmail(String emailGestor) throws EmailAlreadyInUse, UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM gestor WHERE email = " + emailGestor + ";";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if (resultadoConsulta.next()) {
                throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public Gestor checarAcesso (String email, String senha) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM usuario WHERE (emailUsuario = " + conexaoBD.stringBD(email) +
                    " AND senhaUsuario = " + conexaoBD.stringBD(senha) + ");";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            if(resultadoConsulta.next()) {
                return new Gestor(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
