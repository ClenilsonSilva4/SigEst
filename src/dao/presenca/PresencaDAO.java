package dao.presenca;

import exception.ChangeNotMade;
import entities.Presenca;
import exception.DBUnavailable;

public interface PresencaDAO {
    void inserirPresenca(int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws ChangeNotMade, DBUnavailable;
    Presenca consultarPresenca(int Presenca);
    void removerPresenca(int idPresenca) throws ChangeNotMade, DBUnavailable;
    void alterarPresenca(Presenca presencaAlterada) throws ChangeNotMade, DBUnavailable;
}
