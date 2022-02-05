package AplicacaoEstudantil.dao;

import AplicacaoEstudantil.entities.Professor;
import AplicacaoEstudantil.exception.ChangeNotMade;
import AplicacaoEstudantil.exception.DBUnavailable;
import AplicacaoEstudantil.exception.UserNotFoundException;
import framework.DAO.AvaliadorDAOMySQL;
import framework.Domain.Avaliador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOMySQL implements AvaliadorDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public ProfessorDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    @Override
    public void adicionarAvaliador(Avaliador novoAvaliador) throws DBUnavailable, ChangeNotMade {
        Professor titularidade = (Professor) novoAvaliador;
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO avaliador (emailUsuario, nomeUsuario, senhaUsuario, titularidade) VALUES (" +
                    conexaoBD.stringBD(novoAvaliador.getEmail()) + ", " + conexaoBD.stringBD(novoAvaliador.getNome()) +
                    ", " + conexaoBD.stringBD(novoAvaliador.getSenha()) +
                    ", " + conexaoBD.stringBD(titularidade.getTitularidade()) + ";";

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
            String sqlComando = "DELETE FROM avaliador WHERE (idUsuario = " + avaliadorRemovido.getId() + ");";

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
        Professor titularidade = (Professor) avaliadorAlterado;
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE avaliador SET nomeUsuario = " + conexaoBD.stringBD(avaliadorAlterado.getNome()) +
                    ", emailUsuario = " + conexaoBD.stringBD(avaliadorAlterado.getEmail()) + ", senhaUsuario = " +
                    conexaoBD.stringBD(avaliadorAlterado.getSenha()) + ", titularidade = " + conexaoBD.stringBD(titularidade.getTitularidade())
                    + " WHERE idUsuario = " + avaliadorAlterado.getId() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Professor buscarAvaliadorPorID(long idAvaliador) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM avaliador WHERE id = " + idAvaliador + ";";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if (resultadoConsulta.next()) {
                return new Professor(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"), resultadoConsulta.getString("titularidade"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<Avaliador> listarAvaliadores() throws DBUnavailable, UserNotFoundException {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM avaliador";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            List<Avaliador> todosAvaliadores = new ArrayList<>();

            while (resultadoConsulta.next()) {
                todosAvaliadores.add(new Professor(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"), resultadoConsulta.getString("titularidade")));
            }
            return todosAvaliadores;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
