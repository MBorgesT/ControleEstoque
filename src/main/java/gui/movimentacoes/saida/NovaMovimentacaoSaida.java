package gui.movimentacoes.saida;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.InstanciaProdutoEstoqueDAO;
import dao.SaidaProdutosDAO;
import gui.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.Estoque;
import models.InstanciaProdutoEstoque;
import models.InstanciaProdutoMovimentacao;
import models.SaidaProdutos;
import validators.SaidaValidator;

public class NovaMovimentacaoSaida extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;

    private Estoque[] todosEstoques;

    private InstanciaProdutoEstoque[] todasInstanciasEstoque;
    private InstanciaProdutoEstoque[] instanciasNaTabela;
    private ArrayList<InstanciaProdutoMovimentacao> instanciasSaida;

    public NovaMovimentacaoSaida(MenuPrincipal menuPrincipal) {
        initComponents();

        this.menuPrincipal = menuPrincipal;

        this.instanciasSaida = new ArrayList<>();

        setRadioButtonsListeners();
        setComboBoxListener();

        preencherComboBox();

        tabelaInstanciasCadastradas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabelaInstanciasMovimentacao.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void setRadioButtonsListeners() {
        radioButtonSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (radioButtonSim.isSelected()) {
                    if (tabelaInstanciasMovimentacao.getColumnCount() == 4) {
                        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
                        model.setColumnCount(5);

                        TableColumn coluna = tabelaInstanciasMovimentacao.getTableHeader().getColumnModel().getColumn(4);
                        coluna.setHeaderValue("Valor unitário");
                    }

                    labelValorTotal.setVisible(true);
                    campoValorTotal.setVisible(true);
                    botaoCalcularValorTotal.setVisible(true);

                    campoValorTotal.setText("");
                }
            }
        });

        radioButtonNao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (radioButtonNao.isSelected()) {
                    if (tabelaInstanciasMovimentacao.getColumnCount() == 5) {
                        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
                        model.setColumnCount(4);
                    }

                    labelValorTotal.setVisible(false);
                    campoValorTotal.setVisible(false);
                    botaoCalcularValorTotal.setVisible(false);

                    campoValorTotal.setText("");
                }
            }
        });
    }

    private void setComboBoxListener() {
        comboBoxEstoqueOrigem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedIndex = comboBoxEstoqueOrigem.getSelectedIndex();
                if (selectedIndex >= 0) {
                    int idEstoque = todosEstoques[selectedIndex].getIdEstoque();

                    todasInstanciasEstoque = InstanciaProdutoEstoqueDAO.selectInstanciasProdutoEstoquePorEstoque(idEstoque, false);

                    instanciasNaTabela = todasInstanciasEstoque;

                    instanciasSaida = new ArrayList<>();

                    preencherTabelas();

                    campoBusca.setText("");
                    campoQuantidade.setText("");

                    campoValorTotal.setText("");
                }
            }
        });
    }

    private void preencherComboBox() {
        this.todosEstoques = EstoqueDAO.selectTodosEstoques();

        comboBoxEstoqueOrigem.removeAllItems();

        for (Estoque estoque : this.todosEstoques) {
            comboBoxEstoqueOrigem.addItem(estoque.getDescricao());
        }
    }

    private void preencherTabelas() {
        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasCadastradas.getModel();
        model.setRowCount(0);
        for (InstanciaProdutoEstoque instancia : instanciasNaTabela) {
            model.addRow(instancia.getMovimentacaoEntreEstoquesTableRow());
        }

        model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
        model.setRowCount(0);
        for (InstanciaProdutoMovimentacao instancia : instanciasSaida) {
            model.addRow(instancia.getEntreEstoquesTableRow());
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
        jLabel5 = new javax.swing.JLabel();
        comboBoxEstoqueOrigem = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaInstanciasCadastradas = new javax.swing.JTable();
        botaoAdicionarProduto = new javax.swing.JButton();
        botaoRemoverProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaInstanciasMovimentacao = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        campoBusca = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        campoQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        labelValorTotal = new javax.swing.JLabel();
        campoValorTotal = new javax.swing.JTextField();
        botaoCalcularValorTotal = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Saída de Estoque");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/out_arrow_48.png"))); // NOI18N
        jLabel1.setText("Nova Saída de Estoque");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Venda?");

        categoriaButtonGroup.add(radioButtonSim);
        radioButtonSim.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonSim.setSelected(true);
        radioButtonSim.setText("Sim");
        radioButtonSim.setName("radioButtonSim"); // NOI18N

        categoriaButtonGroup.add(radioButtonNao);
        radioButtonNao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonNao.setText("Não");
        radioButtonNao.setName("radioButtonNao"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de origem:");

        comboBoxEstoqueOrigem.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxEstoqueOrigem.setName("comboBoxEstoqueOrigem"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Produtos cadastrados:");

        tabelaInstanciasCadastradas.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaInstanciasCadastradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. embalagem", "Un. medida", "Qtd. estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasCadastradas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaInstanciasCadastradas);
        if (tabelaInstanciasCadastradas.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasCadastradas.getColumnModel().getColumn(0).setPreferredWidth(120);
            tabelaInstanciasCadastradas.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        botaoAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_24.png"))); // NOI18N
        botaoAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarProdutoActionPerformed(evt);
            }
        });

        botaoRemoverProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close_24.png"))); // NOI18N
        botaoRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverProdutoActionPerformed(evt);
            }
        });

        jScrollPane2.setName("scrollPaneTabela"); // NOI18N

        tabelaInstanciasMovimentacao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaInstanciasMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. embalagem", "Un. medida", "Qtd. saida", "Valor unitário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasMovimentacao.setName("tabelaInstanciasMovimentacao"); // NOI18N
        tabelaInstanciasMovimentacao.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelaInstanciasMovimentacao);
        if (tabelaInstanciasMovimentacao.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(225);
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(4).setPreferredWidth(90);
        }

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Produtos a serem retirados do estoque:");

        campoBusca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        campoQuantidade.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Qtd.:");

        labelValorTotal.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelValorTotal.setText("Valor total:");

        campoValorTotal.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoCalcularValorTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculadora_24.png"))); // NOI18N
        botaoCalcularValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularValorTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(639, 639, 639)
                        .addComponent(jLabel2))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(comboBoxEstoqueOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(554, 554, 554)
                        .addComponent(radioButtonSim)
                        .addGap(18, 18, 18)
                        .addComponent(radioButtonNao))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(292, 292, 292)
                        .addComponent(jLabel3))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(botaoBuscar)
                        .addGap(82, 82, 82)
                        .addComponent(labelValorTotal)
                        .addGap(12, 12, 12)
                        .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoCalcularValorTotal))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAdicionarProduto)
                            .addComponent(botaoRemoverProduto))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxEstoqueOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioButtonSim)
                    .addComponent(radioButtonNao))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(6, 6, 6)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botaoBuscar)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(labelValorTotal))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(botaoCalcularValorTotal))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(botaoAdicionarProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(botaoRemoverProduto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoCadastrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarProdutoActionPerformed
        int selectedRow = tabelaInstanciasCadastradas.getSelectedRow();

        if (selectedRow >= 0) {

            InstanciaProdutoEstoque instanciaDaTabela = instanciasNaTabela[selectedRow];

            if (!campoQuantidade.getText().isEmpty()) {

                try {

                    int qtd = Integer.parseInt(campoQuantidade.getText());

                    if (qtd > 0) {

                        if (qtd <= instanciaDaTabela.getQuantidade()) {

                            boolean flag = true;
                            for (InstanciaProdutoMovimentacao i : instanciasSaida) {
                                if (i.getIdProduto() == instanciaDaTabela.getIdProduto()) {
                                    i.setQuantidade(i.getQuantidade() + qtd);
                                    flag = false;
                                    break;
                                }
                            }

                            if (flag) {
                                InstanciaProdutoMovimentacao novaInstancia = new InstanciaProdutoMovimentacao(
                                        instanciaDaTabela.getIdProduto(),
                                        qtd
                                );
                                instanciasSaida.add(novaInstancia);
                            }

                            instanciaDaTabela.setQuantidade(instanciaDaTabela.getQuantidade() - qtd);

                            preencherTabelas();

                            campoQuantidade.setText("");

                        } else {

                            AuxFunctions.popup(
                                    this,
                                    "Atenção",
                                    "A quantidade não pode ser maior do que a que há no estoque.",
                                    JOptionPane.WARNING_MESSAGE
                            );

                        }

                    } else {

                        AuxFunctions.popup(
                                this,
                                "Atenção",
                                "A quantidade precisa ser maior ou igual a zero.",
                                JOptionPane.WARNING_MESSAGE
                        );

                    }

                } catch (NumberFormatException e) {
                    AuxFunctions.popup(
                            this,
                            "Atenção",
                            "O valor de quantidade está incorreto.",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } else {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Favor informar a quantidade de produtos que deseja transferir.",
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

    private void botaoRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverProdutoActionPerformed
        int selectedRow = tabelaInstanciasMovimentacao.getSelectedRow();
        if (selectedRow >= 0) {

            InstanciaProdutoMovimentacao instanciaSelecionada = instanciasSaida.get(selectedRow);

            boolean flag = false;
            for (InstanciaProdutoEstoque instanciaEstoque : todasInstanciasEstoque) {
                if (instanciaEstoque.getIdProduto() == instanciaSelecionada.getIdProduto()) {
                    instanciaEstoque.setQuantidade(instanciaEstoque.getQuantidade() + instanciaSelecionada.getQuantidade());
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                AuxFunctions.popup(
                        null,
                        "Erro",
                        "Houve algum erro na remoção da tabela. Favor contactar o Matheus.",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            instanciasSaida.remove(instanciaSelecionada);

            preencherTabelas();

        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um produto na tabela à direita.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoRemoverProdutoActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        String searchParam = campoBusca.getText().toUpperCase();
        ArrayList<InstanciaProdutoEstoque> instanciasBuscadas = new ArrayList<>();

        for (InstanciaProdutoEstoque instancia : todasInstanciasEstoque) {
            if (instancia.getProduto().getDescricao().contains(searchParam)) {
                instanciasBuscadas.add(instancia);
            }
        }

        instanciasNaTabela = instanciasBuscadas.toArray(new InstanciaProdutoEstoque[0]);
        preencherTabelas();
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (SaidaValidator.validate(formPanel) && AuxFunctions.popupConfirmacao("Cadastro de saída", "Realmente deseja realizar o cadastro?")) {
            int idEstoqueOrigem = todosEstoques[comboBoxEstoqueOrigem.getSelectedIndex()].getIdEstoque();
            SaidaProdutos novaSaida = new SaidaProdutos(
                    idEstoqueOrigem,
                    new Date(System.currentTimeMillis()),
                    radioButtonSim.isSelected() ? 1 : 0
            );

            if (radioButtonSim.isSelected()) {
                float valor;
                for (int i = 0; i < tabelaInstanciasMovimentacao.getRowCount(); i++) {
                    valor = AuxFunctions.valorStringParaFloat(tabelaInstanciasMovimentacao.getValueAt(i, 4).toString());
                    instanciasSaida.get(i).setValorUnitarioVenda(valor);
                }
            }

            if (SaidaProdutosDAO.insertSaidaProdutos(novaSaida, instanciasSaida.toArray(new InstanciaProdutoMovimentacao[0]))) {
                AuxFunctions.popup(
                        this,
                        "Cadastro de saída",
                        "O cadastro foi realizado com sucesso.",
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
                        "Houve algum erro ao cadastrar a saída no banco de dados.\n"
                        + "Favor reiniciar o programa e tentar novamente.",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCalcularValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularValorTotalActionPerformed
        if (SaidaValidator.validarTabela(tabelaInstanciasMovimentacao, radioButtonSim)) {
            float valorTotal = 0;
            String valor;

            for (int i = 0; i < tabelaInstanciasMovimentacao.getRowCount(); i++) {
                valor = tabelaInstanciasMovimentacao.getValueAt(i, 4).toString();
                valorTotal += AuxFunctions.valorStringParaFloat(valor) * instanciasSaida.get(i).getQuantidade();
            }

            campoValorTotal.setText("R$ " + AuxFunctions.valorFloatParaString(valorTotal));
        }
    }//GEN-LAST:event_botaoCalcularValorTotalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCalcularValorTotal;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoRemoverProduto;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.ButtonGroup categoriaButtonGroup;
    private javax.swing.JComboBox<String> comboBoxEstoqueOrigem;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JRadioButton radioButtonNao;
    private javax.swing.JRadioButton radioButtonSim;
    private javax.swing.JTable tabelaInstanciasCadastradas;
    private javax.swing.JTable tabelaInstanciasMovimentacao;
    // End of variables declaration//GEN-END:variables
}
