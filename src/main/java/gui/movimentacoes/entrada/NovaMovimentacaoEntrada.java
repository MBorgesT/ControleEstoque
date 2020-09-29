package gui.movimentacoes.entrada;

import aux_functions.AuxFunctions;
import dao.EntradaProdutosDAO;
import dao.EstoqueDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;
import gui.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.EntradaProdutos;
import models.Estoque;
import models.Fornecedor;
import models.InstanciaProdutoMovimentacao;
import models.Produto;
import validators.EntradaValidator;

public class NovaMovimentacaoEntrada extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;

    private Fornecedor[] todosFornecedores;
    private Estoque[] todosEstoques;

    private Produto[] todosProdutosCadastrados;
    private Produto[] produtosNaTabela;
    private ArrayList<Produto> produtosEntrada;

    public NovaMovimentacaoEntrada(MenuPrincipal menuPrincipal) {
        initComponents();

        this.menuPrincipal = menuPrincipal;

        this.todosProdutosCadastrados = ProdutoDAO.selectTodosProdutos();
        this.produtosNaTabela = this.todosProdutosCadastrados;
        this.produtosEntrada = new ArrayList<>();

        setRadioButtonsListeners();

        preencherComboBoxFornecedores();
        preencherComboBoxEstoques();
        preencherTabela();

        comboBoxFornecedor.setEnabled(true);
        comboBoxFornecedor.setSelectedIndex(-1);

        comboBoxEstoqueDestino.setSelectedIndex(-1);

        tabelaProdutosCadastrados.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabelaProdutosEntrada.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void setRadioButtonsListeners() {
        radioButtonSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (radioButtonSim.isSelected()) {
                    comboBoxFornecedor.setEnabled(true);
                    comboBoxFornecedor.setSelectedIndex(-1);

                    campoValorTabela.setText("");
                    botaoCalcularValorTabela.setEnabled(true);

                    if (tabelaProdutosEntrada.getColumnCount() == 4) {
                        DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
                        model.setColumnCount(5);

                        TableColumn coluna = tabelaProdutosEntrada.getTableHeader().getColumnModel().getColumn(4);
                        coluna.setHeaderValue("Valor unitário");
                    }
                }
            }
        });

        radioButtonNao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (radioButtonNao.isSelected()) {
                    comboBoxFornecedor.setEnabled(false);
                    comboBoxFornecedor.setSelectedIndex(-1);

                    campoValorTabela.setText("");
                    botaoCalcularValorTabela.setEnabled(false);

                    if (tabelaProdutosEntrada.getColumnCount() == 5) {
                        DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
                        model.setColumnCount(4);
                    }
                }
            }
        });
    }

    private void preencherComboBoxFornecedores() {
        todosFornecedores = FornecedorDAO.selectTodosFornecedores();
        comboBoxFornecedor.removeAllItems();

        for (Fornecedor fornecedor : todosFornecedores) {
            comboBoxFornecedor.addItem(fornecedor.getDescricao());
        }

        comboBoxFornecedor.setSelectedIndex(-1);
    }

    private void preencherComboBoxEstoques() {
        todosEstoques = EstoqueDAO.selectTodosEstoques();
        comboBoxEstoqueDestino.removeAllItems();

        for (Estoque estoque : todosEstoques) {
            comboBoxEstoqueDestino.addItem(estoque.getDescricao());
        }

        comboBoxEstoqueDestino.setSelectedIndex(-1);
    }

    private void preencherTabela() {
        DefaultTableModel model = (DefaultTableModel) tabelaProdutosCadastrados.getModel();

        model.setRowCount(0);
        for (Produto p : produtosNaTabela) {
            model.addRow(p.getCadastroEntradaTableRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoriaButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        radioButtonSim = new javax.swing.JRadioButton();
        radioButtonNao = new javax.swing.JRadioButton();
        comboBoxFornecedor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxEstoqueDestino = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutosCadastrados = new javax.swing.JTable();
        botaoAdicionarProduto = new javax.swing.JButton();
        botaoExcluirProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutosEntrada = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        botaoBuscar = new javax.swing.JButton();
        campoBusca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campoValorTabela = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        botaoCalcularValorTabela = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Entrada");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_48.png"))); // NOI18N
        jLabel1.setText("Nova Entrada em Estoque");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Compra?");

        categoriaButtonGroup.add(radioButtonSim);
        radioButtonSim.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonSim.setSelected(true);
        radioButtonSim.setText("Sim");
        radioButtonSim.setName("radioButtonSim"); // NOI18N

        categoriaButtonGroup.add(radioButtonNao);
        radioButtonNao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonNao.setText("Não");
        radioButtonNao.setName("radioButtonNao"); // NOI18N

        comboBoxFornecedor.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxFornecedor.setName("comboBoxFornecedor"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Fornecedor:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de destino:");

        comboBoxEstoqueDestino.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxEstoqueDestino.setName("comboBoxEstoqueDestino"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Produtos cadastrados:");

        jScrollPane1.setName("scrollPaneProdutosCadastrados"); // NOI18N

        tabelaProdutosCadastrados.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaProdutosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. na embalagem", "Un. de medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutosCadastrados.setName("tabelaProdutosCadastrados"); // NOI18N
        jScrollPane1.setViewportView(tabelaProdutosCadastrados);
        if (tabelaProdutosCadastrados.getColumnModel().getColumnCount() > 0) {
            tabelaProdutosCadastrados.getColumnModel().getColumn(0).setPreferredWidth(140);
            tabelaProdutosCadastrados.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabelaProdutosCadastrados.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        botaoAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_24.png"))); // NOI18N
        botaoAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarProdutoActionPerformed(evt);
            }
        });

        botaoExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close_24.png"))); // NOI18N
        botaoExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirProdutoActionPerformed(evt);
            }
        });

        jScrollPane2.setName("scrollPaneProdutosEntrada"); // NOI18N

        tabelaProdutosEntrada.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaProdutosEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. na embalagem", "Un. de medida", "Quantidade", "Valor Unitário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutosEntrada.setName("tabelaProdutosEntrada"); // NOI18N
        jScrollPane2.setViewportView(tabelaProdutosEntrada);
        if (tabelaProdutosEntrada.getColumnModel().getColumnCount() > 0) {
            tabelaProdutosEntrada.getColumnModel().getColumn(0).setPreferredWidth(225);
            tabelaProdutosEntrada.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaProdutosEntrada.getColumnModel().getColumn(4).setPreferredWidth(90);
        }

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Produtos da entrada:");

        botaoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        campoBusca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sigma_24.png"))); // NOI18N
        jLabel8.setText("Valor na tabela:");

        campoValorTabela.setEditable(false);
        campoValorTabela.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoCalcularValorTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculadora_24.png"))); // NOI18N
        botaoCalcularValorTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularValorTabelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(radioButtonSim)
                                .addGap(18, 18, 18)
                                .addComponent(radioButtonNao))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(comboBoxEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addComponent(campoBusca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoBuscar))
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoAdicionarProduto)
                            .addComponent(botaoExcluirProduto))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoValorTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(botaoCalcularValorTabela)))
                                .addGap(0, 110, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioButtonSim)
                        .addComponent(radioButtonNao))
                    .addComponent(comboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBuscar))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(campoValorTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6))
                                    .addComponent(botaoCalcularValorTabela, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(botaoAdicionarProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoExcluirProduto))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoCancelar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_36.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoCadastrar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ok_36.png"))); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoCadastrar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (EntradaValidator.validate(formPanel) && AuxFunctions.popupConfirmacao("Confirmação", "Realmente deseja cadastrar esta entrada?")) {
            int idFornecedor = radioButtonSim.isSelected() ? todosFornecedores[comboBoxFornecedor.getSelectedIndex()].getIdFornecedor() : -1;
            int tipoMovimentacao = radioButtonSim.isSelected() ? 4 : 3;

            EntradaProdutos novaEntrada = new EntradaProdutos(
                    idFornecedor,
                    todosEstoques[comboBoxEstoqueDestino.getSelectedIndex()].getIdEstoque(),
                    tipoMovimentacao,
                    new Date(System.currentTimeMillis())
            );

            ArrayList<InstanciaProdutoMovimentacao> arrayInstancias = new ArrayList<>();
            for (int i = 0; i < tabelaProdutosEntrada.getRowCount(); i++) {
                InstanciaProdutoMovimentacao instancia;
                if (radioButtonSim.isSelected()) {
                    instancia = new InstanciaProdutoMovimentacao(
                            AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 4).toString()),
                            produtosEntrada.get(i).getIdProduto(),
                            Integer.parseInt(tabelaProdutosEntrada.getValueAt(i, 3).toString())
                    );
                } else {
                    instancia = new InstanciaProdutoMovimentacao(
                            produtosEntrada.get(i).getIdProduto(),
                            Integer.parseInt(tabelaProdutosEntrada.getValueAt(i, 3).toString())
                    );
                }
                arrayInstancias.add(instancia);
            }

            if (EntradaProdutosDAO.insertEntradaProdutos(novaEntrada, arrayInstancias.toArray(new InstanciaProdutoMovimentacao[0]))) {
                AuxFunctions.popup(
                        this,
                        "Cadastro de entrada",
                        "A entrada de produtos foi cadastrada com sucesso.",
                        JOptionPane.INFORMATION_MESSAGE
                );

                menuPrincipal.setArrayInstanciasProdutoEstoqueTodosCadastrados();
                menuPrincipal.preencherTabelaInstanciasProdutoEstoque();

                menuPrincipal.setArrayMovimentacoesTodasCadastradas();
                menuPrincipal.preencherTabelaMovimentacoes();

                this.dispose();
            } else {
                AuxFunctions.popup(
                        this,
                        "Erro",
                        "Houve algum erro ao cadastrar a entrada no banco de dados.\nFavorReiniciar o programa e tentar novamente.",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        String searchParam = campoBusca.getText().toUpperCase();
        ArrayList<Produto> produtosBuscados = new ArrayList<>();

        for (Produto p : todosProdutosCadastrados) {
            if (p.getDescricao().contains(searchParam)) {
                produtosBuscados.add(p);
            }
        }

        produtosNaTabela = produtosBuscados.toArray(new Produto[0]);
        preencherTabela();
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarProdutoActionPerformed
        int selectedRow = tabelaProdutosCadastrados.getSelectedRow();

        if (selectedRow >= 0) {

            Produto novoProduto = produtosNaTabela[selectedRow];
            boolean flag = true;
            for (Produto produtoEntrada : produtosEntrada) {
                if (novoProduto.getIdProduto() == produtoEntrada.getIdProduto()) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                produtosEntrada.add(novoProduto);

                DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
                model.addRow(produtosNaTabela[selectedRow].getCadastroEntradaTableRow());
            } else {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Este produto já está na tabela à direita.",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um produto na tabela à esquerda.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoAdicionarProdutoActionPerformed

    private void botaoExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirProdutoActionPerformed
        int selectedRow = tabelaProdutosEntrada.getSelectedRow();

        if (selectedRow >= 0) {
            produtosEntrada.remove(selectedRow);

            DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
            model.removeRow(selectedRow);
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um produto na tabela à esquerda.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoExcluirProdutoActionPerformed

    private void botaoCalcularValorTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularValorTabelaActionPerformed
        float valorTotal = 0;

        if (EntradaValidator.validateTabela(tabelaProdutosEntrada, radioButtonSim)) {
            for (int i = 0; i < tabelaProdutosEntrada.getRowCount(); i++) {
                int quantidade = Integer.parseInt(tabelaProdutosEntrada.getValueAt(i, 3).toString());
                float valorUnitario = AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 4).toString());

                valorTotal += quantidade * valorUnitario;
            }
        }

        campoValorTabela.setText("R$ " + AuxFunctions.valorFloatParaString(valorTotal));
    }//GEN-LAST:event_botaoCalcularValorTabelaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCalcularValorTabela;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluirProduto;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JTextField campoValorTabela;
    private javax.swing.ButtonGroup categoriaButtonGroup;
    private javax.swing.JComboBox<String> comboBoxEstoqueDestino;
    private javax.swing.JComboBox<String> comboBoxFornecedor;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioButtonNao;
    private javax.swing.JRadioButton radioButtonSim;
    private javax.swing.JTable tabelaProdutosCadastrados;
    private javax.swing.JTable tabelaProdutosEntrada;
    // End of variables declaration//GEN-END:variables
}
