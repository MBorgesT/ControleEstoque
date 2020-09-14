USE controleEstoque;

CREATE TABLE Estoque (
 idEstoque INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
 descricao VARCHAR(100) NOT NULL
);


CREATE TABLE Fornecedor (
 idFornecedor INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
 descricao VARCHAR(100) NOT NULL,
 telefone1 VARCHAR(50),
 telefone2 VARCHAR(50)
);


CREATE TABLE Movimentacao (
 idMovimentacao INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
 dataHora DATETIME NOT NULL,
 tipoMovimentacao INTEGER NOT NULL,
 idEstoqueOrigem INTEGER,
 idEstoqueDestino INTEGER,
 idFornecedor INTEGER
);


CREATE TABLE Produto (
 idProduto INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
 descricao VARCHAR(100) NOT NULL,
 valorUnitarioPago REAL NOT NULL,
 valorUnitarioVenda REAL NOT NULL,
 unidadeDeMedida VARCHAR(20) NOT NULL,
 quantidadeNaEmbalagem REAL NOT NULL,
 produzidoNaPadaria BOOLEAN NOT NULL
);


CREATE TABLE Ingrediente (
 idIngrediente INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
 idProduto INTEGER NOT NULL,
 idProdutoFazParte INTEGER NOT NULL,
 quantidadeRelativa REAL NOT NULL
);


CREATE TABLE InstanciaProduto (
 idInstanciaProduto INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
 idProduto INTEGER NOT NULL,
 quantidade INTEGER NOT NULL,
 valorUnitarioPago REAL NOT NULL,
 idMovimentacao INTEGER,
 idEstoque INTEGER
);

ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_EstoqueOrigem FOREIGN KEY (idEstoqueOrigem) REFERENCES Estoque (idEstoque);
ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_EstoqueDestino FOREIGN KEY (idEstoqueDestino) REFERENCES Estoque (idEstoque);
ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_Fornecedor FOREIGN KEY (idFornecedor) REFERENCES Fornecedor (idFornecedor);


ALTER TABLE Ingrediente ADD CONSTRAINT FK_Ingrediente_Produto FOREIGN KEY (idProduto) REFERENCES Produto (idProduto);
ALTER TABLE Ingrediente ADD CONSTRAINT FK_Ingrediente_ProdutoFazParte FOREIGN KEY (idProdutoFazParte) REFERENCES Produto (idProduto);


ALTER TABLE InstanciaProduto ADD CONSTRAINT FK_InstanciaProduto_Produto FOREIGN KEY (idProduto) REFERENCES Produto (idProduto);
ALTER TABLE InstanciaProduto ADD CONSTRAINT FK_InstanciaProduto_Movimentacao FOREIGN KEY (idMovimentacao) REFERENCES Movimentacao (idMovimentacao);
ALTER TABLE InstanciaProduto ADD CONSTRAINT FK_InstanciaProduto_Estoque FOREIGN KEY (idEstoque) REFERENCES Estoque (idEstoque);


