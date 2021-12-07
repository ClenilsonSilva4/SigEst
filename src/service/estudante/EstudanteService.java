package service.estudante;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import dao.estudante.EstudanteDAO;
import dao.estudante.EstudanteDAOMySQL;
import exception.UserNotFoundException;
import service.Presenca;
import service.Turma;

import java.util.Map;

public class EstudanteService implements EstudanteServiceInterface{
    public Estudante consultarEstudante(int idEstudante) {
        EstudanteDAO consultaBD = new EstudanteDAOMySQL();
        try {
            return consultaBD.consultarEstudante(idEstudante);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Turma consultarTurma(int idTurma) {
        TurmaDAO consultaTurma = new TurmaDAOMySQL();
        return consultaTurma.consultarTurma(idTurma);
    }

    public Map<String, Presenca> consultarPresenca(int idTurma, int idEstudante) {
        PresencaDAO consultarPresenca = new PresencaDAOMySQL();
        return consultarPresenca.consultarPresenca(idTurma, idEstudante);
    }
}
