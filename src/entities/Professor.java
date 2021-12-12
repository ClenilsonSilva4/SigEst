package entities;

public class Professor extends Usuario {
    public Professor(int idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario) {
        super(idUsuario, nomeUsuario, emailUsuario, senhaUsuario);
    }

    public Professor(Usuario novoProfessor) {
        super(novoProfessor.getIdUsuario(), novoProfessor.getNomeUsuario(), novoProfessor.getEmailUsuario(),
                novoProfessor.getSenhaUsuario());
    }
}
