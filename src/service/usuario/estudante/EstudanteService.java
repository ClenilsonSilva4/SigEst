package service.usuario.estudante;

import dao.presenca.PresencaDAO;
import dao.presenca.PresencaDAOMySQL;
import dao.turma.TurmaDAO;
import dao.turma.TurmaDAOMySQL;
import dao.usuario.UsuarioDAO;
import dao.usuario.EstudanteDAOMySQL;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Recurso;
import entities.AcompanhamentoRecurso;
import entities.ConjuntoRecurso;

public class EstudanteService implements EstudanteServiceInterface{
    private final UsuarioDAO estudanteBD;
    private final TurmaDAO turmaBD;
    private final PresencaDAO presencaBD;

    public EstudanteService() {
        this.estudanteBD = new EstudanteDAOMySQL();
        this.turmaBD = new TurmaDAOMySQL();
        this.presencaBD = new PresencaDAOMySQL();
    }

    public Recurso consultarEstudante(int idEstudante) throws DBUnavailable {
        try {
            return new Recurso(estudanteBD.consultarUsuario(idEstudante));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ConjuntoRecurso consultarTurma(int idTurma) throws UserNotFoundException, DBUnavailable {
        return turmaBD.consultarTurma(idTurma);
    }

    public AcompanhamentoRecurso consultarPresenca(int idPresenca) {
        return presencaBD.consultarPresenca(idPresenca);
    }
}
