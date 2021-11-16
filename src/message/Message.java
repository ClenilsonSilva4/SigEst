package message;

public class Message {
    private final String idDestinatario;
    private final String idRemetente;
    private final String texto;
    private final String dataMessage;
    private final String emailRemetente;
    private String idMensagem;
    private String emailDestinatario;
    private boolean pertenceGrupo;

    public Message(String idRemetente, String texto, String dateMessage, String email, String idDestinatario) {
        this.idRemetente = idRemetente;
        this.texto = texto;
        this.dataMessage = dateMessage;
        this.emailRemetente = email;
        this.idDestinatario = idDestinatario;
    }

    public String getIdDestinatario() {
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

    public String getIdMensagem() {
        return idMensagem;
    }

    public String getIdRemetente() {
        return idRemetente;
    }

    public String getTexto() {
        return texto;
    }

    public String getDataMessage() {
        return dataMessage;
    }

    public void setIdMensagem(String idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }
}