package gui;

import aux_functions.AuxFunctions;
import dao.EntradaProdutosDAO;
import dao.EstoqueDAO;
import dao.FornecedorDAO;
import dao.InstanciaProdutoEstoqueDAO;
import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import gui.estoques.MaisInfoEstoque;
import gui.estoques.NovoEstoque;
import gui.fornecedores.MaisInfoFornecedor;
import gui.fornecedores.NovoFornecedor;
import gui.movimentacoes.OpcoesNovaMovimentacao;
import gui.movimentacoes.entrada.MaisInfoMovimentacaoEntrada;
import gui.movimentacoes.entre_estoques.MaisInfoMovimentacaoEntreEstoques;
import gui.movimentacoes.saida.MaisInfoMovimentacaoSaida;
import gui.produtos.BuscaProduto;
import gui.produtos.MaisInfoProduto;
import gui.produtos.NovoProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.EntradaProdutos;
import models.Estoque;
import models.Fornecedor;
import models.InstanciaProdutoEstoque;
import models.Movimentacao;
import models.MovimentacaoEntreEstoques;
import models.Produto;
import models.SaidaProdutos;

public class MenuPrincipal extends javax.swing.JFrame {

    private Produto[] produtosNaTabela;

    private Fornecedor[] fornecedoresNoComboBox;
    private EntradaProdutos[] comprasNaTabelaFornecedores;

    private Estoque[] estoquesNoComboBox;
    private InstanciaProdutoEstoque[] instanciasProdutoEstoqueNaTabela;
    private Movimentacao[] movimentacoesNaTabela;

    public MenuPrincipal() {
        initComponents();

        //============== Produtos ==============
        setArrayProdutosTodosCadastrados();
        preencherTabelaProdutos();

        //============= Fornecedores ===========
        setArrayFornecedoresTodosCadastrados();
        preencherComboBoxFornecedores();

        setArrayFornecedorComprasTodasCadastradas();
        preencherTabelaFornecedorCompras();

        createFornecedoresComboBoxListener();

        //============== Estoques ==============
        setArrayEstoquesTodosCadastrados();
        preencherComboBoxEstoques();

        setArrayInstanciasProdutoEstoqueTodosCadastrados();
        preencherTabelaInstanciasProdutoEstoque();

        setArrayMovimentacoesTodasCadastradas();
        preencherTabelaMovimentacoes();

        createEstoquesComboBoxListener();
    }

    //====================== Listeners ===========================//
    private void createFornecedoresComboBoxListener() {
        comboBoxFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedIndex = comboBoxFornecedor.getSelectedIndex();

                if (selectedIndex <= 0) {
                    setArrayFornecedorComprasTodasCadastradas();
                } else {
                    Fornecedor fornecedorSelecionado = fornecedoresNoComboBox[selectedIndex - 1];
                    updateArrayFornecedorCompras(EntradaProdutosDAO.selectComprasPorIdFornecedor(fornecedorSelecionado.getIdFornecedor()));
                }

                preencherTabelaFornecedorCompras();
            }
        });
    }

    private void createEstoquesComboBoxListener() {
        comboBoxEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedIndex = comboBoxEstoque.getSelectedIndex();

                if (selectedIndex <= 0) {
                    setArrayInstanciasProdutoEstoqueTodosCadastrados();

                    setArrayMovimentacoesTodasCadastradas();
                } else {
                    Estoque estoqueSelecionado = estoquesNoComboBox[selectedIndex - 1];

                    updateArrayInstanciasProdutoEstoque(InstanciaProdutoEstoqueDAO.selectInstanciasProdutoEstoquePorEstoque(estoqueSelecionado.getIdEstoque(), false));

                    updateArrayMovimentacoes(MovimentacaoDAO.selectMovimentacoesPorEstoque(estoqueSelecionado.getIdEstoque()));
                }

                preencherTabelaInstanciasProdutoEstoque();
                preencherTabelaMovimentacoes();
            }
        });
    }

    //======================= Produtos ===========================//
    public void setArrayProdutosTodosCadastrados() {
        this.produtosNaTabela = ProdutoDAO.selectTodosProdutos();
    }

    public void updateArrayProdutos(Produto[] novoArrayProdutos) {
        this.produtosNaTabela = novoArrayProdutos;
    }

    public void preencherTabelaProdutos() {
        DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
        model.setRowCount(0);
        for (Produto produto : this.produtosNaTabela) {
            model.addRow(produto.getMenuPrincipalTableRow());
        }
    }

    //====================== Fornecedores ========================//
    public void setArrayFornecedoresTodosCadastrados() {
        this.fornecedoresNoComboBox = FornecedorDAO.selectTodosFornecedores();
    }

    public void preencherComboBoxFornecedores() {
        comboBoxFornecedor.removeAllItems();

        comboBoxFornecedor.addItem("TODOS FORNECEDORES");
        for (Fornecedor fornecedor : fornecedoresNoComboBox) {
            comboBoxFornecedor.addItem(fornecedor.getDescricao());
        }
    }

    private void setArrayFornecedorComprasTodasCadastradas() {
        this.comprasNaTabelaFornecedores = EntradaProdutosDAO.selectTodasCompras();
    }

    public void updateArrayFornecedorCompras(EntradaProdutos[] compras) {
        this.comprasNaTabelaFornecedores = compras;
    }

    public void preencherTabelaFornecedorCompras() {
        DefaultTableModel model = (DefaultTableModel) tabelaFornecedorCompras.getModel();
        model.setRowCount(0);

        for (EntradaProdutos compra : this.comprasNaTabelaFornecedores) {
            model.addRow(compra.getCompraFornecedoresTableRow());
        }
    }

    //======================== Estoques ==========================//
    public void setArrayEstoquesTodosCadastrados() {
        this.estoquesNoComboBox = EstoqueDAO.selectTodosEstoques();
    }

    public void preencherComboBoxEstoques() {
        comboBoxEstoque.removeAllItems();

        comboBoxEstoque.addItem("TODOS ESTOQUES");
        for (Estoque estoque : estoquesNoComboBox) {
            comboBoxEstoque.addItem(estoque.getDescricao());
        }
    }

    public void setArrayInstanciasProdutoEstoqueTodosCadastrados() {
        this.instanciasProdutoEstoqueNaTabela = InstanciaProdutoEstoqueDAO.getTodasInstanciasProdutoEstoque(false);
        comboBoxEstoque.setSelectedIndex(0);
    }

    public void updateArrayInstanciasProdutoEstoque(InstanciaProdutoEstoque[] instancias) {
        this.instanciasProdutoEstoqueNaTabela = instancias;
    }

    public void preencherTabelaInstanciasProdutoEstoque() {
        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasProdutoEstoque.getModel();
        model.setRowCount(0);

        for (InstanciaProdutoEstoque instancia : this.instanciasProdutoEstoqueNaTabela) {
            model.addRow(instancia.getInstanciasMovimentacaoTableRow());
        }
    }

    public void setArrayMovimentacoesTodasCadastradas() {
        this.movimentacoesNaTabela = MovimentacaoDAO.selectTodasMovimentacoes();
        comboBoxEstoque.setSelectedIndex(0);
    }

    public void updateArrayMovimentacoes(Movimentacao[] movimentacoes) {
        this.movimentacoesNaTabela = movimentacoes;
    }

    public void preencherTabelaMovimentacoes() {
        DefaultTableModel model = (DefaultTableModel) tabelaMovimentacoes.getModel();
        model.setRowCount(0);

        for (Movimentacao movimentacao : this.movimentacoesNaTabela) {
            model.addRow(movimentacao.getMovimentacaoTableRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        produtosPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        botaoNovoProduto = new javax.swing.JButton();
        botaoMaisInfoProduto = new javax.swing.JButton();
        botaoBuscarProduto = new javax.swing.JButton();
        botaoLimparBuscaProduto = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        fornecedoresPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        botaoNovoFornecedor = new javax.swing.JButton();
        botaoMaisInfoFornecedor = new javax.swing.JButton();
        comboBoxFornecedor = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelaFornecedorCompras = new javax.swing.JTable();
        estoquesPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxEstoque = new javax.swing.JComboBox<>();
        botaoMaisInfoEstoque = new javax.swing.JButton();
        botaoNovoEstoque = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        botaoNovaMovimentacao = new javax.swing.JButton();
        maisInfoMovimentacao = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaMovimentacoes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaInstanciasProdutoEstoque = new javax.swing.JTable();
        relatorioPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Estoque");
        setResizable(false);

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        produtosPanel.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        botaoNovoProduto.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoNovoProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adicionar_24.png"))); // NOI18N
        botaoNovoProduto.setText("Novo");
        botaoNovoProduto.setPreferredSize(new java.awt.Dimension(95, 42));
        botaoNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoProdutoActionPerformed(evt);
            }
        });

        botaoMaisInfoProduto.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoMaisInfoProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        botaoMaisInfoProduto.setText("Mais Info");
        botaoMaisInfoProduto.setMaximumSize(new java.awt.Dimension(100, 50));
        botaoMaisInfoProduto.setMinimumSize(new java.awt.Dimension(100, 50));
        botaoMaisInfoProduto.setPreferredSize(new java.awt.Dimension(95, 42));
        botaoMaisInfoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMaisInfoProdutoActionPerformed(evt);
            }
        });

        botaoBuscarProduto.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoBuscarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        botaoBuscarProduto.setText("Buscar");
        botaoBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarProdutoActionPerformed(evt);
            }
        });

        botaoLimparBuscaProduto.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoLimparBuscaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eraser_24.png"))); // NOI18N
        botaoLimparBuscaProduto.setText("Limpar Busca");
        botaoLimparBuscaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparBuscaProdutoActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoBuscarProduto)
                .addGap(18, 18, 18)
                .addComponent(botaoLimparBuscaProduto)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoMaisInfoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoBuscarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoLimparBuscaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoMaisInfoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tabelaProdutos.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Valor Un. Pago", "Valor Un. Venda", "Quantidade na Embalagem", "Unidade de Medida", "Produzido na Padaria?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaProdutos);
        if (tabelaProdutos.getColumnModel().getColumnCount() > 0) {
            tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(360);
            tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabelaProdutos.getColumnModel().getColumn(4).setMinWidth(100);
            tabelaProdutos.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabelaProdutos.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setText("Produtos cadastrados:");

        javax.swing.GroupLayout produtosPanelLayout = new javax.swing.GroupLayout(produtosPanel);
        produtosPanel.setLayout(produtosPanelLayout);
        produtosPanelLayout.setHorizontalGroup(
            produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(produtosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
                    .addGroup(produtosPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        produtosPanelLayout.setVerticalGroup(
            produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produtosPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Produtos", new javax.swing.ImageIcon(getClass().getResource("/produtos_48.png")), produtosPanel); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("Fornecedor:");

        botaoNovoFornecedor.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoNovoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adicionar_24.png"))); // NOI18N
        botaoNovoFornecedor.setText("Novo");
        botaoNovoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoFornecedorActionPerformed(evt);
            }
        });

        botaoMaisInfoFornecedor.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoMaisInfoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        botaoMaisInfoFornecedor.setText("Mais Info");
        botaoMaisInfoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMaisInfoFornecedorActionPerformed(evt);
            }
        });

        comboBoxFornecedor.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(comboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoMaisInfoFornecedor)
                .addGap(18, 18, 18)
                .addComponent(botaoNovoFornecedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoMaisInfoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoNovoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setText("Compras com o Fornecedor:");

        tabelaFornecedorCompras.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaFornecedorCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fornecedor", "Data e Hora", "Valor Total", "Estoque de Destino", "Produtos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabelaFornecedorCompras);
        if (tabelaFornecedorCompras.getColumnModel().getColumnCount() > 0) {
            tabelaFornecedorCompras.getColumnModel().getColumn(0).setPreferredWidth(130);
            tabelaFornecedorCompras.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabelaFornecedorCompras.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabelaFornecedorCompras.getColumnModel().getColumn(3).setPreferredWidth(110);
            tabelaFornecedorCompras.getColumnModel().getColumn(4).setPreferredWidth(500);
        }

        javax.swing.GroupLayout fornecedoresPanelLayout = new javax.swing.GroupLayout(fornecedoresPanel);
        fornecedoresPanel.setLayout(fornecedoresPanelLayout);
        fornecedoresPanelLayout.setHorizontalGroup(
            fornecedoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fornecedoresPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fornecedoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
                    .addGroup(fornecedoresPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        fornecedoresPanelLayout.setVerticalGroup(
            fornecedoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fornecedoresPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Fornecedores", new javax.swing.ImageIcon(getClass().getResource("/fornecedor_48.png")), fornecedoresPanel); // NOI18N

        estoquesPanel.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Estoque:");

        comboBoxEstoque.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoMaisInfoEstoque.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoMaisInfoEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        botaoMaisInfoEstoque.setText("Mais Info");
        botaoMaisInfoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMaisInfoEstoqueActionPerformed(evt);
            }
        });

        botaoNovoEstoque.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoNovoEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adicionar_24.png"))); // NOI18N
        botaoNovoEstoque.setText("Novo");
        botaoNovoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoEstoqueActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Movimentações:");

        jButton8.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        jButton8.setText("Buscar");

        botaoNovaMovimentacao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoNovaMovimentacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/move_24.png"))); // NOI18N
        botaoNovaMovimentacao.setText("Movimentar");
        botaoNovaMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovaMovimentacaoActionPerformed(evt);
            }
        });

        maisInfoMovimentacao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        maisInfoMovimentacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        maisInfoMovimentacao.setText("Mais Info");
        maisInfoMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisInfoMovimentacaoActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eraser_24.png"))); // NOI18N
        jButton14.setText("Limpar Buscas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(comboBoxEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoMaisInfoEstoque)
                        .addGap(18, 18, 18)
                        .addComponent(botaoNovoEstoque)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)
                        .addGap(18, 18, 18)
                        .addComponent(maisInfoMovimentacao)
                        .addGap(18, 18, 18)
                        .addComponent(botaoNovaMovimentacao)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maisInfoMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoNovaMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(comboBoxEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoNovoEstoque)
                        .addComponent(botaoMaisInfoEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Produtos no Estoque:");

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("Movimentações:");

        tabelaMovimentacoes.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaMovimentacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Tipo de Movimentação", "Valor Total", "Estoque Origem", "Estoque Destino", "Produtos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaMovimentacoes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaMovimentacoes.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tabelaMovimentacoes);
        if (tabelaMovimentacoes.getColumnModel().getColumnCount() > 0) {
            tabelaMovimentacoes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabelaMovimentacoes.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabelaMovimentacoes.getColumnModel().getColumn(2).setPreferredWidth(75);
            tabelaMovimentacoes.getColumnModel().getColumn(3).setPreferredWidth(180);
            tabelaMovimentacoes.getColumnModel().getColumn(4).setPreferredWidth(180);
            tabelaMovimentacoes.getColumnModel().getColumn(5).setPreferredWidth(500);
        }

        tabelaInstanciasProdutoEstoque.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaInstanciasProdutoEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estoque", "Produto", "Quantidade", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasProdutoEstoque.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabelaInstanciasProdutoEstoque);
        if (tabelaInstanciasProdutoEstoque.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasProdutoEstoque.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabelaInstanciasProdutoEstoque.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabelaInstanciasProdutoEstoque.getColumnModel().getColumn(2).setPreferredWidth(40);
            tabelaInstanciasProdutoEstoque.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        javax.swing.GroupLayout estoquesPanelLayout = new javax.swing.GroupLayout(estoquesPanel);
        estoquesPanel.setLayout(estoquesPanelLayout);
        estoquesPanelLayout.setHorizontalGroup(
            estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(estoquesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(estoquesPanelLayout.createSequentialGroup()
                        .addGroup(estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        estoquesPanelLayout.setVerticalGroup(
            estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estoquesPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab("Estoques", new javax.swing.ImageIcon(getClass().getResource("/prateleira_48.png")), estoquesPanel); // NOI18N

        relatorioPanel.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout relatorioPanelLayout = new javax.swing.GroupLayout(relatorioPanel);
        relatorioPanel.setLayout(relatorioPanelLayout);
        relatorioPanelLayout.setHorizontalGroup(
            relatorioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1129, Short.MAX_VALUE)
        );
        relatorioPanelLayout.setVerticalGroup(
            relatorioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Relatório", new javax.swing.ImageIcon(getClass().getResource("/relatorio_48.png")), relatorioPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoProdutoActionPerformed
        new NovoProduto(this).setVisible(true);
    }//GEN-LAST:event_botaoNovoProdutoActionPerformed

    private void botaoMaisInfoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMaisInfoProdutoActionPerformed
        int selectedRow = tabelaProdutos.getSelectedRow();

        if (selectedRow >= 0) {
            Produto produto = produtosNaTabela[selectedRow];
            new MaisInfoProduto(this, produto.getIdProduto()).setVisible(true);
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção", "Favor selecionar um produto na tabela",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoMaisInfoProdutoActionPerformed

    private void botaoBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarProdutoActionPerformed
        new BuscaProduto(this).setVisible(true);
    }//GEN-LAST:event_botaoBuscarProdutoActionPerformed

    private void botaoLimparBuscaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparBuscaProdutoActionPerformed
        setArrayProdutosTodosCadastrados();
        preencherTabelaProdutos();
    }//GEN-LAST:event_botaoLimparBuscaProdutoActionPerformed

    private void botaoNovoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoFornecedorActionPerformed
        new NovoFornecedor(this).setVisible(true);
    }//GEN-LAST:event_botaoNovoFornecedorActionPerformed

    private void botaoMaisInfoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMaisInfoFornecedorActionPerformed
        int selectedIndex = comboBoxFornecedor.getSelectedIndex();
        if (selectedIndex == 0) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um fornecedor na caixa de seleção.",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            new MaisInfoFornecedor(this, fornecedoresNoComboBox[selectedIndex - 1].getIdFornecedor()).setVisible(true);
        }
    }//GEN-LAST:event_botaoMaisInfoFornecedorActionPerformed

    private void botaoNovoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoEstoqueActionPerformed
        new NovoEstoque(this).setVisible(true);
    }//GEN-LAST:event_botaoNovoEstoqueActionPerformed

    private void botaoMaisInfoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMaisInfoEstoqueActionPerformed
        int selectedIndex = comboBoxEstoque.getSelectedIndex();
        if (selectedIndex == 0) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um estoque na caixa de seleção.",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            new MaisInfoEstoque(this, estoquesNoComboBox[selectedIndex - 1]).setVisible(true);
        }
    }//GEN-LAST:event_botaoMaisInfoEstoqueActionPerformed

    private void botaoNovaMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovaMovimentacaoActionPerformed
        new OpcoesNovaMovimentacao(this).setVisible(true);
    }//GEN-LAST:event_botaoNovaMovimentacaoActionPerformed

    private void maisInfoMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisInfoMovimentacaoActionPerformed
        int selectedRow = tabelaMovimentacoes.getSelectedRow();
        
        if (selectedRow >= 0) {
            if (movimentacoesNaTabela[selectedRow] instanceof EntradaProdutos) {
                new MaisInfoMovimentacaoEntrada(this, movimentacoesNaTabela[selectedRow].getIdMovimentacao()).setVisible(true);
            } else if (movimentacoesNaTabela[selectedRow] instanceof MovimentacaoEntreEstoques) {
                new MaisInfoMovimentacaoEntreEstoques(this, movimentacoesNaTabela[selectedRow].getIdMovimentacao()).setVisible(true);
            } else {
                new MaisInfoMovimentacaoSaida(this, movimentacoesNaTabela[selectedRow].getIdMovimentacao()).setVisible(true);
            }
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar uma movimentação na tabela para fazer essa operação.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_maisInfoMovimentacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscarProduto;
    private javax.swing.JButton botaoLimparBuscaProduto;
    private javax.swing.JButton botaoMaisInfoEstoque;
    private javax.swing.JButton botaoMaisInfoFornecedor;
    private javax.swing.JButton botaoMaisInfoProduto;
    private javax.swing.JButton botaoNovaMovimentacao;
    private javax.swing.JButton botaoNovoEstoque;
    private javax.swing.JButton botaoNovoFornecedor;
    private javax.swing.JButton botaoNovoProduto;
    private javax.swing.JComboBox<String> comboBoxEstoque;
    private javax.swing.JComboBox<String> comboBoxFornecedor;
    private javax.swing.JPanel estoquesPanel;
    private javax.swing.JPanel fornecedoresPanel;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton maisInfoMovimentacao;
    private javax.swing.JPanel produtosPanel;
    private javax.swing.JPanel relatorioPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tabelaFornecedorCompras;
    private javax.swing.JTable tabelaInstanciasProdutoEstoque;
    private javax.swing.JTable tabelaMovimentacoes;
    private javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables
}
