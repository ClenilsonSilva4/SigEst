package DAO;

import Domain.Avaliação;
import java.util.List;

public interface AvaliaçãoDAOMySQL {

	public void salvarAvaliação(Avaliação avaliação);

	public void excluirAvaliação(Avaliação avaliaçãoRemovida);

	public void alterarAvaliação(Avaliação avaliaçãoAlterada);

	public List listarAvaliações();

	public Avaliação obterAvaliação(long avaliaçãoID);

}
