package message;

public class Message {
    private final String idMensagem;
    private final String idRemetente;
    private final String texto;
    private final String dateMessage;

    public Message(String idMensagem, String idRemetente, String texto, String dateMessage) {
        this.idMensagem = idMensagem;
        this.idRemetente = idRemetente;
        this.texto = texto;
        this.dateMessage = dateMessage;
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
