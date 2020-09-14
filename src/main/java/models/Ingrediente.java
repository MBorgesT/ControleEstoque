package models;

public class Ingrediente {

    private int idIngrediente;
    private int idProduto;
    private int idProdutoFazParte;
    private float quantidadeRelativa;

    public Ingrediente(int idProduto, float quantidadeRelativa) {
        this.idProduto = idProduto;
        this.quantidadeRelativa = quantidadeRelativa;
    }

    public Produto getProduto() {
        return null;
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
