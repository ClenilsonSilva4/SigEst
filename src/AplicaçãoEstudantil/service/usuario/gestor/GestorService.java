package AplicaçãoEstudantil.service.usuario.gestor;

import AplicaçãoEstudantil.entities.*;
import AplicaçãoEstudantil.exception.*;

public class GestorService implements GestorServiceInterface{
    private final UsuarioDAO estudanteBD;
    private final UsuarioDAO professorBD;
    private final UsuarioDAO gestorBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public GestorService() {
        this.estudanteBD = new EstudanteDAOMySQL();
        this.professorBD = new AvaliadorDAOMySQL();
        this.gestorBD = new GestorDAOMySQL();
        this.turmaBD = new TurmaDAOMySQL();
        this.presencaBD = new PresencaDAOMySQL();
    }

    @Override
    public void inserirEstudante(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade {
        try {
            estudanteBD.consultarUsuario(email, "");
            throw new EmailAlreadyInUse("Esse email j� pertence a um usu�rio cadastrado");
        } catch (UserNotFoundException e) {
            estudanteBD.inserirUsuario(nome, email, senha);
        }
    }

    @Override
    public Recurso consultarEstudante(int idEstudante) throws UserNotFoundException, DBUnavailable {
        return new Recurso(estudanteBD.consultarUsuario(idEstudante));
    }

    @Override
    public void removerEstudante(int idEstudante, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);

            estudanteBD.removerUsuario(idEstudante);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
    }

    @Override
    public void alterarEstudante(int id, String nome, String email, String senha, int idGestor) throws DBUnavailable, ChangeNotMade, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);

            Recurso alteracaoRecurso = (Recurso) estudanteBD.consultarUsuario(id);

            if(!nome.equals(alteracaoRecurso.getNomeUsuario())){
                alteracaoRecurso.setNomeUsuario(nome);
            }
            if(!email.equals(alteracaoRecurso.getEmailUsuario())) {
                alteracaoRecurso.setEmailUsuario(email);
            }
            if(!senha.equals(alteracaoRecurso.getSenhaUsuario())) {
                alteracaoRecurso.setSenhaUsuario(senha);
            }

            estudanteBD.alterarUsuario(alteracaoRecurso);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
    }

    @Override
    public void inserirProfessor(String nome, String email, String senha) throws DBUnavailable, EmailAlreadyInUse, ChangeNotMade {
        try {
            professorBD.consultarUsuario(email, "");
            throw new EmailAlreadyInUse("Esse email j� pertence a um usu�rio cadastrado");
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
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
    }

    @Override
    public void alterarProfessor(int id, String nome, String email, String senha, int idGestor) throws ChangeNotMade, DBUnavailable, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);

            Professor alteracaoAvaliador = (Professor) professorBD.consultarUsuario(id);

            if(!nome.equals(alteracaoAvaliador.getNomeUsuario())){
                alteracaoAvaliador.setNomeUsuario(nome);
            }
            if(!email.equals(alteracaoAvaliador.getEmailUsuario())) {
                alteracaoAvaliador.setEmailUsuario(email);
            }
            if(!senha.equals(alteracaoAvaliador.getSenhaUsuario())) {
                alteracaoAvaliador.setSenhaUsuario(senha);
            }

            professorBD.alterarUsuario(alteracaoAvaliador);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
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
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
    }

    @Override
    public void inserirTurma(int idGestor, String nomeDisciplina, int capacidadeAlunos) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
        turmaBD.inserirTurma(nomeDisciplina, capacidadeAlunos);
    }

    @Override
    public ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    @Override
    public void removerTurma(int idTurma, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
        turmaBD.removerTurma(idTurma);
    }

    @Override
    public void alterarTurma(int id, String nomeDisciplina, int capacidade, int idGestor) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);

            ConjuntoRecurso alteracaoConjuntoRecurso = turmaBD.consultarTurma(id);

            if(!nomeDisciplina.equals(alteracaoConjuntoRecurso.getNomeDisciplina())){
                alteracaoConjuntoRecurso.setNomeDisciplina(nomeDisciplina);
            }
            if(capacidade != alteracaoConjuntoRecurso.getCapacidadeAlunos()) {
                alteracaoConjuntoRecurso.setCapacidadeAlunos(capacidade);
            }

            turmaBD.alterarTurma(alteracaoConjuntoRecurso);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
    }

    @Override
    public void inserirPresenca(int idGestor, int idTurma, int idProfessor, int idAluno, String dataPresenca,
                                boolean estavaPresente) throws DBUnavailable, UserWithoutPermission, ChangeNotMade {
        try {
            gestorBD.consultarUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }
        presencaBD.inserirPresenca(idTurma, idProfessor, idAluno, dataPresenca, estavaPresente);
    }

    @Override
    public AcompanhamentoRecurso consultarPresenca(int idGestor, int idPresenca) {
        return null;
    }

    @Override
    public void alterarPresenca(int idGestor, AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws ChangeNotMade, DBUnavailable, UserWithoutPermission {
        try {
            gestorBD.consultarUsuario(idGestor);
        } catch (UserNotFoundException e) {
            throw new UserWithoutPermission("O ID informado n�o pertence a um gestor v�lido");
        }

        presencaBD.consultarPresenca(acompanhamentoRecursoAlterada.getIdTurma());
        presencaBD.alterarPresenca(acompanhamentoRecursoAlterada);
    }
}
