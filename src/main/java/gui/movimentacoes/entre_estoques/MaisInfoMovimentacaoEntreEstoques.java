package gui.movimentacoes.entre_estoques;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.InstanciaProdutoMovimentacaoDAO;
import dao.MovimentacaoDAO;
import dao.MovimentacaoEntreEstoquesDAO;
import gui.MenuPrincipal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Estoque;
import models.InstanciaProdutoMovimentacao;
import models.MovimentacaoEntreEstoques;

public class MaisInfoMovimentacaoEntreEstoques extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;
    private MovimentacaoEntreEstoques movimentacao;

    public MaisInfoMovimentacaoEntreEstoques(MenuPrincipal menuPrincipal, int idMovimentacao) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.movimentacao = (MovimentacaoEntreEstoques) MovimentacaoDAO.selectMovimentacaoPorId(idMovimentacao);

        preencherCampos();

        tabelaInstanciasMovimentacao.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void preencherCampos() {
        labelId.setText("ID: " + movimentacao.getIdMovimentacao());

        campoEstoqueOrigem.setText(EstoqueDAO.selectEstoquePorId(movimentacao.getIdEstoqueOrigem()).getDescricao());
        campoEstoqueDestino.setText(EstoqueDAO.selectEstoquePorId(movimentacao.getIdEstoqueDestino()).getDescricao());
        campoData.setText(AuxFunctions.formatData(movimentacao.getData()));

        DefaultTableModel model = (DefaultTableModel) tabelaInstanciasMovimentacao.getModel();
        model.setRowCount(0);
        for (InstanciaProdutoMovimentacao instancia : InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(movimentacao.getIdMovimentacao(), movimentacao.getTipoMovimentacao())) {
            model.addRow(instancia.getEntreEstoquesTableRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaInstanciasMovimentacao = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        campoEstoqueOrigem = new javax.swing.JTextField();
        campoEstoqueDestino = new javax.swing.JTextField();
        campoData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Movimentação Entre Estoques");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("produtos_48.png")).getImage());

        labelId.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        labelId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/up_arrow_48.png"))); // NOI18N
        labelId.setText("ID: 42");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de destino:");

        jScrollPane2.setName("scrollPaneInstanciasMovimentacao"); // NOI18N

        tabelaInstanciasMovimentacao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaInstanciasMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaInstanciasMovimentacao.setName("tabelaInstanciasMovimentacao"); // NOI18N
        jScrollPane2.setViewportView(tabelaInstanciasMovimentacao);
        if (tabelaInstanciasMovimentacao.getColumnModel().getColumnCount() > 0) {
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(225);
            tabelaInstanciasMovimentacao.getColumnModel().getColumn(1).setPreferredWidth(70);
        }

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Estoque de origem:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Produtos transferidos:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        campoEstoqueOrigem.setEditable(false);
        campoEstoqueOrigem.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoEstoqueDestino.setEditable(false);
        campoEstoqueDestino.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoData.setEditable(false);
        campoData.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Data:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(campoEstoqueOrigem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(campoEstoqueDestino, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(campoData))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 134, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoEstoqueOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoExcluir.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete_36.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelId)
                            .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelId)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoExcluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (MovimentacaoEntreEstoquesDAO.podeExcluir(movimentacao)) {
            if (AuxFunctions.popupConfirmacao("Exclusão", "Realmente deseja excluir a movimentação?")) {
                if (MovimentacaoEntreEstoquesDAO.deleteMovimentacaoEntreEstoques(movimentacao)) {
                    AuxFunctions.popup(
                            this,
                            "Exclusão",
                            "Exclusão efetuada com sucesso.",
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
                            "Houve algum erro na exclusão da movimentação. Favor reiniciar o programa e tentar novamente.",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "A exclusão não pode ser realizada pois o estoque de destino não possui mais essas quantidades de produtos nele.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoEstoqueDestino;
    private javax.swing.JTextField campoEstoqueOrigem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelId;
    private javax.swing.JTable tabelaInstanciasMovimentacao;
    // End of variables declaration//GEN-END:variables

}
