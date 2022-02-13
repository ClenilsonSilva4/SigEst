package AplicacaoMercado.dao;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import framework.DAO.ConjuntoRecursoDAOMySQL;
import framework.Domain.ConjuntoRecurso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrateleiraDAOMySQL implements ConjuntoRecursoDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public PrateleiraDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    private List<Long> consultarIDEstocadores(long idPrateleira) throws DBUnavailable {
        try {
            if(conexaoBD.conexao.isClosed()) {
                conexaoBD.conectar();
            }

            String sqlComando = "SELECT * FROM estocadores WHERE (idPrateleira = " + idPrateleira + ");";

            ResultSet resultadoEstocadores = conexaoBD.comandos.executeQuery(sqlComando);
            List<Long> idProfessores = new ArrayList<>();

            while(resultadoEstocadores.next()) {
                idProfessores.add(Long.getLong(resultadoEstocadores.getString("idEstocador")));
            }

            return idProfessores;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    private List<Long> consultarIDProdutos(long idPrateleira) throws DBUnavailable {
        try {
            if(conexaoBD.conexao.isClosed()) {
                conexaoBD.conectar();
            }

            String sqlComando = "SELECT * FROM produtos WHERE (idPrateleira = " + idPrateleira + ");";

            ResultSet resultadoProdutos = conexaoBD.comandos.executeQuery(sqlComando);
            List<Long> idProdutos = new ArrayList<>();

            while(resultadoProdutos.next()) {
                idProdutos.add(Long.getLong(resultadoProdutos.getString("idAluno")));
            }

            return idProdutos;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void adicionarConjuntoRecurso(ConjuntoRecurso novoConjunto) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO prateleira (nome, capacidade) VALUES (" +
                    conexaoBD.stringBD(novoConjunto.getNomeConjunto()) + ", " + novoConjunto.getCapacidadeMaxima() + ");";

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
    public void alterarConjuntoRecurso(ConjuntoRecurso ambienteAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE prateleira SET nome = " + conexaoBD.stringBD(ambienteAlterado.getNomeConjunto()) +
                    ", capacidadeProdutos = " + ambienteAlterado.getCapacidadeMaxima() + " WHERE idTurma = " +
                    ambienteAlterado.getIdConjunto() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerConjuntoRecurso(ConjuntoRecurso ambienteRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM prateleira WHERE (idPrateleira = " + ambienteRemovido.getIdConjunto() + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<ConjuntoRecurso> consultarConjuntosRecurso() {
        return null;
    }

    @Override
    public ConjuntoRecurso consultarConjuntoID(long idConjunto) throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM prateleira WHERE (idPrateleira = " + idConjunto + ");";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                ConjuntoRecurso consultarConjuntoRecurso = new ConjuntoRecurso(Integer.parseInt(resultadoConsulta.getString("idTurma")),
                        resultadoConsulta.getString("nomeDisciplina"),
                        Integer.parseInt(resultadoConsulta.getString("capacidadeAlunos")));

                List<Long> idProfessores = consultarIDEstocadores(idConjunto);

                if(!idProfessores.isEmpty()) {
                    consultarConjuntoRecurso.setAvaliadoresConjunto(idProfessores);
                }

                List<Long> idAlunos = consultarIDProdutos(idConjunto);

                while(!idAlunos.isEmpty()) {
                    consultarConjuntoRecurso.setRecursosConjunto(idAlunos);
                }

                return consultarConjuntoRecurso;
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
