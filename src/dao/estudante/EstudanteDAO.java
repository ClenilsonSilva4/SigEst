package dao.estudante;

import service.Estudante;

public interface EstudanteDAO {
    void inserirEstudante(Estudante estudante);
    Estudante consultarEstudante(int idEstudante);
    void removerEstudante(int idEstudante);
    void alterarEstudante(Estudante estudanteAlterado);
}
