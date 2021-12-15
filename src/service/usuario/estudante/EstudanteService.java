package service.usuario.estudante;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import dao.usuario.UsuarioDAO;
import dao.usuario.EstudanteDAOMySQL;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Estudante;
import entities.Presenca;
import entities.Turma;

public class EstudanteService implements EstudanteServiceInterface{
    private final UsuarioDAO estudanteBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public EstudanteService() {
        this.estudanteBD = new EstudanteDAOMySQL();
        this.turmaBD = new TurmaDAOMySQL();
        this.presencaBD = new PresencaDAOMySQL();
    }

    public Estudante consultarEstudante(int idEstudante) throws DBUnavailable {
        try {
            return new Estudante(estudanteBD.consultarUsuario(idEstudante));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Turma consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    public Presenca consultarPresenca(int idPresenca) {
        return presencaBD.consultarPresenca(idPresenca);
    }
}
