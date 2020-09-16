package gui.produtos;

import dao.ProdutoDAO;
import gui.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Produto;
import aux_functions.AuxFunctions;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.Ingrediente;
import validators.ProdutoValidator;

public class NovoProduto extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;
    private Produto[] todosProdutosCadastrados, produtosNaTabelaProdutosCadastrados;
    private ArrayList<Produto> ingredientesNaTabela;

    public NovoProduto(MenuPrincipal menuPrincipal) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.todosProdutosCadastrados = ProdutoDAO.selectTodosProdutos();
        this.ingredientesNaTabela = new ArrayList<>();

        setRadioButtonsActionListeners();
        flipProduzidoPadaria(false);

        tabelaIngredientes.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void setRadioButtonsActionListeners() {
        radioButtonSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                flipProduzidoPadaria(radioButtonSim.isSelected());
            }
        });

        radioButtonNao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                flipProduzidoPadaria(!radioButtonNao.isSelected());
            }
        });
    }

    private void flipProduzidoPadaria(boolean b) {
        campoBuscaProdutos.setEnabled(b);
        botaoBuscarProdutos.setEnabled(b);

        tabelaProdutosCadastrados.setEnabled(b);
        tabelaIngredientes.setEnabled(b);

        botaoAdicionarIngrediente.setEnabled(b);
        botaoRemoverIngrediente.setEnabled(b);
        
        campoTotalIngredientes.setEnabled(b);
        botaoCalcularValorIngredientes.setEnabled(b);

        DefaultTableModel produtosCadastradosModel = (DefaultTableModel) tabelaProdutosCadastrados.getModel();
        if (b) {
            this.produtosNaTabelaProdutosCadastrados = this.todosProdutosCadastrados;
            ingredientesNaTabela = new ArrayList<>();

            for (Produto p : this.todosProdutosCadastrados) {
                produtosCadastradosModel.addRow(p.getCadastroProdutosTableRow());
            }
        } else {
            produtosCadastradosModel.setRowCount(0);

            DefaultTableModel ingredientesModel = (DefaultTableModel) tabelaIngredientes.getModel();
            ingredientesModel.setRowCount(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produzidoPadariaButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoDescricao = new javax.swing.JTextField();
        campoUnidadeMedida = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoQuantidadeEmbalagem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        radioButtonSim = new javax.swing.JRadioButton();
        radioButtonNao = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutosCadastrados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaIngredientes = new javax.swing.JTable();
        campoBuscaProdutos = new javax.swing.JTextField();
        botaoBuscarProdutos = new javax.swing.JButton();
        botaoAdicionarIngrediente = new javax.swing.JButton();
        botaoRemoverIngrediente = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        campoValorPago = new javax.swing.JTextField();
        campoPorcentagemLucro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoValorSugeridoVenda = new javax.swing.JTextField();
        campoValorVenda = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        campoTotalIngredientes = new javax.swing.JTextField();
        botaoCalcularValorIngredientes = new javax.swing.JButton();
        botaoCalcularValorSugerido = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Produto");
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(654, 720));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novo_produto_48.png"))); // NOI18N
        jLabel1.setText("Novo Produto");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Descrição:");

        campoDescricao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoDescricao.setName("campoDescricao"); // NOI18N

        campoUnidadeMedida.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoUnidadeMedida.setName("campoUnidadeMedida"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("<html>Unidade de medida:</html>");

        campoQuantidadeEmbalagem.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoQuantidadeEmbalagem.setName("campoQuantidadeEmbalagem"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("<html>Quantidade na embalagem:</html>");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Produzido na Padaria?");

        produzidoPadariaButtonGroup.add(radioButtonSim);
        radioButtonSim.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonSim.setText("Sim");
        radioButtonSim.setName("radioButtonSim"); // NOI18N

        produzidoPadariaButtonGroup.add(radioButtonNao);
        radioButtonNao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonNao.setText("Não");
        radioButtonNao.setName("radioButtonNao"); // NOI18N

        jScrollPane1.setName("scrollPaneProdutosCadastrados"); // NOI18N

        tabelaProdutosCadastrados.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaProdutosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "U. de Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutosCadastrados.setName("tabelaProdutosCadastrados"); // NOI18N
        jScrollPane1.setViewportView(tabelaProdutosCadastrados);
        if (tabelaProdutosCadastrados.getColumnModel().getColumnCount() > 0) {
            tabelaProdutosCadastrados.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabelaProdutosCadastrados.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Produtos Cadastrados:");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setText("Ingredientes:");

        jScrollPane2.setName("scrollPaneIngredientes"); // NOI18N

        tabelaIngredientes.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "U. de Medida", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaIngredientes.setName("tabelaIngredientes"); // NOI18N
        jScrollPane2.setViewportView(tabelaIngredientes);
        if (tabelaIngredientes.getColumnModel().getColumnCount() > 0) {
            tabelaIngredientes.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabelaIngredientes.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaIngredientes.getColumnModel().getColumn(2).setPreferredWidth(70);
        }

        campoBuscaProdutos.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoBuscarProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        botaoBuscarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarProdutosActionPerformed(evt);
            }
        });

        botaoAdicionarIngrediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_24.png"))); // NOI18N
        botaoAdicionarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarIngredienteActionPerformed(evt);
            }
        });

        botaoRemoverIngrediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close_24.png"))); // NOI18N
        botaoRemoverIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverIngredienteActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 47, 52));
        jLabel14.setText("*");

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 47, 52));
        jLabel15.setText("*");

        jLabel16.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 47, 52));
        jLabel16.setText("*");

        jLabel17.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 47, 52));
        jLabel17.setText("*");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Valor pago:");

        campoValorPago.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorPago.setName("campoValorPago"); // NOI18N

        campoPorcentagemLucro.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("% de Lucro:");

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("<html>Valor de venda sugerido:</html>");

        campoValorSugeridoVenda.setEditable(false);
        campoValorSugeridoVenda.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        campoValorVenda.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorVenda.setName("campoValorVenda"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setText("Valor de venda:");

        jLabel13.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel13.setText("Total (R$):");

        campoTotalIngredientes.setEditable(false);
        campoTotalIngredientes.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoCalcularValorIngredientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculadora_24.png"))); // NOI18N
        botaoCalcularValorIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularValorIngredientesActionPerformed(evt);
            }
        });

        botaoCalcularValorSugerido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculadora_24.png"))); // NOI18N
        botaoCalcularValorSugerido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularValorSugeridoActionPerformed(evt);
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
                    .addComponent(jSeparator2)
                    .addComponent(campoDescricao)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(campoUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(campoQuantidadeEmbalagem, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(radioButtonSim)
                                .addGap(18, 18, 18)
                                .addComponent(radioButtonNao))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16))))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addComponent(campoBuscaProdutos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscarProdutos))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoAdicionarIngrediente)
                            .addComponent(botaoRemoverIngrediente))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTotalIngredientes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoCalcularValorIngredientes))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(campoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(campoPorcentagemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoValorSugeridoVenda)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCalcularValorSugerido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoQuantidadeEmbalagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButtonSim)
                            .addComponent(radioButtonNao))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(campoBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(botaoCalcularValorIngredientes))
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(campoTotalIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(botaoBuscarProdutos)))
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(botaoAdicionarIngrediente)
                        .addGap(86, 86, 86)
                        .addComponent(botaoRemoverIngrediente)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoValorSugeridoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPorcentagemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoCalcularValorSugerido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 47, 52));
        jLabel11.setText("* Campos obrigatórios");

        botaoCadastrar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ok_36.png"))); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoCancelar))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoBuscarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarProdutosActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabelaProdutosCadastrados.getModel();

        String searchParam = campoBuscaProdutos.getText().toUpperCase();

        model.setRowCount(0);
        if (!searchParam.isEmpty()) {
            ArrayList<Produto> novosProdutosTabela = new ArrayList<>();

            for (Produto p : this.todosProdutosCadastrados) {
                if (p.getDescricao().contains(searchParam)) {
                    novosProdutosTabela.add(p);
                    model.addRow(p.getCadastroProdutosTableRow());
                }
            }

            this.produtosNaTabelaProdutosCadastrados = novosProdutosTabela.toArray(new Produto[0]);
        } else {
            this.produtosNaTabelaProdutosCadastrados = this.todosProdutosCadastrados;

            for (Produto p : this.todosProdutosCadastrados) {
                model.addRow(p.getCadastroProdutosTableRow());
            }
        }
    }//GEN-LAST:event_botaoBuscarProdutosActionPerformed

    private void botaoAdicionarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarIngredienteActionPerformed
        int selectedRow = tabelaProdutosCadastrados.getSelectedRow();
        if (selectedRow < 0) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor seleciona um produto na tabela de produtos cadastrados.",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            Produto p = this.produtosNaTabelaProdutosCadastrados[selectedRow];

            boolean flag = true;
            for (Produto produto : ingredientesNaTabela) {
                if (produto.getIdProduto() == p.getIdProduto()) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ingredientesNaTabela.add(p);

                DefaultTableModel model = (DefaultTableModel) tabelaIngredientes.getModel();
                model.addRow(p.getCadastroProdutosTableRow());
            } else {
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Este produto já consta nos ingredientes",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_botaoAdicionarIngredienteActionPerformed

    private void botaoRemoverIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverIngredienteActionPerformed
        int selectedRow = tabelaIngredientes.getSelectedRow();
        if (selectedRow < 0) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor seleciona um produto na tabela de ingredientes.",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            ingredientesNaTabela.remove(selectedRow);

            DefaultTableModel model = (DefaultTableModel) tabelaIngredientes.getModel();
            model.removeRow(selectedRow);
        }
    }//GEN-LAST:event_botaoRemoverIngredienteActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (ProdutoValidator.validate(formPanel)) {
            String[] options = {"SIM", "NÃO"};
            int reply;
            if (radioButtonSim.isSelected() && tabelaIngredientes.getRowCount() == 0) {
                reply = JOptionPane.showOptionDialog(null, "Este produto consta que é produzido na padaria e não possui nenhum ingrediente cadastrado. Deseja realizar o cadastro mesmo assim?", "Cadastro de Produto",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                        options, options[0]);
            } else {
                reply = JOptionPane.showOptionDialog(null, "Realmente deseja realizar o cadastro?", "Cadastro de produto",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                        options, options[0]);
            }

            if (reply == 0) {
                Produto novoProduto = new Produto(
                        campoDescricao.getText().toUpperCase(),
                        campoValorPago.getText().isEmpty() ? 0 : AuxFunctions.valorStringParaFloat(campoValorPago.getText()),
                        campoValorVenda.getText().isEmpty() ? 0 : AuxFunctions.valorStringParaFloat(campoValorVenda.getText()),
                        campoUnidadeMedida.getText().toUpperCase(),
                        AuxFunctions.valorStringParaFloat(campoQuantidadeEmbalagem.getText()),
                        radioButtonSim.isSelected()
                );

                boolean flag;
                if (radioButtonSim.isSelected() && tabelaIngredientes.getRowCount() > 0) {
                    Ingrediente[] novosIngredientes = new Ingrediente[tabelaIngredientes.getRowCount()];
                    for (int i = 0; i < tabelaIngredientes.getRowCount(); i++) {
                        novosIngredientes[i] = new Ingrediente(
                                ingredientesNaTabela.get(i).getIdProduto(),
                                AuxFunctions.valorStringParaFloat((String) tabelaIngredientes.getValueAt(i, 2))
                        );
                    }

                    flag = ProdutoDAO.insertProduto(novoProduto, novosIngredientes);
                } else {
                    flag = ProdutoDAO.insertProduto(novoProduto);
                }

                if (flag) {
                    AuxFunctions.popup(
                            this,
                            "Cadastro de produto", "Produto cadastrado com sucesso.",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    menuPrincipal.updateTodosProdutosCadastrados();
                    menuPrincipal.preencherTabelaProdutosComTodosCadastrados();

                    this.dispose();
                } else {
                    AuxFunctions.popup(
                            this,
                            "Cadastro de produto",
                            "Algum erro ocorreu ao cadastrar o produto. Favor reiniciar o programa e tentar novamente.",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoCalcularValorIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularValorIngredientesActionPerformed
        Produto ingrediente;
        float valorTotal = 0, quantidadeRelativa;
        boolean flag = true;

        for (int i = 0; i < tabelaIngredientes.getRowCount(); i++) {
            ingrediente = ingredientesNaTabela.get(i);

            try {
                quantidadeRelativa = AuxFunctions.valorStringParaFloat(tabelaIngredientes.getValueAt(i, 2).toString());

                valorTotal += (quantidadeRelativa / ingrediente.getQuantidadeNaEmbalagem()) * ingrediente.getValorUnitarioPago();
            } catch (NumberFormatException e) {
                AuxFunctions.popup(this, "Atenção", "Algum valor na tabela de ingredientes está incorreto", JOptionPane.WARNING_MESSAGE);
                flag = false;
                break;
            }

            if (ingrediente.getValorUnitarioPago() == 0) {
                AuxFunctions.popup(this, "Atenção", "Algum produto na tabela não tem seu valor pago definido.", JOptionPane.WARNING_MESSAGE);
                flag = false;
                break;
            }
        }

        if (flag) {
            campoTotalIngredientes.setText("R$ " + AuxFunctions.valorFloatParaString(valorTotal));
        } else {
            campoTotalIngredientes.setText("");
        }
    }//GEN-LAST:event_botaoCalcularValorIngredientesActionPerformed

    private void botaoCalcularValorSugeridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularValorSugeridoActionPerformed
        try {
            float porcentagemLucro = AuxFunctions.valorStringParaFloat(campoPorcentagemLucro.getText());
            float valorPago = AuxFunctions.valorStringParaFloat(campoValorPago.getText());

            if (campoValorPago.getText().isEmpty()) {
                campoValorSugeridoVenda.setText("");
            } else {
                campoValorSugeridoVenda.setText("R$ " + AuxFunctions.valorFloatParaString(valorPago + (valorPago * (porcentagemLucro / 100))));
            }
        } catch (NumberFormatException e) {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "O valor no campo de porcentagem de lucro e/ou de valor pago está incorreto",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoCalcularValorSugeridoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarIngrediente;
    private javax.swing.JButton botaoBuscarProdutos;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCalcularValorIngredientes;
    private javax.swing.JButton botaoCalcularValorSugerido;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoRemoverIngrediente;
    private javax.swing.JTextField campoBuscaProdutos;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JTextField campoPorcentagemLucro;
    private javax.swing.JTextField campoQuantidadeEmbalagem;
    private javax.swing.JTextField campoTotalIngredientes;
    private javax.swing.JTextField campoUnidadeMedida;
    private javax.swing.JTextField campoValorPago;
    private javax.swing.JTextField campoValorSugeridoVenda;
    private javax.swing.JTextField campoValorVenda;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.ButtonGroup produzidoPadariaButtonGroup;
    private javax.swing.JRadioButton radioButtonNao;
    private javax.swing.JRadioButton radioButtonSim;
    private javax.swing.JTable tabelaIngredientes;
    private javax.swing.JTable tabelaProdutosCadastrados;
    // End of variables declaration//GEN-END:variables
}
