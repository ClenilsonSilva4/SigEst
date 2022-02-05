package AplicaçãoEstudantil.dao;

import AplicaçãoEstudantil.exception.ChangeNotMade;
import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.UserNotFoundException;
import Domain.Avaliador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliadorDAOMySQL implements framework.DAO.AvaliadorDAOMySQL {
    private ConexaoSistemaDAO conexaoBD;

    public AvaliadorDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    @Override
    public void adicionarAvaliador(Avaliador novoAvaliador) throws DBUnavailable, ChangeNotMade {
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario) VALUES (" +
                    conexaoBD.stringBD(novoAvaliador.getEmail()) + ", " + conexaoBD.stringBD(novoAvaliador.getNome()) +
                    ", " + conexaoBD.stringBD(novoAvaliador.getSenha()) + ";";

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
    public void removerAvaliador(Avaliador avaliadorRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM usuario WHERE (idUsuario = " + avaliadorRemovido.getId() + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarAvaliador(Avaliador avaliadorAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE usuario SET nomeUsuario = " + conexaoBD.stringBD(avaliadorAlterado.getNome()) +
                    ", emailUsuario = " + conexaoBD.stringBD(avaliadorAlterado.getEmail()) + ", senhaUsuario = " +
                    conexaoBD.stringBD(avaliadorAlterado.getSenha()) + " WHERE idUsuario = " + avaliadorAlterado.getId() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Avaliador buscarAvaliadorPorID() {
        return null;
    }

    @Override
    public List listarAvaliadores() throws DBUnavailable, UserNotFoundException {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM avaliador";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            List<Avaliador> todosAvaliadores = new ArrayList<>();

            while (resultadoConsulta.next()) {
                todosAvaliadores.add(new Avaliador(resultadoConsulta.getString("id"), resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"), resultadoConsulta.getString("senha")));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
