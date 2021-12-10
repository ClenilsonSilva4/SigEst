package dao.turma;

import dao.conexao.ConexaoSistemaDAO;
import entities.Turma;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAOMySQL extends ConexaoSistemaDAO implements TurmaDAO{
    public void inserirTurma(String nomeDisciplina, int capacidadeAlunos) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();

            String sqlComando = "INSERT INTO turma (nomeDisciplina, capacidadeAlunos) VALUES (" +
                    stringBD(nomeDisciplina) + ", " + capacidadeAlunos + ");";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a inserção no banco de dados");
            }

            encerrarConexao();
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    public Turma consultarTurma(int idTurma) throws DBUnavailable, UserNotFoundException {
        try {
            conectar();
            String sqlComando = "SELECT * FROM turma WHERE (idTurma = " + idTurma + ");";

            ResultSet resultadoConsulta = comandos.executeQuery(sqlComando);

            if(resultadoConsulta.next()) {
                Turma consultarTurma = new Turma(Integer.parseInt(resultadoConsulta.getString("idTurma")),
                        resultadoConsulta.getString("nomeDisciplina"),
                        Integer.parseInt(resultadoConsulta.getString("capacidadeAlunos")));

                List<Integer> idProfessores = consultarIDProfessores(idTurma);

                if(!idProfessores.isEmpty()) {
                    consultarTurma.setIdProfessores(idProfessores);
                }

                List<Integer> idAlunos = consultarIDAlunos(idTurma);

                if(!idAlunos.isEmpty()) {
                    consultarTurma.setIdEstudantes(idAlunos);
                }

                return consultarTurma;
            }
            throw new UserNotFoundException("O ID não pertence a um usuário válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void removerTurma(int idTurma) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "DELETE FROM turma WHERE (idTurma = " + idTurma + ");";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarTurma(Turma turmaAlterada) throws ChangeNotMade, DBUnavailable {
        try {
            conectar();
            String sqlComando = "UPDATE turma SET nomeDisciplina = " + stringBD(turmaAlterada.getNomeDisciplina()) +
                    ", capacidadeAlunos = " + turmaAlterada.getCapacidadeAlunos() + " WHERE idTurma = " +
                    turmaAlterada.getIdTurma() + ";";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a alteração no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    private List<Integer> consultarIDProfessores(int idTurma) throws DBUnavailable {
        try {
            if(conexaoBD.isClosed()) {
                conectar();
            }

            String sqlComando = "SELECT * FROM professores WHERE (idTurma = " + idTurma + ");";

            ResultSet resultadoProfessores = comandos.executeQuery(sqlComando);
            List<Integer> idProfessores = new ArrayList<>();

            while(resultadoProfessores.next()) {
                idProfessores.add(Integer.parseInt(resultadoProfessores.getString("idProfessor")));
            }

            return idProfessores;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    private List<Integer> consultarIDAlunos(int idTurma) throws DBUnavailable {
        try {
            if(conexaoBD.isClosed()) {
                conectar();
            }

            String sqlComando = "SELECT * FROM alunos WHERE (idTurma = " + idTurma + ");";

            ResultSet resultadoAlunos = comandos.executeQuery(sqlComando);
            List<Integer> idAlunos = new ArrayList<>();

            while(resultadoAlunos.next()) {
                idAlunos.add(Integer.parseInt(resultadoAlunos.getString("idAluno")));
            }

            return idAlunos;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
