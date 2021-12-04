package dao.presenca;

import service.Presenca;
import java.util.Map;

public interface PresencaDAO {
    void inserirPresenca(Presenca novaPresenca);
    Map<String, Presenca> consultarPresenca(int idTurma, int idUsuario);
    void removerPresenca(int idPresenca, int idFuncionario);
    void alterarPresenca(Presenca presencaAlterada);
}
