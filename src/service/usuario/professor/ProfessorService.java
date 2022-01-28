package service.usuario.professor;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.usuario.UsuarioDAO;
import dao.usuario.ProfessorDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.AcompanhamentoRecurso;
import entities.Avaliador;
import entities.ConjuntoRecurso;
import exception.UserWithoutPermission;

public class ProfessorService implements ProfessorServiceInterface{
    private final UsuarioDAO professorBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public ProfessorService() {
        turmaBD = new TurmaDAOMySQL();
        professorBD = new ProfessorDAOMySQL();
        presencaBD = new PresencaDAOMySQL();
    }

    @Override
    public Avaliador consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable {
        return new Avaliador(professorBD.consultarUsuario(idProfessor));
    }

    @Override
    public ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    @Override
    public void inserirPresenca(AcompanhamentoRecurso novaAcompanhamentoRecurso) throws ChangeNotMade, UserWithoutPermission, DBUnavailable {
        //Checar se o professor realmente dá aula nessa turma.
        if(checarPermissao(novaAcompanhamentoRecurso.getIdProfessor(), novaAcompanhamentoRecurso.getIdTurma())) {
            presencaBD.inserirPresenca(novaAcompanhamentoRecurso.getIdTurma(), novaAcompanhamentoRecurso.getIdProfessor(),
                    novaAcompanhamentoRecurso.getIdAluno(), novaAcompanhamentoRecurso.getData(), novaAcompanhamentoRecurso.isPresente());
        } else {
            throw new UserWithoutPermission("Este professor não possui permissão para inserir essa presença.");
        }
    }

    @Override
    public AcompanhamentoRecurso consultarPresenca(int idPresenca) {
        return presencaBD.consultarPresenca(idPresenca);
    }

    @Override
    public void removerPresenca(int idPresenca, int idProfessor) throws ChangeNotMade, UserWithoutPermission, DBUnavailable {
        //Checar se o professor realmente dá aula nessa turma.
        if(checarPermissao(idProfessor, presencaBD.consultarPresenca(idPresenca).getIdTurma())) {
            presencaBD.removerPresenca(idPresenca);
        } else {
            throw new UserWithoutPermission("Este professor não possui permissão para excluir essa presença.");
        }
    }

    @Override
    public void alterarPresenca(AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws UserWithoutPermission, ChangeNotMade, DBUnavailable {
        //Checar se o professor realmente dá aula nessa turma.
        if(checarPermissao(acompanhamentoRecursoAlterada.getIdProfessor(), acompanhamentoRecursoAlterada.getIdTurma())) {
            presencaBD.alterarPresenca(acompanhamentoRecursoAlterada);
        } else {
            throw new UserWithoutPermission("Este professor não possui permissão para alterar essa presença.");
        }
    }

    private boolean checarPermissao (int idFuncionario, int idTurma) throws DBUnavailable {
        try {
            consultarProfessor(idFuncionario);

            ConjuntoRecurso checarConjuntoRecurso = consultarTurma(idTurma);
            return checarConjuntoRecurso.containsProfessor(idFuncionario);
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}
