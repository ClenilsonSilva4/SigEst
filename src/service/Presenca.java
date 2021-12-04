package service;

public class Presenca {
    private int id;
    private String data;
    private int idTurma;
    private int idAluno;
    private int idProfessor;
    private boolean presente;

    public Presenca(int id, String data, int idTurma, int idAluno, int idProfessor, boolean presente) {
        this.id = id;
        this.data = data;
        this.idTurma = idTurma;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.presente = presente;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public boolean isPresente() {
        return presente;
    }
}
