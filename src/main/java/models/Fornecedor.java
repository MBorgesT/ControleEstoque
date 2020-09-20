package models;

public class Fornecedor {

    private int idFornecedor;
    private String descricao;
    private String telefone1;
    private String telefone2;

    public Fornecedor(int idFornecedor, String descricao, String telefone1, String telefone2) {
        this.idFornecedor = idFornecedor;
        this.descricao = descricao;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public Fornecedor(String descricao, String telefone1, String telefone2) {
        this.descricao = descricao;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
    
    

}
