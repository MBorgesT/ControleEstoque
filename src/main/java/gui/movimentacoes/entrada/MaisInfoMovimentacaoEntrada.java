package gui.movimentacoes.entrada;

import aux_functions.AuxFunctions;
import dao.EntradaProdutosDAO;
import dao.InstanciaProdutoMovimentacaoDAO;
import dao.MovimentacaoDAO;
import gui.MenuPrincipal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.EntradaProdutos;
import models.InstanciaProdutoMovimentacao;
import models.Produto;
import org.apache.commons.lang3.ArrayUtils;

public class MaisInfoMovimentacaoEntrada extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;

    private EntradaProdutos entradaProdutos;
    
    private ArrayList<Produto> produtosEntrada;

    public MaisInfoMovimentacaoEntrada(MenuPrincipal menuPrincipal, int idMovimentacao) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.entradaProdutos = (EntradaProdutos) MovimentacaoDAO.selectMovimentacaoPorId(idMovimentacao);

        preencherCampos();

        tabelaProdutosEntrada.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void preencherCampos() {
        labelId.setText("ID: " + String.valueOf(entradaProdutos.getIdMovimentacao()));

        campoData.setText(AuxFunctions.formatData(entradaProdutos.getData()));
        campoCompra.setText(entradaProdutos.isCompra() ? "SIM" : "NÃO");
        campoFornecedor.setText(entradaProdutos.isCompra() ? entradaProdutos.getFornecedor().getDescricao() : "");
        campoEstoqueDestino.setText(entradaProdutos.getEstoqueDestino().getDescricao());

        if (entradaProdutos.isCompra()) {
            campoValorTotal.setText("R$ " + AuxFunctions.valorFloatParaString(entradaProdutos.getValorTotal()));
        } else {
            campoValorTotal.setVisible(false);
            labelValorTotal.setVisible(false);

            DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
            model.setColumnCount(4);
        }

        InstanciaProdutoMovimentacao[] instancias = InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(entradaProdutos.getIdMovimentacao(), entradaProdutos.getTipoMovimentacao());
        this.produtosEntrada = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
        model.setRowCount(0);

        for (InstanciaProdutoMovimentacao instancia : instancias) {
            Produto produto = instancia.getProduto();

            produtosEntrada.add(produto);
            Object[] produtoArray = produto.getCadastroEntradaTableRow();
            Object[] infoArray = null;
            if (entradaProdutos.isCompra()) {
                infoArray = new Object[]{instancia.getQuantidade(), instancia.getValorUnitarioPago()};
            } else {
                infoArray = new Object[]{instancia.getQuantidade()};
            }

            model.addRow(ArrayUtils.addAll(produtoArray, infoArray));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoriaButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutosEntrada = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        labelValorTotal = new javax.swing.JLabel();
        campoValorTotal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        campoCompra = new javax.swing.JTextField();
        campoFornecedor = new javax.swing.JTextField();
        campoEstoqueDestino = new javax.swing.JTextField();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Entrada");

        labelId.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        labelId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_48.png"))); // NOI18N
        labelId.setText("ID: 42");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Compra?");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Fornecedor:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de destino:");

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
                false, false, false, false, false
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

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Data:");

        campoData.setEditable(false);
        campoData.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        labelValorTotal.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelValorTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sigma_24.png"))); // NOI18N
        labelValorTotal.setText("Valor total:");

        campoValorTotal.setEditable(false);
        campoValorTotal.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoCompra.setEditable(false);
        campoCompra.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoFornecedor.setEditable(false);
        campoFornecedor.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoEstoqueDestino.setEditable(false);
        campoEstoqueDestino.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

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
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelValorTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(campoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                                .addComponent(campoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(75, 75, 75)))
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(campoEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelValorTotal)
                    .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelId)
                    .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoExcluir)
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

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (EntradaProdutosDAO.podeExcluir(entradaProdutos)) {
            if (AuxFunctions.popupConfirmacao("Exclusão", "Realmente deseja realizar a exclusão?")) {
                if (EntradaProdutosDAO.deleteEntradaProdutos(entradaProdutos)) {
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
                            "Houve algum erro ao excluir a movimentação no banco de dados.\nFavorReiniciar o programa e tentar novamente.",
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
    private javax.swing.JTextField campoCompra;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoEstoqueDestino;
    private javax.swing.JTextField campoFornecedor;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.ButtonGroup categoriaButtonGroup;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JTable tabelaProdutosEntrada;
    // End of variables declaration//GEN-END:variables
}
