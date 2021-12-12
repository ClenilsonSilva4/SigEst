package entities;

public class Gestor extends Usuario{
    public Gestor(int idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario) {
        super(idUsuario, nomeUsuario, emailUsuario, senhaUsuario);
    }

    public Gestor(Usuario novoGestor) {
        super(novoGestor.getIdUsuario(), novoGestor.getNomeUsuario(), novoGestor.getEmailUsuario(),
                novoGestor.getSenhaUsuario());
    }
}
