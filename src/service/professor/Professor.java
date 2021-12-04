package service.professor;

public class Professor {
    private final int idProfessor;
    private String nomeProfessor;

    public Professor(int idProfessor, String nomeProfessor) {
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }
}
