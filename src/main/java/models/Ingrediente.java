package models;

import aux_functions.AuxFunctions;
import dao.ProdutoDAO;

public class Ingrediente {

    private int idIngrediente;
    private int idProduto;
    private int idProdutoFazParte;
    private float quantidadeRelativa;

    public Ingrediente(int idIngrediente, int idProduto, int idProdutoFazParte, float quantidadeRelativa) {
        this.idIngrediente = idIngrediente;
        this.idProduto = idProduto;
        this.idProdutoFazParte = idProdutoFazParte;
        this.quantidadeRelativa = quantidadeRelativa;
    }

    public Ingrediente(int idProduto, float quantidadeRelativa) {
        this.idProduto = idProduto;
        this.quantidadeRelativa = quantidadeRelativa;
    }

    public Ingrediente(int idProduto) {
        this.idProduto = idProduto;
    }

    public Produto getProduto() {
        return ProdutoDAO.selectProdutoPorId(this.idProduto);
    }
    
    public Produto getProdutoFazParte() {
        return ProdutoDAO.selectProdutoPorId(this.idProdutoFazParte);
    }
    
    public Object[] getIngredientesTableRow() {
        Produto produto = this.getProduto();
        return new Object[]{
            produto.getDescricao(),
            produto.getUnidadeDeMedida(),
            AuxFunctions.valorFloatParaString(this.quantidadeRelativa)
        };
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdProdutoFazParte() {
        return idProdutoFazParte;
    }

    public void setIdProdutoFazParte(int idProdutoFazParte) {
        this.idProdutoFazParte = idProdutoFazParte;
    }

    public float getQuantidadeRelativa() {
        return quantidadeRelativa;
    }

    public void setQuantidadeRelativa(float quantidadeRelativa) {
        this.quantidadeRelativa = quantidadeRelativa;
    }

    
    
}
