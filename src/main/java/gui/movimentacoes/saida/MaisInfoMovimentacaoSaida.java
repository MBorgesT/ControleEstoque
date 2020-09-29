package gui.movimentacoes.saida;

import aux_functions.AuxFunctions;
import dao.MovimentacaoDAO;
import dao.SaidaProdutosDAO;
import gui.MenuPrincipal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.InstanciaProdutoMovimentacao;
import models.SaidaProdutos;

public class MaisInfoMovimentacaoSaida extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;
    private SaidaProdutos saidaProdutos;

    public MaisInfoMovimentacaoSaida(MenuPrincipal menuPrincipal, int idSaidaProdutos) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.saidaProdutos = (SaidaProdutos) MovimentacaoDAO.selectMovimentacaoPorId(idSaidaProdutos);

        preencherCampos();

        tabelaInstanciasMovimentacao.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void preencherCampos() {
        if (saidaProdutos.getTipoMovimentacao() == 0) {
            DefaultTableModel model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
            model.setColumnCount(4);
        }

        campoEstoqueOrigem.setText(saidaProdutos.getEstoqueOrigem().getDescricao());
        campoVenda.setText(saidaProdutos.getTipoMovimentacao() == 1 ? "SIM" : "NÃO");

        campoValorTotal.setText("R$ " + AuxFunctions.valorFloatParaString(saidaProdutos.getValorTotal()));

        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
        model.setRowCount(0);
        for (InstanciaProdutoMovimentacao instancia : saidaProdutos.getInstanciasProduto()) {
            model.addRow(instancia.getEntreEstoquesTableRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaInstanciasMovimentacao = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelValorTotal = new javax.swing.JLabel();
        campoValorTotal = new javax.swing.JTextField();
        campoEstoqueOrigem = new javax.swing.JTextField();
        campoVenda = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Saída de Estoque");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/out_arrow_48.png"))); // NOI18N
        jLabel1.setText("Nova Saída de Estoque");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Venda?");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de origem:");

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
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasMovimentacao.setEnabled(false);
        tabelaInstanciasMovimentacao.setName("tabelaInstanciasMovimentacao"); // NOI18N
        tabelaInstanciasMovimentacao.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelaInstanciasMovimentacao);
        if (tabelaInstanciasMovimentacao.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(225);
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(4).setPreferredWidth(90);
        }

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Produtos retirados do estoque:");

        labelValorTotal.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelValorTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sigma_24.png"))); // NOI18N
        labelValorTotal.setText("Valor total:");

        campoValorTotal.setEditable(false);
        campoValorTotal.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoEstoqueOrigem.setEditable(false);
        campoEstoqueOrigem.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoVenda.setEditable(false);
        campoVenda.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(labelValorTotal)
                        .addGap(12, 12, 12)
                        .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(campoEstoqueOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEstoqueOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(24, 24, 24)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorTotal)
                    .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        botaoCancelar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete_36.png"))); // NOI18N
        botaoCancelar.setText("Excluir");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoCancelar)
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

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        if (AuxFunctions.popupConfirmacao("Exclusão", "Realmente deseja excluir essa movimentação?")) {
            if (SaidaProdutosDAO.deleteSaidaProdutos(saidaProdutos)) {
                AuxFunctions.popup(
                        this,
                        "Exclusão",
                        "Exclusão realizada com sucesso.",
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
                        "Houve algum erro com o banco de dados na exclusão.\n"
                                + "Favor reiniciar o programa e tentar novamente.",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_botaoCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoEstoqueOrigem;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.JTextField campoVenda;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JTable tabelaInstanciasMovimentacao;
    // End of variables declaration//GEN-END:variables
}
