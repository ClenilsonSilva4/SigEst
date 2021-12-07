package service.professor;

public class Professor {
    private final int idProfessor;
    private String nomeProfessor;
    private String email;
    private String senha;

    public Professor(int idProfessor, String nomeProfessor, String email, String senha) {
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
        this.email = email;
        this.senha = senha;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
