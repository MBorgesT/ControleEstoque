package models;

import dao.ProdutoDAO;

public class InstanciaProduto {

    private int idInstanciaProduto;
    private int idProduto;
    private int quantidade;

    public InstanciaProduto(int idInstanciaProduto, int idProduto, int quantidade) {
        this.idInstanciaProduto = idInstanciaProduto;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public InstanciaProduto(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return ProdutoDAO.selectProdutoPorId(this.idProduto);
    }

    public int getIdInstanciaProduto() {
        return idInstanciaProduto;
    }

    public void setIdInstanciaProduto(int idInstanciaProduto) {
        this.idInstanciaProduto = idInstanciaProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
