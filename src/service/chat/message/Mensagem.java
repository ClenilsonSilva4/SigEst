package service.chat.message;

public class Mensagem {
    private final int idDestinatario;
    private final int idRemetente;
    private final String texto;
    private final String dataMessage;
    private final String emailRemetente;
    private int idMensagem;
    private String emailDestinatario;
    private boolean pertenceGrupo;

    public Mensagem(int idRemetente, String texto, String dateMessage, String email, int idDestinatario) {
        this.idRemetente = idRemetente;
        this.texto = texto;
        this.dataMessage = dateMessage;
        this.emailRemetente = email;
        this.idDestinatario = idDestinatario;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public boolean isPertenceGrupo() {
        return pertenceGrupo;
    }

    public void setPertenceGrupo(boolean pertenceGrupo) {
        this.pertenceGrupo = pertenceGrupo;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public int getIdRemetente() {
        return idRemetente;
    }

    public String getTexto() {
        return texto;
    }

    public String getDataMessage() {
        return dataMessage;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }
}