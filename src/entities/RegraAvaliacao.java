package entities;

import java.util.List;

public interface RegraAvaliacao {
    void definirAprovacao(long idRecurso);
    List<AcompanhamentoRecurso> obterAcompanhamentosRecurso();
}
