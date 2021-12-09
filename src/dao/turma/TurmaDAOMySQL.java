package dao.turma;

import dao.conexao.ConexaoSistemaDAO;
import entities.Turma;

public class TurmaDAOMySQL extends ConexaoSistemaDAO implements TurmaDAO{
    public void inserirTurma(Turma novaTurma) {
        //TODO Acesso ao BD
    }

    public Turma consultarTurma(int idTurma) {
        //TODO Acesso ao BD
        return null;
    }

    @Override
    public void removerTurma(int idTurma) {

    }

    @Override
    public void alterarTurma(Turma turmaAlterada) {

    }
}
