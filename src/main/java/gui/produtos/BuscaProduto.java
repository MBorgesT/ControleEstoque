package gui.produtos;

import aux_functions.AuxFunctions;
import dao.ProdutoDAO;
import gui.MenuPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import models.Produto;

public class BuscaProduto extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;

    public BuscaProduto(MenuPrincipal menuPrincipal) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
    }

    private boolean validarCampos() {
        if (checkBoxId.isSelected()) {

            if (campoId.getText().isEmpty()) {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "A opção de ID está selecionada e o campo está vazio.",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }

            try {
                Integer.parseInt(campoId.getText());
            } catch (NumberFormatException e) {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "O valor de ID está incorreto.",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
        }

        if (checkBoxDescricao.isSelected() && campoDescricao.getText().isEmpty()) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "A opção de descrição está selecionada e o campo está vazio.",
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
        campoId = new javax.swing.JTextField();
        campoDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        checkBoxProduzidoNaPadariaSim = new javax.swing.JCheckBox();
        checkBoxProduzidoNaPadariaNao = new javax.swing.JCheckBox();
        checkBoxId = new javax.swing.JCheckBox();
        checkBoxDescricao = new javax.swing.JCheckBox();
        botaoBuscar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busca por Produto");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("produtos_48.png")).getImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_36.png"))); // NOI18N
        jLabel1.setText("Busca por Produto");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        campoId.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoDescricao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Produzido na Padaria?");

        checkBoxProduzidoNaPadariaSim.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        checkBoxProduzidoNaPadariaSim.setSelected(true);
        checkBoxProduzidoNaPadariaSim.setText("Sim");

        checkBoxProduzidoNaPadariaNao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        checkBoxProduzidoNaPadariaNao.setSelected(true);
        checkBoxProduzidoNaPadariaNao.setText("Não");

        checkBoxId.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        checkBoxId.setText("ID");

        checkBoxDescricao.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        checkBoxDescricao.setText("Descrição");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoDescricao)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkBoxId, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(checkBoxProduzidoNaPadariaSim)
                                    .addGap(18, 18, 18)
                                    .addComponent(checkBoxProduzidoNaPadariaNao)))))
                    .addComponent(checkBoxDescricao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(checkBoxId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxProduzidoNaPadariaSim)
                    .addComponent(checkBoxProduzidoNaPadariaNao))
                .addGap(18, 18, 18)
                .addComponent(checkBoxDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoBuscar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ok_36.png"))); // NOI18N
        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        botaoCancelar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel_36.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(botaoCancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoBuscar))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoBuscar))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if (validarCampos()) {
            Produto[] todosProdutosCadastrados = ProdutoDAO.selectTodosProdutos();
            ArrayList<Produto> novosProdutosTabela = new ArrayList();
            boolean flag;

            for (Produto p : todosProdutosCadastrados) {
                flag = true;
                
                if (checkBoxId.isSelected() && p.getIdProduto() != Integer.parseInt(campoId.getText())) {
                    flag = false;
                }

                if (checkBoxDescricao.isSelected() && !p.getDescricao().contains(campoDescricao.getText().toUpperCase())) {
                    flag = false;
                }

                if (!checkBoxProduzidoNaPadariaSim.isSelected() && p.isProduzidoNaPadaria()) {
                    flag = false;
                }

                if (!checkBoxProduzidoNaPadariaNao.isSelected() && !p.isProduzidoNaPadaria()) {
                    flag = false;
                }
                
                if (flag){
                    novosProdutosTabela.add(p);
                }
            }

            menuPrincipal.updateArrayProdutos(novosProdutosTabela.toArray(new Produto[0]));
            menuPrincipal.preencherTabelaProdutos();

            this.dispose();
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JTextField campoId;
    private javax.swing.JCheckBox checkBoxDescricao;
    private javax.swing.JCheckBox checkBoxId;
    private javax.swing.JCheckBox checkBoxProduzidoNaPadariaNao;
    private javax.swing.JCheckBox checkBoxProduzidoNaPadariaSim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
