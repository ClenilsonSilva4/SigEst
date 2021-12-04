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
    @Override
    public Professor consultarProfessor(int idProfessor) {
        ProfessorDAO consultaProfessor = new ProfessorDAOMySQL();
        return consultaProfessor.consultarProfessor(idProfessor);
    }

    @Override
    public Turma consultarTurma(int idTurma) {
        TurmaDAO consultaTurma = new TurmaDAOMySQL();
        return consultaTurma.consultarTurma(idTurma);
    }

    @Override
    public String inserirPresenca(Presenca novaPresenca) {
        //Checar se o professor realmente dá aula nessa turma.
        Turma checarTurma = consultarTurma(novaPresenca.getIdTurma());

        if(checarTurma.containsProfessor(novaPresenca.getIdProfessor())) {
            PresencaDAO adicionarPresenca = new PresencaDAOMySQL();
            adicionarPresenca.inserirPresenca(novaPresenca);

            return "Presença inserida com sucesso";
        } /*else if(//TODO Checar para o admin) {

        }*/ else {
            return "Este usuário não possui permissão para inserir essa presença.";
        }
    }

    @Override
    public Map<String, Presenca> consultarPresenca(int idTurma, int idProfessor) {
        PresencaDAO consultarPresenca = new PresencaDAOMySQL();
        return consultarPresenca.consultarPresenca(idTurma, idProfessor);
    }

    @Override
    public String removerPresenca(int idPresenca, int idProfessor) {
        //Checar se o professor realmente dá aula nessa turma.
        Turma checarTurma = consultarTurma(idPresenca);

        if(checarTurma.containsProfessor(idProfessor)) {
            PresencaDAO removerPresenca = new PresencaDAOMySQL();
            removerPresenca.removerPresenca(idPresenca, idProfessor);

            return "Presença removida com sucesso";
        } /*else if(//TODO Checar para o admin) {

        }*/ else {
            return "Este usuário não possui permissão para remover essa presença.";
        }
    }

    @Override
    public String alterarPresenca(Presenca presencaAlterada) {
        //Checar se o professor realmente dá aula nessa turma.
        Turma checarTurma = consultarTurma(presencaAlterada.getIdTurma());

        if(checarTurma.containsProfessor(presencaAlterada.getIdProfessor())) {
            PresencaDAO alterarPresenca = new PresencaDAOMySQL();
            alterarPresenca.alterarPresenca(presencaAlterada);

            return "Presença alterada com sucesso";
        } /*else if(//TODO Checar para o admin) {

        }*/ else {
            return "Este usuário não possui permissão para alterar essa presença.";
        }
    }
}
