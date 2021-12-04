package dao.professor;

import service.professor.Professor;

public interface ProfessorDAO {
    void inserirProfessor(Professor novoProfessor);
    Professor consultarProfessor(int idProfessor);
    void removerProfessor(int idProfessor);
    void alterarProfessor(Professor professorAlterado);
}
