package framework.domain;

public class AcompanhamentoRecurso {
	private final long id;
	private final long idRecurso;
	private final long idAvaliador;
	private final String dataAcompanhamento;
	private boolean acompanhamento;

	public AcompanhamentoRecurso(long id, long idRecurso, long idAvaliador, String dataAcompanhamento, boolean acompanhamento) {
		this.id = id;
		this.idRecurso = idRecurso;
		this.idAvaliador = idAvaliador;
		this.dataAcompanhamento = dataAcompanhamento;
		this.acompanhamento = acompanhamento;
	}

	public long getId() {
		return id;
	}

	public void setAcompanhamento(boolean acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public long getIdRecurso() {
		return idRecurso;
	}

	public long getIdAvaliador() {
		return idAvaliador;
	}

	public String getDataAcompanhamento() {
		return dataAcompanhamento;
	}

	public boolean isAcompanhamento() {
		return acompanhamento;
	}
}
