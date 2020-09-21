package models;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.InstanciaProdutoMovimentacaoDAO;
import java.sql.Date;

public class SaidaProdutos extends Movimentacao {

    private int idEstoqueOrigem;

    public SaidaProdutos(int idEstoqueOrigem, int idMovimentacao, Date data, int tipoMovimentacao) {
        super(idMovimentacao, data, tipoMovimentacao);
        this.idEstoqueOrigem = idEstoqueOrigem;
    }

    public SaidaProdutos(int idEstoqueOrigem, Date data, int tipoMovimentacao) {
        super(data, tipoMovimentacao);
        this.idEstoqueOrigem = idEstoqueOrigem;
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
            this.getTipoMovimentacao() == 1 ? "SAÍDA DE PRODUTOS - VENDA" : "SAÍDA DE PRODUTOS",
            this.getTipoMovimentacao() == 1 ? this.getValorTotal() : null,
            this.getEstoqueOrigem().getDescricao(),
            null,
            produtosMovimentacao
        };
    }
    
    public Estoque getEstoqueOrigem() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoqueOrigem);
    }
    
    public InstanciaProdutoMovimentacao[] getInstanciasProduto() {
        return InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(this.getIdMovimentacao(), this.getTipoMovimentacao());
    }
    
    public float getValorTotal() {
        float valorTotal = 0;
        
        for (InstanciaProdutoMovimentacao instancia : this.getInstanciasProduto()){
            valorTotal += instancia.getValorUnitarioPago() * instancia.getQuantidade();
        }
        
        return valorTotal;
    }

}
