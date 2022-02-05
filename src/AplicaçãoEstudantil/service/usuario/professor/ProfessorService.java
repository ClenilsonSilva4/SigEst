package AplicaçãoEstudantil.service.usuario.professor;

import AplicaçãoEstudantil.dao.presenca.PresencaDAO;
import AplicaçãoEstudantil.dao.presenca.PresencaDAOMySQL;
import AplicaçãoEstudantil.dao.AvaliadorDAOMySQL;
import AplicaçãoEstudantil.dao.turma.TurmaDAO;
import AplicaçãoEstudantil.dao.turma.TurmaDAOMySQL;
import AplicaçãoEstudantil.exception.*;
import AplicaçãoEstudantil.entities.*;

public class ProfessorService implements ProfessorServiceInterface{
    private final UsuarioDAO professorBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public ProfessorService() {
        turmaBD = new TurmaDAOMySQL();
        professorBD = new AvaliadorDAOMySQL();
        presencaBD = new PresencaDAOMySQL();
    }

    @Override
    public Professor consultarProfessor(int idProfessor) throws UserNotFoundException, DBUnavailable {
        return new Professor(professorBD.consultarUsuario(idProfessor));
    }

    @Override
    public ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    @Override
    public void inserirPresenca(AcompanhamentoRecurso novaAcompanhamentoRecurso) throws ChangeNotMade, UserWithoutPermission, DBUnavailable {
        //Checar se o professor realmente d� aula nessa turma.
        if(checarPermissao(novaAcompanhamentoRecurso.getIdProfessor(), novaAcompanhamentoRecurso.getIdTurma())) {
            presencaBD.inserirPresenca(novaAcompanhamentoRecurso.getIdTurma(), novaAcompanhamentoRecurso.getIdProfessor(),
                    novaAcompanhamentoRecurso.getIdAluno(), novaAcompanhamentoRecurso.getData(), novaAcompanhamentoRecurso.isPresente());
        } else {
            throw new UserWithoutPermission("Este professor n�o possui permiss�o para inserir essa presen�a.");
        }
    }

    @Override
    public AcompanhamentoRecurso consultarPresenca(int idPresenca) {
        return presencaBD.consultarPresenca(idPresenca);
    }

    @Override
    public void removerPresenca(int idPresenca, int idProfessor) throws ChangeNotMade, UserWithoutPermission, DBUnavailable {
        //Checar se o professor realmente d� aula nessa turma.
        if(checarPermissao(idProfessor, presencaBD.consultarPresenca(idPresenca).getIdTurma())) {
            presencaBD.removerPresenca(idPresenca);
        } else {
            throw new UserWithoutPermission("Este professor n�o possui permiss�o para excluir essa presen�a.");
        }
    }

    @Override
    public void alterarPresenca(AcompanhamentoRecurso acompanhamentoRecursoAlterada) throws UserWithoutPermission, ChangeNotMade, DBUnavailable {
        //Checar se o professor realmente d� aula nessa turma.
        if(checarPermissao(acompanhamentoRecursoAlterada.getIdProfessor(), acompanhamentoRecursoAlterada.getIdTurma())) {
            presencaBD.alterarPresenca(acompanhamentoRecursoAlterada);
        } else {
            throw new UserWithoutPermission("Este professor n�o possui permiss�o para alterar essa presen�a.");
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
