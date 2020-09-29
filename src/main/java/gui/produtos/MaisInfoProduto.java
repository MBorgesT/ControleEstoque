package gui.produtos;

import aux_functions.AuxFunctions;
import dao.ProdutoDAO;
import gui.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Ingrediente;
import models.Produto;
import validators.ProdutoValidator;

public class MaisInfoProduto extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;
    private Produto produto;

    private Produto[] todosProdutosCadastrados, produtosNaTabelaProdutosCadastrados;
    private ArrayList<Ingrediente> ingredientesNaTabela;

    private boolean editando;

    public MaisInfoProduto(MenuPrincipal menuPrincipal, int idProduto) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.produto = ProdutoDAO.selectProdutoPorId(idProduto);
        this.todosProdutosCadastrados = ProdutoDAO.selectTodosProdutos();
        this.ingredientesNaTabela = new ArrayList<>();
        this.editando = false;

        flipEdicaoInfo(false);
        preencherCampos();
        setRadioButtonsActionListeners();

        tabelaProdutosCadastrados.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabelaIngredientes.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void preencherCampos() {
        labelId.setText("ID: " + String.valueOf(produto.getIdProduto()));

        campoDescricao.setText(produto.getDescricao());
        campoUnidadeMedida.setText(produto.getUnidadeDeMedida());
        campoQuantidadeEmbalagem.setText(AuxFunctions.valorFloatParaString(produto.getQuantidadeNaEmbalagem()));

        if (produto.isProduzidoNaPadaria()) {
            radioButtonSim.setSelected(true);
            preencherTabelaIngredientes();
        } else {
            radioButtonNao.setSelected(true);
        }

        campoValorPago.setText(AuxFunctions.valorFloatParaString(produto.getValorUnitarioPago()));
        campoValorVenda.setText(AuxFunctions.valorFloatParaString(produto.getValorUnitarioVenda()));
    }

    private void preencherTabelaProdutosCadastrados() {
        DefaultTableModel produtosCadastradosModel = (DefaultTableModel) tabelaProdutosCadastrados.getModel();
        produtosCadastradosModel.setRowCount(0);

        this.produtosNaTabelaProdutosCadastrados = this.todosProdutosCadastrados;

        for (Produto p : this.todosProdutosCadastrados) {
            if (p.getIdProduto() != produto.getIdProduto()) {
                produtosCadastradosModel.addRow(p.getCadastroProdutosTableRow());
            }
        }
    }

    private void preencherTabelaIngredientes() {
        DefaultTableModel ingredientesModel = (DefaultTableModel) tabelaIngredientes.getModel();
        ingredientesModel.setRowCount(0);
        this.ingredientesNaTabela = new ArrayList<>(Arrays.asList(ProdutoDAO.selectTodosIngredientesDeProduto(produto.getIdProduto())));

        for (Ingrediente ingrediente : this.ingredientesNaTabela) {
            ingredientesModel.addRow(ingrediente.getIngredientesTableRow());
        }
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

        if (b) {
            preencherTabelaProdutosCadastrados();
            preencherTabelaIngredientes();
        } else {
            DefaultTableModel produtosCadastradosModel = (DefaultTableModel) tabelaProdutosCadastrados.getModel();
            produtosCadastradosModel.setRowCount(0);

            DefaultTableModel ingredientesModel = (DefaultTableModel) tabelaIngredientes.getModel();
            ingredientesModel.setRowCount(0);
        }
    }

    private void flipEdicaoInfo(boolean b) {
        campoDescricao.setEditable(b);
        campoUnidadeMedida.setEditable(b);
        campoQuantidadeEmbalagem.setEditable(b);

        radioButtonSim.setEnabled(b);
        radioButtonNao.setEnabled(b);

        if (b && radioButtonSim.isSelected()) {
            campoBuscaProdutos.setEnabled(b);
            botaoBuscarProdutos.setEnabled(b);

            tabelaProdutosCadastrados.setEnabled(b);
            tabelaIngredientes.setEnabled(b);

            botaoAdicionarIngrediente.setEnabled(b);
            botaoRemoverIngrediente.setEnabled(b);

            campoTotalIngredientes.setEnabled(b);
            botaoCalcularValorIngredientes.setEnabled(b);

            preencherTabelaProdutosCadastrados();
        } else {
            campoBuscaProdutos.setEnabled(false);
            botaoBuscarProdutos.setEnabled(false);

            tabelaProdutosCadastrados.setEnabled(false);
            tabelaIngredientes.setEnabled(false);

            botaoAdicionarIngrediente.setEnabled(false);
            botaoRemoverIngrediente.setEnabled(false);

            campoTotalIngredientes.setEnabled(false);
            botaoCalcularValorIngredientes.setEnabled(false);

            DefaultTableModel produtosCadastradosModel = (DefaultTableModel) tabelaProdutosCadastrados.getModel();
            produtosCadastradosModel.setRowCount(0);
        }

        campoValorPago.setEditable(b);
        campoPorcentagemLucro.setEditable(b);
        campoValorVenda.setEditable(b);

        botaoCalcularValorSugerido.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produzidoPadariaButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
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
        scrollPaneProdutosCadastrados = new javax.swing.JScrollPane();
        tabelaProdutosCadastrados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        scrollPaneIngredientes = new javax.swing.JScrollPane();
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
        jLabel9 = new javax.swing.JLabel();
        campoValorPago = new javax.swing.JTextField();
        campoPorcentagemLucro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoValorSugeridoVenda = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        campoValorVenda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        campoTotalIngredientes = new javax.swing.JTextField();
        botaoCalcularValorIngredientes = new javax.swing.JButton();
        botaoCalcularValorSugerido = new javax.swing.JButton();
        botaoEditarInfo = new javax.swing.JButton();
        botaoCancelarEdicao = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Produto");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("produtos_48.png")).getImage());
        setResizable(false);

        labelId.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        labelId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_36.png"))); // NOI18N
        labelId.setText("ID: 42");

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
        scrollPaneProdutosCadastrados.setViewportView(tabelaProdutosCadastrados);
        if (tabelaProdutosCadastrados.getColumnModel().getColumnCount() > 0) {
            tabelaProdutosCadastrados.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabelaProdutosCadastrados.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Produtos Cadastrados:");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setText("Ingredientes:");

        scrollPaneIngredientes.setName("scrollPaneIngredientes"); // NOI18N

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
        scrollPaneIngredientes.setViewportView(tabelaIngredientes);
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

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("Valor pago:");

        campoValorPago.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorPago.setName("campoValorPago"); // NOI18N

        campoPorcentagemLucro.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("% de lucro:");

        campoValorSugeridoVenda.setEditable(false);
        campoValorSugeridoVenda.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setText("<html>Valor de venda sugerido:</html>");

        campoValorVenda.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorVenda.setName("campoValorVenda"); // NOI18N

        jLabel18.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel18.setText("Valor de venda:");

        jLabel19.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel19.setText("Total (R$):");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addComponent(campoBuscaProdutos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscarProdutos))
                            .addComponent(scrollPaneProdutosCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoAdicionarIngrediente)
                            .addComponent(botaoRemoverIngrediente))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPaneIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTotalIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoCalcularValorIngredientes))))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(campoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(campoPorcentagemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoValorSugeridoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoCalcularValorSugerido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(campoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                        .addGap(18, 18, 18)
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel19)
                                                .addComponent(campoTotalIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(botaoBuscarProdutos)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollPaneProdutosCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(botaoCalcularValorIngredientes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(scrollPaneIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addComponent(botaoAdicionarIngrediente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(botaoRemoverIngrediente)))))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(botaoCalcularValorSugerido, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPorcentagemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoValorSugeridoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        botaoCancelarEdicao.setText("Cancelar Edição");
        botaoCancelarEdicao.setEnabled(false);
        botaoCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarEdicaoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 47, 52));
        jLabel11.setText("* Campos obrigatórios");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoEditarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(botaoCancelarEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelId)
                    .addComponent(jLabel11))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoEditarInfo)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelarEdicao)
                        .addGap(394, 394, 394)
                        .addComponent(botaoExcluir))
                    .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEditarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarInfoActionPerformed
        if (editando) {
            if (ProdutoValidator.validate(formPanel)) {
                boolean reply;
                if (radioButtonSim.isSelected() && tabelaIngredientes.getRowCount() == 0) {
                    reply = AuxFunctions.popupConfirmacao("Alterações", "Este produto consta que é produzido na padaria e não possui nenhum ingrediente cadastrado. Deseja realizar as alterações mesmo assim?");
                } else {
                    reply = AuxFunctions.popupConfirmacao("Alterações", "Realmente deseja realizar as alterações?");
                }

                if (reply) {
                    produto.setDescricao(campoDescricao.getText().toUpperCase());
                    produto.setUnidadeDeMedida(campoUnidadeMedida.getText().toUpperCase());
                    produto.setQuantidadeNaEmbalagem(AuxFunctions.valorStringParaFloat(campoQuantidadeEmbalagem.getText()));

                    float valorPago = campoValorPago.getText().isEmpty() ? 0 : AuxFunctions.valorStringParaFloat(campoValorPago.getText());
                    float valorVenda = campoValorVenda.getText().isEmpty() ? 0 : AuxFunctions.valorStringParaFloat(campoValorVenda.getText());
                    produto.setValorUnitarioPago(valorPago);
                    produto.setValorUnitarioVenda(valorVenda);

                    boolean flag;
                    if (radioButtonSim.isSelected() && tabelaIngredientes.getRowCount() > 0) {
                        Ingrediente[] ingredientes = new Ingrediente[tabelaIngredientes.getRowCount()];
                        for (int i = 0; i < tabelaIngredientes.getRowCount(); i++) {
                            ingredientes[i] = new Ingrediente(
                                    ingredientesNaTabela.get(i).getIdProduto(),
                                    AuxFunctions.valorStringParaFloat((String) tabelaIngredientes.getValueAt(i, 2))
                            );
                        }

                        flag = ProdutoDAO.updateProduto(produto, ingredientes);
                    } else {
                        flag = ProdutoDAO.updateProduto(produto);
                    }

                    if (flag) {
                        AuxFunctions.popup(
                                this,
                                "Edição de produto", "Produto atualizado com sucesso.",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        menuPrincipal.setArrayProdutosTodosCadastrados();
                        menuPrincipal.preencherTabelaProdutos();

                        this.editando = false;

                        preencherCampos();

                        flipEdicaoInfo(false);

                        botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/edit_36.png")));
                        botaoEditarInfo.setText("Editar Informações");

                        botaoCancelarEdicao.setEnabled(false);
                        botaoExcluir.setEnabled(true);
                    } else {
                        AuxFunctions.popup(
                                this,
                                "Edição de produto",
                                "Algum erro ocorreu ao editar o produto. Favor reiniciar o programa e tentar novamente.",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        } else {
            this.editando = true;

            flipEdicaoInfo(true);

            botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/save_edit_36.png")));
            botaoEditarInfo.setText("Salvar Alterações");

            botaoCancelarEdicao.setEnabled(true);
            botaoExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_botaoEditarInfoActionPerformed

    private void botaoCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarEdicaoActionPerformed
        this.editando = false;

        preencherCampos();

        flipEdicaoInfo(false);

        botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/edit_36.png")));
        botaoEditarInfo.setText("Editar Informações");

        botaoCancelarEdicao.setEnabled(false);
        botaoExcluir.setEnabled(true);
    }//GEN-LAST:event_botaoCancelarEdicaoActionPerformed

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
            for (Ingrediente ingrediente : ingredientesNaTabela) {
                if (ingrediente.getIdProduto() == p.getIdProduto()) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ingredientesNaTabela.add(new Ingrediente(
                        p.getIdProduto()
                ));

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

    private void botaoCalcularValorIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularValorIngredientesActionPerformed
        Ingrediente ingrediente;
        float valorTotal = 0, quantidadeRelativa;
        boolean flag = true;

        for (int i = 0; i < tabelaIngredientes.getRowCount(); i++) {
            ingrediente = ingredientesNaTabela.get(i);

            try {
                quantidadeRelativa = AuxFunctions.valorStringParaFloat(tabelaIngredientes.getValueAt(i, 2).toString());

                valorTotal += (quantidadeRelativa / ingrediente.getProduto().getQuantidadeNaEmbalagem()) * ingrediente.getProduto().getValorUnitarioPago();
            } catch (NumberFormatException e) {
                AuxFunctions.popup(this, "Atenção", "Algum valor na tabela de ingredientes está incorreto", JOptionPane.WARNING_MESSAGE);
                flag = false;
                break;
            }

            if (ingrediente.getProduto().getValorUnitarioPago() == 0) {
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

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (!ProdutoDAO.produtoEstaEmAlgumEstoque(produto.getIdProduto())) {
            Ingrediente[] ingredientesDoProduto = ProdutoDAO.selectTodosIngredientesProdutoFazParte(produto.getIdProduto());
            if (ingredientesDoProduto.length == 0) {
                if (AuxFunctions.popupConfirmacao("Exclusão de produto", "Realmente deseja realizar a exclusão?")) {
                    ProdutoDAO.deleteProduto(produto.getIdProduto());

                    AuxFunctions.popup(
                            this,
                            "Informação",
                            "Exclusão realizada com sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    menuPrincipal.setArrayProdutosTodosCadastrados();
                    menuPrincipal.preencherTabelaProdutos();
                    
                    this.dispose();
                }
            } else {
                String listaProdutos = "";
                for (Ingrediente ingrediente : ingredientesDoProduto){
                    listaProdutos += "\n" + ingrediente.getProdutoFazParte().getDescricao();
                }
                        
                AuxFunctions.popup(
                        this,
                        "Atenção",
                        "Este produto atualmente faz parte dos ingredientes de pelo menos um outro produto,\nportanto a exclusão não poderá ser realizada.\n" +
                        "Produtos:" + listaProdutos,
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Este produto atualmente está cadastrado em algum estoque, portanto a exclusão não poderá ser realizada.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarIngrediente;
    private javax.swing.JButton botaoBuscarProdutos;
    private javax.swing.JButton botaoCalcularValorIngredientes;
    private javax.swing.JButton botaoCalcularValorSugerido;
    private javax.swing.JButton botaoCancelarEdicao;
    private javax.swing.JButton botaoEditarInfo;
    private javax.swing.JButton botaoExcluir;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelId;
    private javax.swing.ButtonGroup produzidoPadariaButtonGroup;
    private javax.swing.JRadioButton radioButtonNao;
    private javax.swing.JRadioButton radioButtonSim;
    private javax.swing.JScrollPane scrollPaneIngredientes;
    private javax.swing.JScrollPane scrollPaneProdutosCadastrados;
    private javax.swing.JTable tabelaIngredientes;
    private javax.swing.JTable tabelaProdutosCadastrados;
    // End of variables declaration//GEN-END:variables
}
