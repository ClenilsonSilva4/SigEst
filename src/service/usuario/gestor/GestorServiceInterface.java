package service.usuario.gestor;

import entities.*;
import exception.*;

public interface GestorServiceInterface {
    void inserirEstudante(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade;
    Recurso consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable;
    void removerEstudante(int idEstudante, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    void alterarEstudante(int id, String nome, String email, String senha, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission;

    void inserirProfessor(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade;
    Avaliador consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable;
    void removerProfessor(int idProfessor, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission;
    void alterarProfessor(int id, String nome, String email, String senha, int idGestor) throws ChangeNotMade, DBUnavailable, UserWithoutPermission;

    void inserirGestor(String nome, String email, String senha) throws EmailAlreadyInUse, ChangeNotMade, DBUnavailable;
    Gestor consultarGestor(int idGestor) throws UserNotFoundException, DBUnavailable;
    void removerGestor(int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission;
    void alterarGestor(int id, String nome, String email, String senha, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;

    void inserirTurma(int idGestor, String nomeDisciplina, int capacidadeAlunos) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    void removerTurma(int idTurma, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    void alterarTurma(int id, String nomeDisciplina, int capacidade, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;

    void inserirPresenca(int idGestor, int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    AcompanhamentoRecurso consultarPresenca(int idGestor, int idPresenca);
    void alterarPresenca(int idGestor, AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws ChangeNotMade, DBUnavailable, UserWithoutPermission;
}
