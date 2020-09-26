package models;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.InstanciaProdutoMovimentacaoDAO;

public class MovimentacaoEntreEstoques extends Movimentacao {

    private int idEstoqueOrigem;
    private int idEstoqueDestino;

    public MovimentacaoEntreEstoques(int idEstoqueOrigem, int idEstoqueDestino, int idMovimentacao, java.util.Date data, int tipoMovimentacao) {
        super(idMovimentacao, data, tipoMovimentacao);
        this.idEstoqueOrigem = idEstoqueOrigem;
        this.idEstoqueDestino = idEstoqueDestino;
    }

    public MovimentacaoEntreEstoques(int idEstoqueOrigem, int idEstoqueDestino, java.util.Date data, int tipoMovimentacao) {
        super(data, tipoMovimentacao);
        this.idEstoqueOrigem = idEstoqueOrigem;
        this.idEstoqueDestino = idEstoqueDestino;
    }

    @Override
    public Object[] getMovimentacaoTableRow() {
        String produtosMovimentacao = "";
        for (InstanciaProdutoMovimentacao instancia : this.getInstanciasProduto()) {
            produtosMovimentacao += instancia.getProduto().getDescricao() + ", ";
        }

        produtosMovimentacao = produtosMovimentacao.substring(0, produtosMovimentacao.length() - 2);

        return new Object[]{
            AuxFunctions.formatData(this.getData()),
            "ENTRE ESTOQUES",
            null,
            this.getEstoqueOrigem().getDescricao(),
            this.getEstoqueDestino().getDescricao(),
            produtosMovimentacao
        };
    }

    private Estoque getEstoqueOrigem() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoqueOrigem);
    }

    private Estoque getEstoqueDestino() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoqueDestino);
    }

    public InstanciaProdutoMovimentacao[] getInstanciasProduto() {
        return InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(this.getIdMovimentacao(), this.getTipoMovimentacao());
    }

    public int getIdEstoqueOrigem() {
        return idEstoqueOrigem;
    }

    public void setIdEstoqueOrigem(int idEstoqueOrigem) {
        this.idEstoqueOrigem = idEstoqueOrigem;
    }

    public int getIdEstoqueDestino() {
        return idEstoqueDestino;
    }

    public void setIdEstoqueDestino(int idEstoqueDestino) {
        this.idEstoqueDestino = idEstoqueDestino;
    }
    
    

}
