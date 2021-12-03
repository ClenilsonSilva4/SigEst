package service;

import dao.TurmaDAOMySQL;
import dao.estudante.EstudanteDAO;
import dao.estudante.EstudanteDAOMySQL;

public class EstudanteService {
    Estudante consultarEstudante(int idEstudante) {
        EstudanteDAO consultaBD = new EstudanteDAOMySQL();
        return consultaBD.consultarEstudante(idEstudante);
    }

    Turma consultarTurma(int idTurma) {
        TurmaDAOMySQL consultaTurma = new TurmaDAOMySQL();
        return consultaTurma.consultarTurma(idTurma);
    }

    Presença consultarPresenca(int idTurma, int idEstudante) {
        //TODO
        return null;
    }


}
