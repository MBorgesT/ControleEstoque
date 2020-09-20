package models;

import java.util.Date;

public abstract class Movimentacao {

    private int idMovimentacao;
    private Date data;
    private int tipoMovimentacao;

    public Movimentacao(int idMovimentacao, Date data, int tipoMovimentacao) {
        this.idMovimentacao = idMovimentacao;
        this.data = data;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Movimentacao(Date data, int tipoMovimentacao) {
        this.data = data;
    }
    
    public Object[] getMovimentacaoTableRow() {
        return null;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(int tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
