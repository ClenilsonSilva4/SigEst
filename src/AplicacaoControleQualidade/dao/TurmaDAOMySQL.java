package AplicacaoControleQualidade.dao;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import framework.DAO.ConjuntoRecursoDAOMySQL;
import framework.Domain.ConjuntoRecurso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAOMySQL implements ConjuntoRecursoDAOMySQL {
    private final ConexaoSistemaDAO conexaoBD;

    public TurmaDAOMySQL() {
        this.conexaoBD = new ConexaoSistemaDAO();
    }

    private List<Long> consultarIDProfessores(long idTurma) throws DBUnavailable {
        try {
            if(conexaoBD.conexao.isClosed()) {
                conexaoBD.conectar();
            }

            String sqlComando = "SELECT * FROM professores WHERE (idTurma = " + idTurma + ");";

            ResultSet resultadoProfessores = conexaoBD.comandos.executeQuery(sqlComando);
            List<Long> idProfessores = new ArrayList<>();

            while(resultadoProfessores.next()) {
                idProfessores.add(Long.getLong(resultadoProfessores.getString("idProfessor")));
            }

            return idProfessores;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    private List<Long> consultarIDAlunos(long idTurma) throws DBUnavailable {
        try {
            if(conexaoBD.conexao.isClosed()) {
                conexaoBD.conectar();
            }

            String sqlComando = "SELECT * FROM alunos WHERE (idTurma = " + idTurma + ");";

            ResultSet resultadoAlunos = conexaoBD.comandos.executeQuery(sqlComando);
            List<Long> idAlunos = new ArrayList<>();

            while(resultadoAlunos.next()) {
                idAlunos.add(Long.getLong(resultadoAlunos.getString("idAluno")));
            }

            return idAlunos;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void adicionarConjuntoRecurso(ConjuntoRecurso novoConjunto) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO turma (nomeDisciplina, capacidadeAlunos) VALUES (" +
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
            String sqlComando = "UPDATE turma SET nomeDisciplina = " + conexaoBD.stringBD(ambienteAlterado.getNomeConjunto()) +
                    ", capacidadeAlunos = " + ambienteAlterado.getCapacidadeMaxima() + " WHERE idTurma = " +
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
            String sqlComando = "DELETE FROM turma WHERE (idTurma = " + ambienteRemovido.getIdConjunto() + ");";

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
            String sqlComando = "SELECT * FROM turma WHERE (idTurma = " + idConjunto + ");";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                ConjuntoRecurso consultarConjuntoRecurso = new ConjuntoRecurso(Integer.parseInt(resultadoConsulta.getString("idTurma")),
                        resultadoConsulta.getString("nomeDisciplina"),
                        Integer.parseInt(resultadoConsulta.getString("capacidadeAlunos")));

                List<Long> idProfessores = consultarIDProfessores(idConjunto);

                if(!idProfessores.isEmpty()) {
                    consultarConjuntoRecurso.setAvaliadoresConjunto(idProfessores);
                }

                List<Long> idAlunos = consultarIDAlunos(idConjunto);

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
