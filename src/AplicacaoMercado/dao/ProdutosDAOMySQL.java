package AplicacaoMercado.dao;

import AplicacaoMercado.entities.Produto;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import framework.DAO.RecursoDAOMySQL;
import framework.Domain.Recurso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAOMySQL implements RecursoDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public ProdutosDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    @Override
    public void adicionarRecurso(Recurso novoRecurso) throws ChangeNotMade, DBUnavailable {
        Produto novoProduto = (Produto) novoRecurso;
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO produto (nomeUsuario, validade, aprovacao) VALUES (" +
                    conexaoBD.stringBD(novoRecurso.getNome()) + ", " + conexaoBD.stringBD(novoProduto.getValidade()) + ", "
                    + novoRecurso.isEstaAprovado() + ");";

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
    public void removerRecurso(long recursoRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM produto WHERE (idUsuario = " + recursoRemovido + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<Recurso> listarRecursos() throws DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM produto";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            List<Recurso> todosAlunos = new ArrayList<>();

            while (resultadoConsulta.next()) {
                todosAlunos.add(new Produto(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), Boolean.getBoolean(resultadoConsulta.getString("aprovacao")),
                        resultadoConsulta.getString("validade")));
            }
            return todosAlunos;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarRecurso(Recurso recursoAlterado) throws ChangeNotMade, DBUnavailable {
        Produto curso = (Produto) recursoAlterado;
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE produto SET nomeUsuario = " + conexaoBD.stringBD(recursoAlterado.getNome()) +
                    ", curso = " + conexaoBD.stringBD(curso.getValidade()) +   recursoAlterado.isEstaAprovado() +
                    " WHERE idUsuario = " + recursoAlterado.getId() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public Recurso buscarRecursoPorID(long recursoID) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM produto WHERE id = " + recursoID + ";";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if (resultadoConsulta.next()) {
                return new Produto(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), Boolean.getBoolean(resultadoConsulta.getString("aprovacao")),
                        resultadoConsulta.getString("validade"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
