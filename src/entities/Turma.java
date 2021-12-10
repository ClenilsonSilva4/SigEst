package entities;

import java.util.List;

public class Turma {
    private final int idTurma;
    private String nomeDisciplina;
    private int capacidadeAlunos;
    private List<Integer> idProfessores;
    private List<Integer> idEstudantes;

    public Turma(int idTurma, String nomeDisciplina, int capacidadeAlunos) {
        this.idTurma = idTurma;
        this.nomeDisciplina = nomeDisciplina;
        this.capacidadeAlunos = capacidadeAlunos;
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

    public boolean containsProfessor(int idProfessor) {
        return idProfessores.contains(idProfessor);
    }

    public List<Integer> getIdProfessores() {
        return idProfessores;
    }

    public void setIdProfessores(List<Integer> idProfessores) {
        this.idProfessores = idProfessores;
    }

    public List<Integer> getIdEstudantes() {
        return idEstudantes;
    }

    public void setIdEstudantes(List<Integer> idEstudantes) {
        this.idEstudantes = idEstudantes;
    }

    public int getCapacidadeAlunos() {
        return capacidadeAlunos;
    }

    public void setCapacidadeAlunos(int capacidadeAlunos) {
        this.capacidadeAlunos = capacidadeAlunos;
    }
}
