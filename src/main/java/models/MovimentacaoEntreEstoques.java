package models;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.InstanciaProdutoMovimentacaoDAO;
import java.sql.Date;
import java.time.LocalDateTime;

public class MovimentacaoEntreEstoques extends Movimentacao {

    private int idEstoqueOrigem;
    private int idEstoqueDestino;

    public MovimentacaoEntreEstoques(int idEstoqueOrigem, int idEstoqueDestino, int idMovimentacao, java.util.Date data, int tipoMovimentacao) {
        super(idMovimentacao, data, tipoMovimentacao);
        this.idEstoqueOrigem = idEstoqueOrigem;
        this.idEstoqueDestino = idEstoqueDestino;
    }

    public MovimentacaoEntreEstoques(int idEstoqueOrigem, int idEstoqueDestino, java.util.Date data, int tipoMovimentacao) {
        super(data, tipoMovimentacao);
        this.idEstoqueOrigem = idEstoqueOrigem;
        this.idEstoqueDestino = idEstoqueDestino;
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
            "Entre estoques",
            this.getEstoqueOrigem().getDescricao(),
            this.getEstoqueDestino().getDescricao(),
            produtosMovimentacao
        };
    }

    private Estoque getEstoqueOrigem() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoqueOrigem);
    }

    private Estoque getEstoqueDestino() {
        return EstoqueDAO.selectEstoquePorId(this.idEstoqueDestino);
    }

    private InstanciaProdutoMovimentacao[] getInstanciasProduto() {
        return InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(this.getIdMovimentacao(), this.getTipoMovimentacao());
    }

}
