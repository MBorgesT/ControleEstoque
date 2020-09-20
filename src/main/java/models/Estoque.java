package models;

public class Estoque {

    private int idEstoque;
    private String descricao;

    public Estoque(int idEstoque, String descricao) {
        this.idEstoque = idEstoque;
        this.descricao = descricao;
    }

    public Estoque(String descricao) {
        this.descricao = descricao;
    }

    public float calcularValorEstoque() {
        return 0;
    }

    public InstanciaProduto[] getInstanciasProdutoEstoque() {
        return null;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

}
