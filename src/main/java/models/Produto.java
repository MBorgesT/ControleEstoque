package models;

import aux_functions.AuxFunctions;

public class Produto {

    private int idProduto;
    private String descricao;
    private float valorUnitarioPago;
    private float valorUnitarioVenda;
    private String unidadeDeMedida;
    private float quantidadeNaEmbalagem;
    private boolean produzidoNaPadaria;

    public Produto(int idProduto, String descricao, float valorUnitarioPago, float valorUnitarioVenda, String unidadeDeMedida, float quantidadeNaEmbalagem, boolean produzidoNaPadaria) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.valorUnitarioPago = valorUnitarioPago;
        this.valorUnitarioVenda = valorUnitarioVenda;
        this.unidadeDeMedida = unidadeDeMedida;
        this.quantidadeNaEmbalagem = quantidadeNaEmbalagem;
        this.produzidoNaPadaria = produzidoNaPadaria;
    }

    public Produto(String descricao, String unidadeDeMedida, float quantidadeNaEmbalagem, boolean produzidoNaPadaria) {
        this.descricao = descricao;
        this.unidadeDeMedida = unidadeDeMedida;
        this.quantidadeNaEmbalagem = quantidadeNaEmbalagem;
        this.produzidoNaPadaria = produzidoNaPadaria;
    }

    public Ingrediente[] getIngredientes() {
        return null;
    }
    
    public Object[] getMenuPrincipalTableRow() {
        return new Object[]{
            this.idProduto,
            this.descricao,
            AuxFunctions.valorFloatParaString(this.valorUnitarioPago),
            AuxFunctions.valorFloatParaString(this.valorUnitarioVenda),
            this.unidadeDeMedida,
            this.quantidadeNaEmbalagem,
            this.produzidoNaPadaria ? "SIM" : "NÃO"
        };
    }
    
    public Object[] getCadastroProdutosTableRow() {
        return new Object[]{
            this.descricao,
            this.unidadeDeMedida
        };
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public float getQuantidadeNaEmbalagem() {
        return quantidadeNaEmbalagem;
    }

    public void setQuantidadeNaEmbalagem(float quantidadeNaEmbalagem) {
        this.quantidadeNaEmbalagem = quantidadeNaEmbalagem;
    }

    public boolean isProduzidoNaPadaria() {
        return produzidoNaPadaria;
    }

    public void setProduzidoNaPadaria(boolean produzidoNaPadaria) {
        this.produzidoNaPadaria = produzidoNaPadaria;
    }

}
