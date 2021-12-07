package service.professor;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.professor.ProfessorDAO;
import dao.professor.ProfessorDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import service.Presenca;
import service.Turma;
import java.util.Map;

public class ProfessorService implements ProfessorServiceInterface{
    private final TurmaDAO turmaBD;
    private final ProfessorDAO professorBD;
    private final PresencaDAO presencaBD;

    public ProfessorService() {
        this.turmaBD = new TurmaDAOMySQL();
        this.professorBD = new ProfessorDAOMySQL();
        this.presencaBD = new PresencaDAOMySQL();
    }

    @Override
    public Professor consultarProfessor(int idProfessor) {
        return professorBD.consultarProfessor(idProfessor);
    }

    @Override
    public Turma consultarTurma(int idTurma) {
        return turmaBD.consultarTurma(idTurma);
    }

    @Override
    public void inserirPresenca(Presenca novaPresenca) {
        //Checar se o professor realmente dá aula nessa turma.
        Turma checarTurma = consultarTurma(novaPresenca.getIdTurma());

        if(checarTurma.containsProfessor(novaPresenca.getIdProfessor())) {
            presencaBD.inserirPresenca(novaPresenca);
        }
    }

    @Override
    public Map<String, Presenca> consultarPresenca(int idTurma, int idProfessor) {
        return presencaBD.consultarPresenca(idTurma, idProfessor);
    }

    @Override
    public void removerPresenca(int idPresenca, int idProfessor) {
        //Checar se o professor realmente dá aula nessa turma.
        Turma checarTurma = consultarTurma(idPresenca);

        if(checarTurma.containsProfessor(idProfessor)) {
            presencaBD.removerPresenca(idPresenca, idProfessor);
        }
    }

    @Override
    public void alterarPresenca(Presenca presencaAlterada) {
        //Checar se o professor realmente dá aula nessa turma.
        Turma checarTurma = consultarTurma(presencaAlterada.getIdTurma());

        if(checarTurma.containsProfessor(presencaAlterada.getIdProfessor())) {
            presencaBD.alterarPresenca(presencaAlterada);
        } //TODO Checar para o admin) {

    }
}
