package AplicacaoMercado.dao;


import AplicacaoMercado.entities.Estocador;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import framework.DAO.AvaliadorDAOMySQL;
import framework.Domain.Avaliador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstocadorDAOMySQL implements AvaliadorDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public EstocadorDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    @Override
    public void adicionarAvaliador(Avaliador novoAvaliador) throws DBUnavailable, ChangeNotMade {
        Estocador titularidade = (Estocador) novoAvaliador;
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO estocador (emailUsuario, nomeUsuario, senhaUsuario, dataAdmissao) VALUES (" +
                    conexaoBD.stringBD(novoAvaliador.getEmail()) + ", " + conexaoBD.stringBD(novoAvaliador.getNome()) +
                    ", " + conexaoBD.stringBD(novoAvaliador.getSenha()) +
                    ", " + conexaoBD.stringBD(titularidade.getDataAdmissao()) + ";";

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
    public void removerAvaliador(long avaliadorRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM estocador WHERE (idUsuario = " + avaliadorRemovido + ");";

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
        Estocador titularidade = (Estocador) avaliadorAlterado;
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE estocador SET nomeUsuario = " + conexaoBD.stringBD(avaliadorAlterado.getNome()) +
                    ", emailUsuario = " + conexaoBD.stringBD(avaliadorAlterado.getEmail()) + ", senhaUsuario = " +
                    conexaoBD.stringBD(avaliadorAlterado.getSenha()) + ", titularidade = " + conexaoBD.stringBD(titularidade.getDataAdmissao())
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
    public Estocador buscarAvaliadorPorID(long idAvaliador) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM avaliador WHERE id = " + idAvaliador + ";";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if (resultadoConsulta.next()) {
                return new Estocador(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"), resultadoConsulta.getString("dataAdmissao"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<Avaliador> listarAvaliadores() throws DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM avaliador";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            List<Avaliador> todosAvaliadores = new ArrayList<>();

            while (resultadoConsulta.next()) {
                todosAvaliadores.add(new Estocador(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"), resultadoConsulta.getString("dataAdmissao")));
            }
            return todosAvaliadores;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public Estocador checarAcesso (String email, String senha) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM avaliador WHERE (emailUsuario = " + conexaoBD.stringBD(email) +
                    " AND senhaUsuario = " + conexaoBD.stringBD(senha) + ");";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            if(resultadoConsulta.next()) {
                return new Estocador(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("senha"), resultadoConsulta.getString("dataAdmissao"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
