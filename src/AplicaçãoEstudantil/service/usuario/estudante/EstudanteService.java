package AplicaçãoEstudantil.service.usuario.estudante;

import AplicaçãoEstudantil.dao.presenca.PresencaDAO;
import AplicaçãoEstudantil.dao.presenca.PresencaDAOMySQL;
import AplicaçãoEstudantil.dao.turma.TurmaDAO;
import AplicaçãoEstudantil.dao.turma.TurmaDAOMySQL;
import framework.exception.DBUnavailable;
import framework.exception.UserNotFoundException;
import framework.entities.Recurso;
import framework.entities.AcompanhamentoRecurso;
import framework.entities.ConjuntoRecurso;

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
