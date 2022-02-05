package AplicacaoEstudantil.dao.presenca;

import framework.exception.ChangeNotMade;
import framework.entities.AcompanhamentoRecurso;
import framework.exception.DBUnavailable;

public interface PresencaDAO {
    void inserirPresenca(int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws ChangeNotMade, DBUnavailable;
    AcompanhamentoRecurso consultarPresenca(int Presenca);
    void removerPresenca(int idPresenca) throws ChangeNotMade, DBUnavailable;
    void alterarPresenca(AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws ChangeNotMade, DBUnavailable;
}
