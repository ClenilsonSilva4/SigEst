package AplicacaoControleQualidade.domain;

import AplicacaoControleQualidade.entities.ItemProducao;
import framework.domain.Recurso;
import framework.domain.RegraRecurso;

import java.util.Date;

public class RegraAdicaoItemProducao implements RegraRecurso {
    private final Date dataAtual = new Date();
    private final boolean itemDanificado = true;
    @Override
    public boolean verificarRecurso(Recurso checarRecurso) {
        ItemProducao checarProduto = (ItemProducao) checarRecurso;

        return checarProduto.getDataFabricacao().before(dataAtual) && checarProduto.getItemDanificado() != itemDanificado && checarProduto.getNome().length() > 2 && checarProduto.getProduto().length() > 5;
    }
}
