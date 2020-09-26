package models;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.ProdutoDAO;

public class InstanciaProdutoEstoque extends InstanciaProduto {

    private int idEstoque;

    public InstanciaProdutoEstoque(int idEstoque, int idInstanciaProduto, int idProduto, int quantidade) {
        super(idInstanciaProduto, idProduto, quantidade);
        this.idEstoque = idEstoque;
    }

    public InstanciaProdutoEstoque(int idEstoque, int idProduto, int quantidade) {
        super(idProduto, quantidade);
        this.idEstoque = idEstoque;
    }
    
    public Object[] getMovimentacaoEntreEstoquesTableRow() {
        Produto produto = this.getProduto();
        return new Object[]{
            produto.getDescricao(),
            produto.getQuantidadeNaEmbalagem(),
            produto.getUnidadeDeMedida(),
            this.getQuantidade()
        };
    }
    
    public Object[] getInstanciasMovimentacaoTableRow() {
        return new Object[]{
            this.getEstoque().getDescricao(),
            this.getProduto().getDescricao(),
            this.getQuantidade(),
            AuxFunctions.valorFloatParaString(this.getProduto().getValorUnitarioPago() * this.getQuantidade())
        };
    }
    
    public Estoque getEstoque() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoque);
    }
    
    public Produto getProduto() {
        return ProdutoDAO.selectProdutoPorId(this.getIdProduto());
    }

}
