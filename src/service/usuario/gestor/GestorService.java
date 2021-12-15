package service.usuario.gestor;

import dao.usuario.UsuarioDAO;
import dao.usuario.EstudanteDAOMySQL;
import dao.usuario.GestorDAOMySQL;
import dao.usuario.ProfessorDAOMySQL;
import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import entities.*;
import exception.*;

public class GestorService implements GestorServiceInterface{
    private final UsuarioDAO estudanteBD;
    private final UsuarioDAO professorBD;
    private final UsuarioDAO gestorBD;
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
            estudanteBD.consultarUsuario(email, "");
            throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
        } catch (UserNotFoundException e) {
            estudanteBD.inserirUsuario(nome, email, senha);
        }
    }

    @Override
    public Estudante consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable {
        return new Estudante(estudanteBD.consultarUsuario(idEstudante));
    }

    @Override
    public void removerEstudante(int idEstudante, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);

            estudanteBD.removerUsuario(idEstudante);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void alterarEstudante(int id, String nome, String email, String senha, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);

            Estudante alteracaoEstudante = (Estudante) estudanteBD.consultarUsuario(id);

            if(!nome.equals(alteracaoEstudante.getNomeUsuario())){
                alteracaoEstudante.setNomeUsuario(nome);
            }
            if(!email.equals(alteracaoEstudante.getEmailUsuario())) {
                alteracaoEstudante.setEmailUsuario(email);
            }
            if(!senha.equals(alteracaoEstudante.getSenhaUsuario())) {
                alteracaoEstudante.setSenhaUsuario(senha);
            }

            estudanteBD.alterarUsuario(alteracaoEstudante);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void inserirProfessor(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade {
        try {
            professorBD.consultarUsuario(email, "");
            throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
        } catch (UserNotFoundException e) {
            professorBD.inserirUsuario(nome, email, senha);
        }
    }

    @Override
    public Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable {
        return new Professor(professorBD.consultarUsuario(idProfessor));
    }

    @Override
    public void removerProfessor(int idProfessor, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);

            professorBD.removerUsuario(idProfessor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void alterarProfessor(int id, String nome, String email, String senha, int idGestor) throws ChangeNotMade, DBUnavailable, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);

            Professor alteracaoProfessor = (Professor) professorBD.consultarUsuario(id);

            if(!nome.equals(alteracaoProfessor.getNomeUsuario())){
                alteracaoProfessor.setNomeUsuario(nome);
            }
            if(!email.equals(alteracaoProfessor.getEmailUsuario())) {
                alteracaoProfessor.setEmailUsuario(email);
            }
            if(!senha.equals(alteracaoProfessor.getSenhaUsuario())) {
                alteracaoProfessor.setSenhaUsuario(senha);
            }

            professorBD.alterarUsuario(alteracaoProfessor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void inserirGestor(String nome, String email, String senha) throws EmailAlreadyInUse, ChangeNotMade, DBUnavailable {
        try {
            gestorBD.consultarUsuario(email, "");
            throw new EmailAlreadyInUse("Esse email já pertence a um usuário cadastrado");
        } catch (UserNotFoundException e) {
            gestorBD.inserirUsuario(nome, email, senha);
        }
    }

    @Override
    public Gestor consultarGestor(int idGestor) throws UserNotFoundException, DBUnavailable {
        return new Gestor(gestorBD.consultarUsuario(idGestor));
    }

    @Override
    public void removerGestor(int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);

            gestorBD.removerUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void alterarGestor(int id, String nome, String email, String senha, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);

            Gestor alteracaoGestor = (Gestor) gestorBD.consultarUsuario(id);

            if(!nome.equals(alteracaoGestor.getNomeUsuario())){
                alteracaoGestor.setNomeUsuario(nome);
            }
            if(!email.equals(alteracaoGestor.getEmailUsuario())) {
                alteracaoGestor.setEmailUsuario(email);
            }
            if(!senha.equals(alteracaoGestor.getSenhaUsuario())) {
                alteracaoGestor.setSenhaUsuario(senha);
            }

            gestorBD.alterarUsuario(alteracaoGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void inserirTurma(int idGestor, String nomeDisciplina, int capacidadeAlunos) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);
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
            gestorBD.consultarUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
        turmaBD.removerTurma(idTurma);
    }

    @Override
    public void alterarTurma(int id, String nomeDisciplina, int capacidade, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);

            Turma alteracaoTurma = turmaBD.consultarTurma(id);

            if(!nomeDisciplina.equals(alteracaoTurma.getNomeDisciplina())){
                alteracaoTurma.setNomeDisciplina(nomeDisciplina);
            }
            if(capacidade != alteracaoTurma.getCapacidadeAlunos()) {
                alteracaoTurma.setCapacidadeAlunos(capacidade);
            }

            turmaBD.alterarTurma(alteracaoTurma);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }
    }

    @Override
    public void inserirPresenca(int idGestor, int idTurma, int idProfessor, int idAluno, String dataPresenca,
                                boolean estavaPresente) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);
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
            gestorBD.consultarUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado não pertence a um gestor válido");
        }

        presencaBD.consultarPresenca(presencaAlterada.getIdTurma());
        presencaBD.alterarPresenca(presencaAlterada);
    }
}
