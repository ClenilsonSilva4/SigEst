package service;

import service.estudante.Estudante;
import service.professor.Professor;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private final int idTurma;
    private String nomeDisciplina;
    private List<Integer> idProfessores;
    private List<Integer> idEstudantes;

    public Turma(int idTurma, String nomeDisciplina, ArrayList<Integer> idProfessores, ArrayList<Integer> idEstudantes) {
        this.idTurma = idTurma;
        this.nomeDisciplina = nomeDisciplina;
        this.idProfessores = idProfessores;
        this.idEstudantes = idEstudantes;
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
}
