package dao.presenca;

import dao.conexao.ConexaoSistemaDAO;
import exception.ChangeNotMade;
import entities.Presenca;
import exception.DBUnavailable;

import java.sql.SQLException;

public class PresencaDAOMySQL extends ConexaoSistemaDAO implements PresencaDAO{
    public void inserirPresenca(int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();

            String sqlComando = "INSERT INTO presenca (idTurma, idProfessor, idAluno, dataPresenca, estavaPresente) VALUES (" +
                    idTurma + ", " + idProfessor + ", " + idAluno + stringBD(dataPresenca) + ", " + estavaPresente + ");";

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
    public Presenca consultarPresenca(int idPresenca) {
        return null;
    }

    @Override
    public void removerPresenca(int idPresenca) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "DELETE FROM presenca WHERE (idPresenca = " + idPresenca + ");";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }

    }

    @Override
    public void alterarPresenca(Presenca presencaAlterada) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "UPDATE presenca SET idTurma = " + presencaAlterada.getIdTurma() + ", idProfessor = "
                    + presencaAlterada.getIdProfessor() + ", idAluno = " + presencaAlterada.getIdAluno() +
                    ", dataPresenca = " + stringBD(presencaAlterada.getData()) + ", estavaPresente = " +
                    presencaAlterada.isPresente() + " WHERE idPresenca = " + presencaAlterada.getId() + ";";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
