package dao.estudante;

import exception.UserNotFoundException;
import service.estudante.Estudante;

import java.sql.SQLException;

public interface EstudanteDAO {
    void inserirEstudante(String nome, String email, String senha) throws SQLException;
    Estudante consultarEstudante(int idEstudante) throws UserNotFoundException;
    void removerEstudante(int idEstudante);
    void alterarEstudante(Estudante estudanteAlterado);
}
