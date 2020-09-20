package models;

public class InstanciaProdutoMovimentacao extends InstanciaProduto {

    private int idMovimentacao;
    private float valorUnitarioPago;

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

}
