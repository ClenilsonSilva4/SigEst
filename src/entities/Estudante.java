package entities;

public class Estudante extends Usuario{
    public Estudante(int idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario) {
        super(idUsuario, nomeUsuario, emailUsuario, senhaUsuario);
    }

    public Estudante(Usuario novoEstudante) {
        super(novoEstudante.getIdUsuario(), novoEstudante.getNomeUsuario(), novoEstudante.getEmailUsuario(),
                novoEstudante.getSenhaUsuario());
    }
}
