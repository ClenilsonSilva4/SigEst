package entities;

public class AcompanhamentoRecurso {
    private long idAcompanhamento;
    private String dataAcompanhamento;
    private int idConjuntoRecurso;
    private int idRecurso;
    private int idAvaliador;
    private boolean acompanhamento;

    public AcompanhamentoRecurso(String dataAcompanhamento, int idConjuntoRecurso, int idRecurso, int idAvaliador, boolean acompanhamento) {
        this.dataAcompanhamento = dataAcompanhamento;
        this.idConjuntoRecurso = idConjuntoRecurso;
        this.idRecurso = idRecurso;
        this.idAvaliador = idAvaliador;
        this.acompanhamento = acompanhamento;
    }

    public void realizarAcompanhamento() {
        acompanhamento = !acompanhamento;
    }

    public long getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public String getDataAcompanhamento() {
        return dataAcompanhamento;
    }

    public int getIdConjuntoRecurso() {
        return idConjuntoRecurso;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public int getIdAvaliador() {
        return idAvaliador;
    }

    public boolean isAcompanhamento() {
        return acompanhamento;
    }
}
