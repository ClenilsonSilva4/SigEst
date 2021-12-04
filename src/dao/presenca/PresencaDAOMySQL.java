package dao.presenca;

import service.Presenca;
import java.util.Map;

public class PresencaDAOMySQL implements PresencaDAO{
    public void inserirPresenca(Presenca adicionarPresenca) {
        //TODO
    }

    @Override
    public Map<String, Presenca> consultarPresenca(int idPresenca, int idUsuario) {
        return null;
    }

    @Override
    public void removerPresenca(int idPresenca, int idFuncionario) {

    }

    @Override
    public void alterarPresenca(Presenca presencaAlterada) {

    }
}
