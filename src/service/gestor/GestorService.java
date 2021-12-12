package service.gestor;

import dao.usuario.estudante.EstudanteDAO;
import dao.usuario.estudante.EstudanteDAOMySQL;
import dao.usuario.gestor.GestorDAO;
import dao.usuario.gestor.GestorDAOMySQL;
import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.usuario.professor.ProfessorDAO;
import dao.usuario.professor.ProfessorDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import entities.*;
import exception.*;

public class GestorService implements GestorServiceInterface{
    private final EstudanteDAO estudanteBD;
    private final ProfessorDAO professorBD;
    private final GestorDAO gestorBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public GestorService() {
        this.estudanteBD = new EstudanteDAOMySQL();
        this.professorBD = new ProfessorDAOMySQL();
        this.gestorBD = new GestorDAOMySQL();
        this.turmaBD = new TurmaDAOMySQL();
        this.presencaBD = new PresencaDAOMySQL();
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
    public void inserirTurma(int idGestor, String nomeDisciplina, int capacidadeAlunos) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
        turmaBD.inserirTurma(nomeDisciplina, capacidadeAlunos);
    }

    @Override
    public Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    @Override
    public void removerTurma(int idTurma, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
        turmaBD.removerTurma(idTurma);
    }

    @Override
    public void alterarTurma(Turma turmaAlterada, int idGestor) throws DBUnavailable, UserWithoutPermission, UserNotFoundException, ChangeNotMade {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }

        turmaBD.consultarTurma(turmaAlterada.getIdTurma());
        turmaBD.alterarTurma(turmaAlterada);
    }

    @Override
    public void inserirPresenca(int idGestor, int idTurma, int idProfessor, int idAluno, String dataPresenca,
                                boolean estavaPresente) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
        presencaBD.inserirPresenca(idTurma, idProfessor, idAluno, dataPresenca, estavaPresente);
    }

    @Override
    public Presenca consultarPresenca(int idGestor, int idPresenca) {
        return null;
    }

    @Override
    public void alterarPresenca(int idGestor, Presenca presencaAlterada) throws ChangeNotMade, DBUnavailable, UserWithoutPermission {
        try {
            gestorBD.consultarGestor(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }

        presencaBD.consultarPresenca(presencaAlterada.getIdTurma());
        presencaBD.alterarPresenca(presencaAlterada);
    }
}
