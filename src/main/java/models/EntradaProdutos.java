package models;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.FornecedorDAO;
import dao.InstanciaProdutoMovimentacaoDAO;
import java.util.Date;

public class EntradaProdutos extends Movimentacao {

    private int idFornecedor;
    private int idEstoqueDestino;

    public EntradaProdutos(int idFornecedor, int idEstoqueDestino, int tipoMovimentacao, int idMovimentacao, Date data) {
        super(idMovimentacao, data, tipoMovimentacao);
        this.idFornecedor = idFornecedor;
        this.idEstoqueDestino = idEstoqueDestino;
    }

    public EntradaProdutos(int idFornecedor, int idEstoqueDestino, int tipoMovimentacao, Date data) {
        super(data, tipoMovimentacao);
        this.idFornecedor = idFornecedor;
        this.idEstoqueDestino = idEstoqueDestino;
    }

    @Override
    public String toString() {
        return (
                "tipoMovimentacao: " + this.getTipoMovimentacao() + "\n" +
                "idFornecedor: " + this.getIdFornecedor() + "\n" +
                "idEstoqueDestino: " + this.getIdEstoqueDestino()
        );
    }

    public Object[] getCompraFornecedoresTableRow() {
        String produtosMovimentacao = "";
        for (InstanciaProdutoMovimentacao instancia : this.getInstanciasProduto()) {
            produtosMovimentacao += instancia.getProduto().getDescricao() + ", ";
        }

        produtosMovimentacao = produtosMovimentacao.substring(0, produtosMovimentacao.length() - 2);

        return new Object[]{
            this.getFornecedor().getDescricao(),
            AuxFunctions.formatData(this.getData()),
            this.getTipoMovimentacao() == 4 ? AuxFunctions.valorFloatParaString(this.getValorTotal()) : null,
            this.getEstoqueDestino().getDescricao(),
            produtosMovimentacao
        };
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
            this.getTipoMovimentacao() == 4 ? "ENTRADA DE PRODUTOS - COMPRA" : "ENTRADA DE PRODUTOS",
            this.getTipoMovimentacao() == 4 ? this.getValorTotal() : null,
            null,
            this.getEstoqueDestino().getDescricao(),
            produtosMovimentacao
        };
    }

    public Estoque getEstoqueDestino() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoqueDestino);
    }

    public InstanciaProdutoMovimentacao[] getInstanciasProduto() {
        return InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(this.getIdMovimentacao(), this.getTipoMovimentacao());
    }

    public Fornecedor getFornecedor() {
        return FornecedorDAO.selectFornecedorPorId(this.idFornecedor);
    }

    public float getValorTotal() {
        float valorTotal = 0;

        for (InstanciaProdutoMovimentacao instancia : this.getInstanciasProduto()) {
            valorTotal += instancia.getValorUnitarioPago();
        }

        return valorTotal;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdEstoqueDestino() {
        return idEstoqueDestino;
    }

    public void setIdEstoqueDestino(int idEstoqueDestino) {
        this.idEstoqueDestino = idEstoqueDestino;
    }

    public boolean isCompra() {
        return this.getTipoMovimentacao() == 4;
    }

}
