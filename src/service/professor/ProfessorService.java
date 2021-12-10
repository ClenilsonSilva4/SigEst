package service.professor;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.professor.ProfessorDAO;
import dao.professor.ProfessorDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Presenca;
import entities.Professor;
import entities.Turma;
import exception.UserWithoutPermission;

public class ProfessorService implements ProfessorServiceInterface{
    private final ProfessorDAO professorBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public ProfessorService() {
        turmaBD = new TurmaDAOMySQL();
        professorBD = new ProfessorDAOMySQL();
        presencaBD = new PresencaDAOMySQL();
    }

    @Override
    public Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable {
        return professorBD.consultarProfessor(idProfessor);
    }

    @Override
    public Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    @Override
    public void inserirPresenca(Presenca novaPresenca) throws ChangeNotMade, UserWithoutPermission, DBUnavailable {
        //Checar se o professor realmente dá aula nessa turma.
        if(checarPermissao(novaPresenca.getIdProfessor(), novaPresenca.getIdTurma())) {
            presencaBD.inserirPresenca(novaPresenca.getIdTurma(), novaPresenca.getIdProfessor(),
                    novaPresenca.getIdAluno(), novaPresenca.getData(), novaPresenca.isPresente());
        } else {
            throw new UserWithoutPermission("Este professor não possui permissão para inserir essa presença.");
        }
    }

    @Override
    public Presenca consultarPresenca(int idPresenca) {
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
    public void alterarPresenca(Presenca presencaAlterada) throws UserWithoutPermission, ChangeNotMade, DBUnavailable {
        //Checar se o professor realmente dá aula nessa turma.
        if(checarPermissao(presencaAlterada.getIdProfessor(), presencaAlterada.getIdTurma())) {
            presencaBD.alterarPresenca(presencaAlterada);
        } else {
            throw new UserWithoutPermission("Este professor não possui permissão para alterar essa presença.");
        }
    }

    private boolean checarPermissao (int idFuncionario, int idTurma) throws DBUnavailable {
        try {
            consultarProfessor(idFuncionario);

            Turma checarTurma = consultarTurma(idTurma);
            return checarTurma.containsProfessor(idFuncionario);
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}
