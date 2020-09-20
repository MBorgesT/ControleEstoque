package gui.fornecedores;

import aux_functions.AuxFunctions;
import dao.FornecedorDAO;
import gui.MenuPrincipal;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.Fornecedor;
import validators.FornecedorValidator;

public class MaisInfoFornecedor extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;
    private Fornecedor fornecedor;
    private boolean editando;

    public MaisInfoFornecedor(MenuPrincipal menuPrincipal, Fornecedor fornecedor) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.fornecedor = fornecedor;
        this.editando = false;

        preencherCampos();
        flipEdicaoInfo(false);
    }

    private void preencherCampos() {
        campoDescricao.setText(fornecedor.getDescricao());
        campoTelefone1.setText(fornecedor.getTelefone1());
        campoTelefone2.setText(fornecedor.getTelefone2());
    }

    private void flipEdicaoInfo(boolean b) {
        campoDescricao.setEditable(b);
        campoTelefone1.setEditable(b);
        campoTelefone2.setEditable(b);

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

        jLabel1 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoTelefone1 = new javax.swing.JTextField();
        campoTelefone2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botaoEditarInfo = new javax.swing.JButton();
        botaoCancelarEdicao = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mais Informações de Fornecedor");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fornecedor_48.png"))); // NOI18N
        jLabel1.setText("ID: 42");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Nome/Descrição:");

        campoDescricao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoDescricao.setName("campoDescricao"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Telefone:");

        campoTelefone1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoTelefone1.setName("campoTelefone1"); // NOI18N

        campoTelefone2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoTelefone2.setName("campoTelefone2"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Telefone secundário:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 47, 52));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 47, 52));
        jLabel6.setText("*");

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(campoTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(campoDescricao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 47, 52));
        jLabel7.setText("* Campos obrigatórios");

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
        botaoCancelarEdicao.setText("Cancelar Edição");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoEditarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(botaoCancelarEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoEditarInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoCancelarEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoExcluir))
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarEdicaoActionPerformed
        this.editando = false;
        flipEdicaoInfo(false);
        preencherCampos();
    }//GEN-LAST:event_botaoCancelarEdicaoActionPerformed

    private void botaoEditarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarInfoActionPerformed
        if (editando) {
            if (FornecedorValidator.validate(formPanel)) {
                if (!FornecedorDAO.fornecedorJaCadastradoComDescricao(campoDescricao.getText().toUpperCase(), fornecedor.getIdFornecedor())) {
                    if (AuxFunctions.popupConfirmacao("Alteração de dados", "Você realmente deseja alterar os dados do fornecedor?")) {
                        fornecedor.setDescricao(campoDescricao.getText().toUpperCase());
                        fornecedor.setTelefone1(campoTelefone1.getText().toUpperCase());
                        fornecedor.setTelefone2(campoTelefone2.getText().toUpperCase());

                        if (FornecedorDAO.updateFornecedor(fornecedor)) {
                            AuxFunctions.popup(
                                    this,
                                    "Alteração de dados",
                                    "Alteração de dados realizada com sucesso.",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            this.editando = false;
                            flipEdicaoInfo(false);
                            preencherCampos();
                        } else {
                            AuxFunctions.popup(
                                    this,
                                    "Erro",
                                    "Ocorreu algum erro ao tentar atualizar os dados do fornecedor. Favor reiniciar o programa e tentar novamente.",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } else {
                    AuxFunctions.popup(
                            this,
                            "Atenção",
                            "Já existe um fornecedor com essa descrição cadastrado.",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        } else {
            this.editando = true;
            flipEdicaoInfo(true);
        }
    }//GEN-LAST:event_botaoEditarInfoActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (AuxFunctions.popupConfirmacao("Exclusão de fornecedor", "Realmente deseja excluir o fornecedor?")) {
            if (FornecedorDAO.podeExcluirFornecedor(fornecedor)) {
                if (FornecedorDAO.deleteFornecedor(fornecedor)) {
                    AuxFunctions.popup(
                            this,
                            "Exclusão de fornecedor",
                            "A exclusão do fornecedor foi realizada com sucesso.",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    menuPrincipal.setArrayFornecedoresTodosCadastrados();
                    menuPrincipal.preencherComboBoxFornecedores();
                    
                    this.dispose();
                } else {
                    AuxFunctions.popup(
                            this,
                            "Erro",
                            "Houve algum erro na exclusão do fornecedor no banco de dados. Favor reiniciar o programa e tentar novamente.",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Não é possível excluir o fonrecedor pois existem compras cadastradas com ele.",
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
    private javax.swing.JTextField campoTelefone1;
    private javax.swing.JTextField campoTelefone2;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
