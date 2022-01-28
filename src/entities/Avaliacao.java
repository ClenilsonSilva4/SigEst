package entities;

public class Avaliacao {
    private long idAvaliacao;
    private Recurso recurso;
    private Avaliador avaliador;
    private String dataAvaliacao;
    private String resultado;

    public Avaliacao(Recurso recurso, Avaliador avaliador, String dataAvaliacao, String resultado) {
        this.recurso = recurso;
        this.avaliador = avaliador;
        this.dataAvaliacao = dataAvaliacao;
        this.resultado = resultado;
    }

    public long getIdAvaliacao() {
        return idAvaliacao;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Avaliador getAvaliador() {
        return avaliador;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public String getResultado() {
        return resultado;
    }
}
