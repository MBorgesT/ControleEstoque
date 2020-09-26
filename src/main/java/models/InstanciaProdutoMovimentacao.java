package models;

import aux_functions.AuxFunctions;

public class InstanciaProdutoMovimentacao extends InstanciaProduto {

    private int idMovimentacao;
    private float valorUnitarioPago, valorUnitarioVenda;

    public InstanciaProdutoMovimentacao(int idInstanciaProduto, int idProduto, int quantidade) {
        super(idInstanciaProduto, idProduto, quantidade);
    }

    public InstanciaProdutoMovimentacao(int idProduto, int quantidade) {
        super(idProduto, quantidade);
    }

    public InstanciaProdutoMovimentacao(float valorUnitarioPago, int idInstanciaProduto, int idProduto, int quantidade) {
        super(idInstanciaProduto, idProduto, quantidade);
        this.valorUnitarioPago = valorUnitarioPago;
    }

    public InstanciaProdutoMovimentacao(float valorUnitarioPago, int idProduto, int quantidade) {
        super(idProduto, quantidade);
        this.valorUnitarioPago = valorUnitarioPago;
    }
    
    public Object[] getEntreEstoquesTableRow() {
        Produto produto = this.getProduto();
        return new Object[]{
            produto.getDescricao(),
            produto.getQuantidadeNaEmbalagem(),
            produto.getUnidadeDeMedida(),
            this.getQuantidade(),
            AuxFunctions.valorFloatParaString(this.getProduto().getValorUnitarioVenda())
        };
    }

    public Movimentacao getMovimentacao() {
        return null;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public float getValorUnitarioPago() {
        return valorUnitarioPago;
    }

    public void setValorUnitarioPago(float valorUnitarioPago) {
        this.valorUnitarioPago = valorUnitarioPago;
    }

    public float getValorUnitarioVenda() {
        return valorUnitarioVenda;
    }

    public void setValorUnitarioVenda(float valorUnitarioVenda) {
        this.valorUnitarioVenda = valorUnitarioVenda;
    }

}
