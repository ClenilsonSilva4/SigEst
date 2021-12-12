package service.gestor;

import entities.*;
import exception.*;

public interface GestorServiceInterface {
    void inserirEstudante(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade;
    Estudante consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable;
    void removerEstudante(int idEstudante, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    void alterarEstudante(Estudante estudanteAlterado) throws UserNotFoundException, DBUnavailable, ChangeNotMade;

    void inserirProfessor(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade;
    Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable;
    void removerProfessor(int idProfessor, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission;
    void alterarProfessor(Professor professorAlterado, int idGestor) throws ChangeNotMade, DBUnavailable, UserNotFoundException, UserWithoutPermission;

    void inserirGestor(String nome, String email, String senha) throws EmailAlreadyInUse, ChangeNotMade, DBUnavailable;
    Gestor consultarGestor(int idGestor) throws UserNotFoundException, DBUnavailable;
    void removerGestor(int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission;
    void alterarGestor(Gestor gestorAlterado, int idGestor) throws DBUnavailable, UserWithoutPermission, UserNotFoundException, ChangeNotMade;

    void inserirTurma(int idGestor, String nomeDisciplina, int capacidadeAlunos) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable;
    void removerTurma(int idTurma, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    void alterarTurma(Turma turmaAlterada, int idGestor) throws DBUnavailable, UserWithoutPermission, UserNotFoundException, ChangeNotMade;

    void inserirPresenca(int idGestor, int idTurma, int idProfessor, int idAluno, String dataPresenca, boolean estavaPresente) throws DBUnavailable, UserWithoutPermission, ChangeNotMade;
    Presenca consultarPresenca(int idGestor, int idPresenca);
    void alterarPresenca(int idGestor, Presenca presencaAlterada) throws ChangeNotMade, DBUnavailable, UserWithoutPermission;
}
