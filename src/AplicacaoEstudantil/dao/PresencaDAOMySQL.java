package AplicacaoEstudantil.dao;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import framework.dao.AcompanhamentoRecursoDAOMySQL;
import framework.domain.AcompanhamentoRecurso;

import java.sql.SQLException;
import java.util.List;

public class PresencaDAOMySQL implements AcompanhamentoRecursoDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public PresencaDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    @Override
    public void inserirAcompanhamentoRecurso(AcompanhamentoRecurso novoAcompanhamento) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO presenca (idProfessor, idAluno, dataPresenca, estavaPresente) VALUES (" +
                   novoAcompanhamento.getIdAvaliador() + ", " + novoAcompanhamento.getIdRecurso() +
                    conexaoBD.stringBD(novoAcompanhamento.getDataAcompanhamento()) + ", " + novoAcompanhamento.isAcompanhamento() + ");";

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
    public void alterarAcompanhamentoRecurso(AcompanhamentoRecurso acompanhamentoAlterado) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE presenca SET idProfessor = " + acompanhamentoAlterado.getIdAvaliador() +
                    ", idAluno = " + acompanhamentoAlterado.getIdRecurso() + ", dataPresenca = " +
                    conexaoBD.stringBD(acompanhamentoAlterado.getDataAcompanhamento()) + ", estavaPresente = " +
                    acompanhamentoAlterado.isAcompanhamento() + " WHERE idPresenca = " + acompanhamentoAlterado.getId() + ";";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerAcompanhamentoRecurso(long acompanhamentoRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM presenca WHERE (idPresenca = " + acompanhamentoRemovido + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<AcompanhamentoRecurso> consultarAcompanhamentosRecurso(long idRecurso) {
        return null;
    }
}
