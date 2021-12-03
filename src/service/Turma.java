package service;

import java.util.List;

public class Turma {
    private final int idTurma;
    private String nomeDisciplina;
    private List<Professor> listaProfessores;
    private List<Estudante> listaPresenca;

    public Turma(int idTurma, String nomeDisciplina, List<Professor> listaProfessores, List<Estudante> listaPresenca) {
        this.idTurma = idTurma;
        this.nomeDisciplina = nomeDisciplina;
        this.listaProfessores = listaProfessores;
        this.listaPresenca = listaPresenca;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public void setListaProfessores(List<Professor> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

    public List<Estudante> getListaPresenca() {
        return listaPresenca;
    }

    public void setListaPresenca(List<Estudante> listaPresenca) {
        this.listaPresenca = listaPresenca;
    }
}
