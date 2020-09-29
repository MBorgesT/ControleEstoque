package gui.movimentacoes.entre_estoques;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.InstanciaProdutoEstoqueDAO;
import dao.MovimentacaoEntreEstoquesDAO;
import gui.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Estoque;
import models.InstanciaProdutoEstoque;
import models.InstanciaProdutoMovimentacao;
import models.MovimentacaoEntreEstoques;

public class NovaMovimentacaoEntreEstoques extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;

    private Estoque[] todosEstoques;
    private Estoque estoqueOrigemSelecionado;

    private InstanciaProdutoEstoque[] todasInstanciasEstoque;
    private InstanciaProdutoEstoque[] instanciasNaTabela;
    private ArrayList<InstanciaProdutoMovimentacao> instanciasMovimentacao;

    public NovaMovimentacaoEntreEstoques(MenuPrincipal menuPrincipal) {
        initComponents();

        this.menuPrincipal = menuPrincipal;

        this.instanciasMovimentacao = new ArrayList<>();

        setComboBoxOrigemListener();

        preencherComboBoxes();

        tabelaInstanciasCadastradas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabelaInstanciasMovimentacao.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void setComboBoxOrigemListener() {
        comboBoxOrigem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedIndex = comboBoxOrigem.getSelectedIndex();
                if (selectedIndex >= 0) {
                    estoqueOrigemSelecionado = todosEstoques[selectedIndex];
                    int idEstoque = estoqueOrigemSelecionado.getIdEstoque();

                    todasInstanciasEstoque = InstanciaProdutoEstoqueDAO.selectInstanciasProdutoEstoquePorEstoque(idEstoque, false);
                    instanciasNaTabela = todasInstanciasEstoque;

                    instanciasMovimentacao = new ArrayList<>();

                    preencherTabelas();

                    campoBusca.setText("");
                    campoQuantidade.setText("");
                } else {
                    estoqueOrigemSelecionado = null;

                    todasInstanciasEstoque = new InstanciaProdutoEstoque[0];
                    instanciasNaTabela = todasInstanciasEstoque;

                    preencherTabelas();

                    campoBusca.setText("");
                    campoQuantidade.setText("");
                }
                /*
                else {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "Favor escolher uma opção válida.",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                 */
            }
        });
    }

    private void preencherComboBoxes() {
        this.todosEstoques = EstoqueDAO.selectTodosEstoques();

        comboBoxOrigem.removeAllItems();
        comboBoxDestino.removeAllItems();

        for (Estoque estoque : this.todosEstoques) {
            comboBoxOrigem.addItem(estoque.getDescricao());
            comboBoxDestino.addItem(estoque.getDescricao());
        }

        comboBoxOrigem.setSelectedIndex(-1);
        comboBoxDestino.setSelectedIndex(-1);
    }

    private void preencherTabelas() {
        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasCadastradas.getModel();
        model.setRowCount(0);
        for (InstanciaProdutoEstoque instancia : instanciasNaTabela) {
            model.addRow(instancia.getMovimentacaoEntreEstoquesTableRow());
        }

        model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
        model.setRowCount(0);
        for (InstanciaProdutoMovimentacao instancia : instanciasMovimentacao) {
            model.addRow(instancia.getEntreEstoquesTableRow());
        }
    }

    private boolean validarCadastro() {
        if (comboBoxOrigem.getSelectedIndex() == -1 || comboBoxDestino.getSelectedIndex() == -1) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar ambos os estoques de origem e destino da operação.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (comboBoxOrigem.getSelectedIndex() == comboBoxDestino.getSelectedIndex()) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "O estoque de origem não pode ser o mesmo que o de destino.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (instanciasMovimentacao.isEmpty()) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor preencher a tabela de produtos a serem movimentados.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxDestino = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaInstanciasCadastradas = new javax.swing.JTable();
        botaoAdicionarProduto = new javax.swing.JButton();
        botaoRemoverProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaInstanciasMovimentacao = new javax.swing.JTable();
        comboBoxOrigem = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoQuantidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        botaoBuscar = new javax.swing.JButton();
        campoBusca = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Movimentação Entre Estoques");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("produtos_48.png")).getImage());

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/up_arrow_48.png"))); // NOI18N
        jLabel1.setText("Nova Movimentação entre Estoques");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de destino:");

        comboBoxDestino.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxDestino.setName("comboBoxDestino"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Produtos no estoque de origem:");

        jScrollPane1.setName("scrollPaneInstanciasCadastradas"); // NOI18N

        tabelaInstanciasCadastradas.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaInstanciasCadastradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. embalagem", "Un. medida", "Qtd. estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasCadastradas.setName("tabelaInstanciasCadastradas"); // NOI18N
        jScrollPane1.setViewportView(tabelaInstanciasCadastradas);
        if (tabelaInstanciasCadastradas.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasCadastradas.getColumnModel().getColumn(0).setPreferredWidth(160);
            tabelaInstanciasCadastradas.getColumnModel().getColumn(3).setPreferredWidth(70);
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

        jScrollPane2.setName("scrollPaneInstanciasMovimentacao"); // NOI18N

        tabelaInstanciasMovimentacao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaInstanciasMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. embalagem", "Un. medida", "Qtd. saida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasMovimentacao.setName("tabelaInstanciasMovimentacao"); // NOI18N
        jScrollPane2.setViewportView(tabelaInstanciasMovimentacao);
        if (tabelaInstanciasMovimentacao.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(225);
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(3).setPreferredWidth(70);
        }

        comboBoxOrigem.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxOrigem.setName("comboBoxOrigem"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Estoque de origem:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Qtd.:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Produtos a serem transferidos:");

        botaoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        campoBusca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(125, 125, 125))
                                        .addComponent(comboBoxOrigem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(campoBusca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoBuscar)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(comboBoxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botaoAdicionarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botaoRemoverProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoQuantidade)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoBuscar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(botaoAdicionarProduto)
                            .addGap(80, 80, 80)
                            .addComponent(botaoRemoverProduto)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(0, 447, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            for (InstanciaProdutoMovimentacao i : instanciasMovimentacao) {
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
                                instanciasMovimentacao.add(novaInstancia);
                            }

                            instanciaDaTabela.setQuantidade(instanciaDaTabela.getQuantidade() - qtd);

                            /*
                            for (InstanciaProdutoEstoque i: todasInstanciasEstoque) { // para não dar problema com a função de busca
                                if (i.getIdInstanciaProduto() == instancia.getIdInstanciaProduto()) {
                                    i.setQuantidade(i.getQuantidade() - qtd);
                                    break;
                                }
                            }
                             */
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
                            "O valor de quantidade está incorreto",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } else {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Favor preencher o campo com a quantidade que deseja transferir.",
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

            InstanciaProdutoMovimentacao instanciaSelecionada = instanciasMovimentacao.get(selectedRow);

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

            instanciasMovimentacao.remove(instanciaSelecionada);

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

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (validarCadastro() && AuxFunctions.popupConfirmacao("Cadastro de movimentação", "Você realmente deseja realizar a movimentação?")) {
            int idEstoqueOrigem = todosEstoques[comboBoxOrigem.getSelectedIndex()].getIdEstoque();
            int idEstoqueDestino = todosEstoques[comboBoxDestino.getSelectedIndex()].getIdEstoque();

            MovimentacaoEntreEstoques novaMovimentacao = new MovimentacaoEntreEstoques(
                    idEstoqueOrigem,
                    idEstoqueDestino,
                    new Date(System.currentTimeMillis()),
                    2
            );

            InstanciaProdutoMovimentacao[] novasInstancias = instanciasMovimentacao.toArray(new InstanciaProdutoMovimentacao[0]);
            if (MovimentacaoEntreEstoquesDAO.insertMovimentacaoEntreEstoques(novaMovimentacao, novasInstancias)) {

                AuxFunctions.popup(
                        this,
                        "Cadastro de movimentação",
                        "Cadastro realizado com sucesso.",
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
                        "Houve algum erro ao cadastrar a movimentação no banco de dados.\nFavorReiniciar o programa e tentar novamente.",
                        JOptionPane.ERROR_MESSAGE
                );

            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoRemoverProduto;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JComboBox<String> comboBoxDestino;
    private javax.swing.JComboBox<String> comboBoxOrigem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelaInstanciasCadastradas;
    private javax.swing.JTable tabelaInstanciasMovimentacao;
    // End of variables declaration//GEN-END:variables

}
