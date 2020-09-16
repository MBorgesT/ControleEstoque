package gui;

import aux_functions.AuxFunctions;
import dao.ProdutoDAO;
import gui.produtos.BuscaProduto;
import gui.produtos.MaisInfoProduto;
import gui.produtos.NovoProduto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Produto;

public class MenuPrincipal extends javax.swing.JFrame {

    Produto[] produtosNaTabela;

    public MenuPrincipal() {
        initComponents();
        
        setArrayProdutosTodosCadastrados();
        preencherTabelaProdutos();
    }
    
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
        jButton2 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        estoquesPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        relatorioPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Produtos", new javax.swing.ImageIcon(getClass().getResource("/produtos_48.png")), produtosPanel); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("Fornecedor:");

        jButton2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adicionar_24.png"))); // NOI18N
        jButton2.setText("Novo");

        jButton11.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        jButton11.setText("Mais Info");

        jComboBox1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setText("Compras com o Fornecedor:");

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setPreferredWidth(130);
            jTable6.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable6.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable6.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTable6.getColumnModel().getColumn(4).setPreferredWidth(500);
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Fornecedores", new javax.swing.ImageIcon(getClass().getResource("/fornecedor_48.png")), fornecedoresPanel); // NOI18N

        estoquesPanel.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Estoque:");

        jComboBox2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        jButton3.setText("Mais Info");

        jButton4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adicionar_24.png"))); // NOI18N
        jButton4.setText("Novo");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Movimentações:");

        jButton8.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        jButton8.setText("Buscar");

        jButton9.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/move_24.png"))); // NOI18N
        jButton9.setText("Movimentar");

        jButton10.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mais_info_24.png"))); // NOI18N
        jButton10.setText("Mais Info");

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
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Estoque", "Produto", "Quantidade", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoscrolls(false);
        jTable2.setPreferredSize(new java.awt.Dimension(250, 72));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(260);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(360);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(125);
        }

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Produtos no Estoque:");

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("Movimentações Internas:");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Data e Hora", "Tipo de Movimentação", "Valor Total", "Estoque Origem", "Estoque Destino", "Produtos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setPreferredWidth(140);
            jTable5.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable5.getColumnModel().getColumn(2).setPreferredWidth(75);
            jTable5.getColumnModel().getColumn(3).setPreferredWidth(180);
            jTable5.getColumnModel().getColumn(4).setPreferredWidth(180);
            jTable5.getColumnModel().getColumn(5).setPreferredWidth(500);
        }

        javax.swing.GroupLayout estoquesPanelLayout = new javax.swing.GroupLayout(estoquesPanel);
        estoquesPanel.setLayout(estoquesPanelLayout);
        estoquesPanelLayout.setHorizontalGroup(
            estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(estoquesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(estoquesPanelLayout.createSequentialGroup()
                        .addGroup(estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        estoquesPanelLayout.setVerticalGroup(
            estoquesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estoquesPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGap(0, 716, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Relatório", new javax.swing.ImageIcon(getClass().getResource("/relatorio_48.png")), relatorioPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        
        if (selectedRow >= 0){
            Produto produto = produtosNaTabela[selectedRow];
            new MaisInfoProduto(this, produto).setVisible(true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscarProduto;
    private javax.swing.JButton botaoLimparBuscaProduto;
    private javax.swing.JButton botaoMaisInfoProduto;
    private javax.swing.JButton botaoNovoProduto;
    private javax.swing.JPanel estoquesPanel;
    private javax.swing.JPanel fornecedoresPanel;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JPanel produtosPanel;
    private javax.swing.JPanel relatorioPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables
}
