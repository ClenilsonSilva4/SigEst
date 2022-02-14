package framework.domain;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoRecurso {
	private final long idConjunto;
	private final String nomeConjunto;
	private int capacidadeMaxima;
	private List<Long> avaliadoresConjunto;
	private List<Long> recursosConjunto;
	private List<Long> acompanhamentoRecursos;

	public ConjuntoRecurso(long idConjunto, String nomeConjunto, int capacidadeMaxima) {
		this.idConjunto = idConjunto;
		this.nomeConjunto = nomeConjunto;
		this.capacidadeMaxima = capacidadeMaxima;
		this.avaliadoresConjunto = new ArrayList<>();
		this.recursosConjunto = new ArrayList<>();
		this.acompanhamentoRecursos = new ArrayList<>();
	}

	public void setCapacidadeMaxima(int capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public void setAcompanhamentoRecursos(List<Long> acompanhamentoRecursos) {
		this.acompanhamentoRecursos = acompanhamentoRecursos;
	}

	public long getIdConjunto() {
		return idConjunto;
	}

	public String getNomeConjunto() {
		return nomeConjunto;
	}

	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public List<Long> getAvaliadoresConjunto() {
		return avaliadoresConjunto;
	}

	public List<Long> getRecursosConjunto() {
		return recursosConjunto;
	}

	public List<Long> getAcompanhamentoRecursos() {
		return acompanhamentoRecursos;
	}

	public void setAvaliadoresConjunto(List<Long> avaliadoresConjunto) {
		this.avaliadoresConjunto = avaliadoresConjunto;
	}

	public void setRecursosConjunto(List<Long> recursosConjunto) {
		this.recursosConjunto = recursosConjunto;
	}
}
