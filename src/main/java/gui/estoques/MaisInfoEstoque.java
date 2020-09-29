package gui.estoques;

import aux_functions.AuxFunctions;
import dao.EstoqueDAO;
import dao.FornecedorDAO;
import gui.MenuPrincipal;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.Estoque;

public class MaisInfoEstoque extends javax.swing.JFrame {

    MenuPrincipal menuPrincipal;
    Estoque estoque;
    boolean editando;

    public MaisInfoEstoque(MenuPrincipal menuPrincipal, Estoque estoque) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.estoque = estoque;
        this.editando = false;

        preencherCampos();
    }

    private void preencherCampos() {
        campoDescricao.setText(estoque.getDescricao());
    }

    private void flipEdicaoInfo(boolean b) {
        campoDescricao.setEditable(b);

        botaoCancelarEdicao.setEnabled(b);
        botaoExcluir.setEnabled(!b);

        if (b) {
            botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/save_edit_36.png")));
            botaoEditarInfo.setText("Salvar Alterações");
        } else {
            botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/edit_36.png")));
            botaoEditarInfo.setText("Editar Informações");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoDescricao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        botaoEditarInfo = new javax.swing.JButton();
        botaoCancelarEdicao = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mais Informações do Estoque");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prateleira_48.png"))); // NOI18N
        jLabel1.setText("ID: 42");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Descrição:");

        campoDescricao.setEditable(false);
        campoDescricao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 47, 52));
        jLabel4.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        botaoEditarInfo.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoEditarInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit_36.png"))); // NOI18N
        botaoEditarInfo.setText("Editar Informações");
        botaoEditarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarInfoActionPerformed(evt);
            }
        });

        botaoCancelarEdicao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCancelarEdicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_36.png"))); // NOI18N
        botaoCancelarEdicao.setText("Cancelar Alterações");
        botaoCancelarEdicao.setEnabled(false);
        botaoCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarEdicaoActionPerformed(evt);
            }
        });

        botaoExcluir.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete_36.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 47, 52));
        jLabel3.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoCancelarEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(botaoEditarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoEditarInfo)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelarEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoExcluir))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void botaoCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarEdicaoActionPerformed
        flipEdicaoInfo(false);
        this.editando = false;
        preencherCampos();
    }//GEN-LAST:event_botaoCancelarEdicaoActionPerformed

    private void botaoEditarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarInfoActionPerformed
        if (this.editando) {
            if (!campoDescricao.getText().isEmpty()) {
                String descricao = campoDescricao.getText().toUpperCase();

                if (!EstoqueDAO.estoqueJaCadastradoComDescricao(descricao, estoque.getIdEstoque())) {

                    if (AuxFunctions.popupConfirmacao("Confirmação", "Realmente deseja atualizar a descrição?")) {
                        estoque.setDescricao(descricao);

                        if (EstoqueDAO.updateEstoque(estoque)) {

                            AuxFunctions.popup(
                                    this,
                                    "Atualização de dados",
                                    "Os dados foram atualizados com sucesso.",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            menuPrincipal.setArrayEstoquesTodosCadastrados();
                            menuPrincipal.preencherComboBoxEstoques();

                            preencherCampos();
                            flipEdicaoInfo(false);
                            this.editando = false;

                        } else {
                            AuxFunctions.popup(
                                    this,
                                    "Erro",
                                    "Houve algum erro ao atualizar os dados do estoque no banco de dados. Favor reiniciar o programa e tentar novamente.",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }

                } else {
                    AuxFunctions.popup(
                            this,
                            "Atualização de informações",
                            "Já existe um estoque cadastrado com essa descrição.",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } else {
                AuxFunctions.popup(
                        this,
                        "Atualização de informações",
                        "O campo de descrição não pode estar vazio.",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } else {
            this.editando = true;
            flipEdicaoInfo(true);
        }
    }//GEN-LAST:event_botaoEditarInfoActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (AuxFunctions.popupConfirmacao("Exclusão de estoque", "Realmente deseja excluir o estoque?")) {
            if (EstoqueDAO.podeExcluirEstoque(estoque)) {
                if (EstoqueDAO.deleteEstoque(estoque)) {
                    AuxFunctions.popup(
                            this,
                            "Exclusão de estoque",
                            "A exclusão do estoque foi realizada com sucesso.",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    menuPrincipal.setArrayEstoquesTodosCadastrados();
                    menuPrincipal.preencherComboBoxEstoques();
                    
                    this.dispose();
                } else {
                    AuxFunctions.popup(
                            this,
                            "Erro",
                            "Houve algum erro na exclusão do estoque no banco de dados. Favor reiniciar o programa e tentar novamente.",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Não é possível excluir o estoque pois existem compras cadastradas com ele.",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelarEdicao;
    private javax.swing.JButton botaoEditarInfo;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
