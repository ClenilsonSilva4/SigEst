package service.estudante;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import dao.usuario.estudante.EstudanteDAO;
import dao.usuario.estudante.EstudanteDAOMySQL;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Estudante;
import entities.Presenca;
import entities.Turma;

public class EstudanteService implements EstudanteServiceInterface{
    public Estudante consultarEstudante(int idEstudante) throws DBUnavailable {
        EstudanteDAO consultaBD = new EstudanteDAOMySQL();
        try {
            return consultaBD.consultarEstudante(idEstudante);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        TurmaDAO consultaTurma = new TurmaDAOMySQL();
        return consultaTurma.consultarTurma(idTurma);
    }

    public Presenca consultarPresenca(int idPresenca) {
        PresencaDAO consultarPresenca = new PresencaDAOMySQL();
        return consultarPresenca.consultarPresenca(idPresenca);
    }
}
