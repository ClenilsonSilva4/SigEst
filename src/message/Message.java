package message;

public class Message {
    private String idMensagem;
    private final String idRemetente;
    private final String texto;
    private final String dateMessage;

    private final String email;

    public Message(String idRemetente, String texto, String dateMessage, String email) {
        this.idRemetente = idRemetente;
        this.texto = texto;
        this.dateMessage = dateMessage;
        this.email = email;
    }

    public String getEmail() {
        return email;
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

    public String getDateMessage() {
        return dateMessage;
    }
}
