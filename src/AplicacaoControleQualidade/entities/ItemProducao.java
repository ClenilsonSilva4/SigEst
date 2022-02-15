package AplicacaoControleQualidade.entities;

import framework.domain.Recurso;

import java.util.Date;

public class ItemProducao extends Recurso {
    private Date dataFabricacao;
    private Boolean itemDanificado;

    public ItemProducao(long id, String nome, Date dataFabricacao, Boolean itemDanificado) {
        super(id, nome);
        this.dataFabricacao = dataFabricacao;
        this.itemDanificado = itemDanificado;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public boolean getItemDanificado(){ return itemDanificado; }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public void setItemDanificado(boolean itemDanificado){this.itemDanificado = itemDanificado; }

    @Override
    public boolean validarRecurso() {
        return false;
    }
}
