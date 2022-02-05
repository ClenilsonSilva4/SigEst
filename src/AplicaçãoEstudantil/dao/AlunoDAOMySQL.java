package AplicaçãoEstudantil.dao;

import AplicaçãoEstudantil.entities.Aluno;
import AplicaçãoEstudantil.exception.ChangeNotMade;
import AplicaçãoEstudantil.exception.DBUnavailable;
import AplicaçãoEstudantil.exception.UserNotFoundException;
import framework.DAO.RecursoDAOMySQL;
import framework.Domain.Recurso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOMySQL implements RecursoDAOMySQL {
    private ConexaoSistemaDAO conexaoBD;

    public AlunoDAOMySQL(ConexaoSistemaDAO conexaoBD) {
        this.conexaoBD = conexaoBD;
    }

    @Override
    public void adicionarRecurso(Recurso novoRecurso) throws ChangeNotMade, DBUnavailable {
        Aluno curso = (Aluno) novoRecurso;
        try {
            conexaoBD.conectar();

            String sqlComando = "INSERT INTO usuario (nomeUsuario, curso, aprovacao) VALUES (" +
                    conexaoBD.stringBD(novoRecurso.getNome()) + ", " + conexaoBD.stringBD(curso.getCurso()) + ", " + novoRecurso.isEstaAprovado() + ");";

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
    public void removerRecurso(Recurso recursoRemovido) throws ChangeNotMade, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "DELETE FROM usuario WHERE (idUsuario = " + recursoRemovido.getId() + ");";

            int resultado = conexaoBD.comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a remoção no banco de dados");
            }
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public List<Recurso> listarRecursos() throws UserNotFoundException, DBUnavailable {
        try {
            conexaoBD.conectar();
            String sqlComando = "SELECT * FROM recurso";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);
            List<Recurso> todosAlunos = new ArrayList<>();

            while (resultadoConsulta.next()) {
                todosAlunos.add(new Aluno(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), Boolean.getBoolean(resultadoConsulta.getString("aprovacao")),
                        resultadoConsulta.getString("curso")));
            }
            return todosAlunos;
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }

    @Override
    public void alterarRecurso(Recurso recursoAlterado) throws ChangeNotMade, DBUnavailable {
        Aluno curso = (Aluno) recursoAlterado;
        try {
            conexaoBD.conectar();
            String sqlComando = "UPDATE recurso SET nomeUsuario = " + conexaoBD.stringBD(recursoAlterado.getNome()) +
                    ", curso = " + conexaoBD.stringBD(curso.getCurso()) + ", aprovacao = " +
                    recursoAlterado.isEstaAprovado() + " WHERE idUsuario = " + recursoAlterado.getId() + ";";

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
            String sqlComando = "SELECT * FROM recurso WHERE id = " + recursoID + ";";

            ResultSet resultadoConsulta = conexaoBD.comandos.executeQuery(sqlComando);

            if (resultadoConsulta.next()) {
                return new Aluno(Long.getLong(resultadoConsulta.getString("id")),
                        resultadoConsulta.getString("nome"), Boolean.getBoolean(resultadoConsulta.getString("aprovacao")),
                        resultadoConsulta.getString("curso"));
            }
            throw new UserNotFoundException("Os dados inseridos não pertence a um usuario válido");
        } catch (SQLException e) {
            throw new DBUnavailable("Houve um erro de comunicação com o banco de dados");
        }
    }
}
