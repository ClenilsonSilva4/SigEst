package dao.presenca;

import exception.ChangeNotMade;
import entities.AcompanhamentoRecurso;
import exception.DBUnavailable;

public interface PresencaDAO {
    void inserirPresenca(int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws ChangeNotMade, DBUnavailable;
    AcompanhamentoRecurso consultarPresenca(int Presenca);
    void removerPresenca(int idPresenca) throws ChangeNotMade, DBUnavailable;
    void alterarPresenca(AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws ChangeNotMade, DBUnavailable;
}
