package dao.presenca;

import exception.ChangeNotMade;
import entities.Presenca;
import exception.UserWithoutPermission;

import java.util.Map;

public interface PresencaDAO {
    void inserirPresenca(int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws ChangeNotMade;
    Presenca consultarPresenca(int Presenca);
    void removerPresenca(int idPresenca) throws ChangeNotMade;
    void alterarPresenca(Presenca presencaAlterada) throws ChangeNotMade;
}
