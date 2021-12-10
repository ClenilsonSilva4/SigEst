package service.gestor;

import dao.estudante.EstudanteDAO;
import dao.estudante.EstudanteDAOMySQL;
import dao.gestor.GestorDAO;
import dao.gestor.GestorDAOMySQL;
import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.professor.ProfessorDAO;
import dao.professor.ProfessorDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import entities.*;
import exception.*;

public class GestorService implements GestorServiceInterface{
    private final EstudanteDAO estudanteBD;
    private final ProfessorDAO professorBD;
    private final GestorDAO gestorBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaDAO;

    public GestorService() {
        this.estudanteBD = new EstudanteDAOMySQL();
        this.professorBD = new ProfessorDAOMySQL();
        this.gestorBD = new GestorDAOMySQL();
        this.turmaBD = new TurmaDAOMySQL();
        this.presencaDAO = new PresencaDAOMySQL();
    }

    @Override
    public void inserirEstudante(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade {
        try {
            estudanteBD.consultarEstudante(email);
            throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
        } catch (UserNotFoundException e) {
            estudanteBD.inserirEstudante(nome, email, senha);
        }
    }

    @Override
    public Estudante consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable {
        return estudanteBD.consultarEstudante(idEstudante);
    }

    @Override
    public void removerEstudante(int idEstudante, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarGestor(idGestor);

            estudanteBD.removerEstudante(idEstudante);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void alterarEstudante(Estudante estudanteAlterado) throws UserNotFoundException, DBUnavailable, ChangeNotMade {
        estudanteBD.consultarEstudante(estudanteAlterado.getIdUsuario());

        estudanteBD.alterarEstudante(estudanteAlterado);
    }

    @Override
    public void inserirProfessor(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade {
        try {
            professorBD.consultarProfessor(email);
            throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
        } catch (UserNotFoundException e) {
            professorBD.inserirProfessor(nome, email, senha);
        }
    }

    @Override
    public Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable {
        return professorBD.consultarProfessor(idProfessor);
    }

    @Override
    public void removerProfessor(int idProfessor, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission {
        try {
            gestorBD.consultarGestor(idGestor);

            professorBD.removerProfessor(idProfessor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void alterarProfessor(Professor professorAlterado, int idGestor) throws ChangeNotMade, DBUnavailable, UserNotFoundException, UserWithoutPermission {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }

        professorBD.consultarProfessor(professorAlterado.getIdUsuario());
        professorBD.alterarProfessor(professorAlterado);
    }

    @Override
    public void inserirGestor(String nome, String email, String senha) throws EmailAlreadyInUse, ChangeNotMade, DBUnavailable {
        try {
            gestorBD.consultarGestor(email);
            throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
        } catch (UserNotFoundException e) {
            gestorBD.inserirGestor(nome, email, senha);
        }
    }

    @Override
    public Gestor consultarGestor(int idGestor) throws UserNotFoundException, DBUnavailable {
        return gestorBD.consultarGestor(idGestor);
    }

    @Override
    public void removerGestor(int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission {
        try {
            gestorBD.consultarGestor(idGestor);

            gestorBD.removerGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void alterarGestor(Gestor gestorAlterado, int idGestor) throws DBUnavailable, UserWithoutPermission, UserNotFoundException, ChangeNotMade {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }

        gestorBD.consultarGestor(gestorAlterado.getIdUsuario());
        gestorBD.alterarGestor(gestorAlterado);
    }

    @Override
    public void inserirTurma(String nomeDisciplina, int capacidadeAlunos) {

    }

    @Override
    public Turma consultarTurma(int idTurma) {
        return null;
    }

    @Override
    public void removerTurma(int idTurma) {

    }

    @Override
    public void alterarTurma(Turma turmaAlterada) {

    }

    @Override
    public void inserirPresenca(String nome, String email, String senha) {

    }

    @Override
    public Presenca consultarPresenca(int idGestor) {
        return null;
    }

    @Override
    public void removerPresenca(int idGestor) {

    }

    @Override
    public void alterarPresenca(Presenca presencaAlterada) {

    }
}
