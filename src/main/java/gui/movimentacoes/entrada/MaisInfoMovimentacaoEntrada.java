package gui.movimentacoes.entrada;

import aux_functions.AuxFunctions;
import dao.EntradaProdutosDAO;
import dao.EstoqueDAO;
import dao.FornecedorDAO;
import dao.InstanciaProdutoMovimentacaoDAO;
import dao.ProdutoDAO;
import gui.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.EntradaProdutos;
import models.Estoque;
import models.Fornecedor;
import models.InstanciaProdutoMovimentacao;
import models.Produto;
import validators.EntradaValidator;
import org.apache.commons.lang3.ArrayUtils;

public class MaisInfoMovimentacaoEntrada extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;

    private boolean editando;

    private int tipoMovimentacaoOriginal;
    private int idEstoqueDestinoOriginal;

    private EntradaProdutos entradaProdutos;

    private Fornecedor[] todosFornecedores;
    private Estoque[] todosEstoques;

    private Produto[] todosProdutosCadastrados;
    private Produto[] produtosNaTabela;
    private ArrayList<Produto> produtosEntrada;

    public MaisInfoMovimentacaoEntrada(MenuPrincipal menuPrincipal, EntradaProdutos entradaProdutos) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        this.entradaProdutos = entradaProdutos;

        this.editando = false;

        this.tipoMovimentacaoOriginal = entradaProdutos.getTipoMovimentacao();
        this.idEstoqueDestinoOriginal = entradaProdutos.getIdEstoqueDestino();

        this.todosProdutosCadastrados = ProdutoDAO.selectTodosProdutos();
        this.produtosNaTabela = this.todosProdutosCadastrados;

        setRadioButtonsListeners();

        preencherComboBoxFornecedores();
        preencherComboBoxEstoques();
        preencherTabelaProdutosCadastrados();
        preencherCampos();

        tabelaProdutosCadastrados.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabelaProdutosEntrada.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        flipEditarInfo(false);
    }

    private void flipEditarInfo(boolean b) {
        radioButtonSim.setEnabled(b);
        radioButtonNao.setEnabled(b);

        comboBoxFornecedor.setEnabled(radioButtonSim.isSelected());

        comboBoxFornecedor.setEnabled(b);
        comboBoxEstoqueDestino.setEnabled(b);

        campoBusca.setEnabled(b);
        botaoBuscar.setEnabled(b);

        campoValorTabela.setEnabled(b);
        botaoCalcularValorTabela.setEnabled(b);

        tabelaProdutosCadastrados.setEnabled(b);
        tabelaProdutosEntrada.setEnabled(b);

        botaoAdicionarProduto.setEnabled(b);
        botaoExcluirProduto.setEnabled(b);

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

    private void flipCompra(boolean b) {
        if (b) {
            comboBoxFornecedor.setEnabled(true);
            comboBoxFornecedor.setSelectedIndex(-1);

            campoValorTabela.setText("");
            botaoCalcularValorTabela.setEnabled(true);

            if (tabelaProdutosEntrada.getColumnCount() == 4) {
                DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
                model.setColumnCount(5);

                TableColumn coluna = tabelaProdutosEntrada.getTableHeader().getColumnModel().getColumn(4);
                coluna.setHeaderValue("Valor unitário");
            }
        } else {
            comboBoxFornecedor.setEnabled(false);
            comboBoxFornecedor.setSelectedIndex(-1);

            campoValorTabela.setText("");
            botaoCalcularValorTabela.setEnabled(false);

            if (tabelaProdutosEntrada.getColumnCount() == 5) {
                DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
                model.setColumnCount(4);
            }
        }
    }

    private void setRadioButtonsListeners() {
        radioButtonSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                flipCompra(true);
            }
        });

        radioButtonNao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                flipCompra(false);
            }
        });
    }

    private void preencherCampos() {
        labelId.setText("ID: " + String.valueOf(entradaProdutos.getIdMovimentacao()));

        campoData.setText(AuxFunctions.formatData(entradaProdutos.getData()));

        if (entradaProdutos.isCompra()) {
            radioButtonSim.setSelected(true);
            flipCompra(true);

            for (int i = 0; i < todosFornecedores.length; i++) {
                if (entradaProdutos.getIdFornecedor() == todosFornecedores[i].getIdFornecedor()) {
                    comboBoxFornecedor.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            radioButtonNao.setSelected(true);
            flipCompra(false);
        }

        for (int i = 0; i < todosEstoques.length; i++) {
            if (entradaProdutos.getIdEstoqueDestino() == todosEstoques[i].getIdEstoque()) {
                comboBoxEstoqueDestino.setSelectedIndex(i);
                break;
            }
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

    private void preencherComboBoxFornecedores() {
        todosFornecedores = FornecedorDAO.selectTodosFornecedores();
        comboBoxFornecedor.removeAllItems();

        for (Fornecedor fornecedor : todosFornecedores) {
            comboBoxFornecedor.addItem(fornecedor.getDescricao());
        }

        comboBoxFornecedor.setSelectedIndex(-1);
    }

    private void preencherComboBoxEstoques() {
        todosEstoques = EstoqueDAO.selectTodosEstoques();
        comboBoxEstoqueDestino.removeAllItems();

        for (Estoque estoque : todosEstoques) {
            comboBoxEstoqueDestino.addItem(estoque.getDescricao());
        }

        comboBoxEstoqueDestino.setSelectedIndex(-1);
    }

    private void preencherTabelaProdutosCadastrados() {
        DefaultTableModel model = (DefaultTableModel) tabelaProdutosCadastrados.getModel();

        model.setRowCount(0);
        for (Produto p : produtosNaTabela) {
            model.addRow(p.getCadastroEntradaTableRow());
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
        radioButtonSim = new javax.swing.JRadioButton();
        radioButtonNao = new javax.swing.JRadioButton();
        comboBoxFornecedor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxEstoqueDestino = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutosCadastrados = new javax.swing.JTable();
        botaoAdicionarProduto = new javax.swing.JButton();
        botaoExcluirProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutosEntrada = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        campoBusca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campoValorTabela = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        botaoCalcularValorTabela = new javax.swing.JButton();
        botaoEditarInfo = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelarEdicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Entrada");

        labelId.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        labelId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_48.png"))); // NOI18N
        labelId.setText("ID: 42");

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Compra?");

        categoriaButtonGroup.add(radioButtonSim);
        radioButtonSim.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonSim.setSelected(true);
        radioButtonSim.setText("Sim");
        radioButtonSim.setName("radioButtonSim"); // NOI18N

        categoriaButtonGroup.add(radioButtonNao);
        radioButtonNao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioButtonNao.setText("Não");
        radioButtonNao.setName("radioButtonNao"); // NOI18N

        comboBoxFornecedor.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxFornecedor.setName("comboBoxFornecedor"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Fornecedor:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Estoque de destino:");

        comboBoxEstoqueDestino.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        comboBoxEstoqueDestino.setName("comboBoxEstoqueDestino"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Produtos cadastrados:");

        jScrollPane1.setName("scrollPaneProdutosCadastrados"); // NOI18N

        tabelaProdutosCadastrados.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaProdutosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtd. na embalagem", "Un. de medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutosCadastrados.setName("tabelaProdutosCadastrados"); // NOI18N
        jScrollPane1.setViewportView(tabelaProdutosCadastrados);
        if (tabelaProdutosCadastrados.getColumnModel().getColumnCount() > 0) {
            tabelaProdutosCadastrados.getColumnModel().getColumn(0).setPreferredWidth(140);
            tabelaProdutosCadastrados.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabelaProdutosCadastrados.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        botaoAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in_arrow_24.png"))); // NOI18N
        botaoAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarProdutoActionPerformed(evt);
            }
        });

        botaoExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close_24.png"))); // NOI18N
        botaoExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirProdutoActionPerformed(evt);
            }
        });

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
                false, false, false, true, true
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

        botaoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_24.png"))); // NOI18N
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        campoBusca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sigma_24.png"))); // NOI18N
        jLabel8.setText("Valor na tabela:");

        campoValorTabela.setEditable(false);
        campoValorTabela.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoCalcularValorTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculadora_24.png"))); // NOI18N
        botaoCalcularValorTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularValorTabelaActionPerformed(evt);
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
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(radioButtonSim)
                                .addGap(18, 18, 18)
                                .addComponent(radioButtonNao))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(comboBoxEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addComponent(campoBusca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoBuscar))
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoAdicionarProduto)
                            .addComponent(botaoExcluirProduto))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoValorTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(botaoCalcularValorTabela)))
                                .addGap(0, 110, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioButtonSim)
                        .addComponent(radioButtonNao)
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxEstoqueDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBuscar))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(16, 16, 16)
                                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(campoValorTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6))
                                    .addComponent(botaoCalcularValorTabela, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(botaoAdicionarProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoExcluirProduto))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

        botaoExcluir.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete_36.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelId)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCancelarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoEditarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoEditarInfo)
                    .addComponent(botaoCancelarEdicao))
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
        if (this.editando) {
            if (EntradaValidator.validate(formPanel) && AuxFunctions.popupConfirmacao("Confirmação", "Realmente deseja atualizar as informações?")) {

                ArrayList<InstanciaProdutoMovimentacao> arrayInstancias = new ArrayList<>();
                for (int i = 0; i < tabelaProdutosEntrada.getRowCount(); i++) {
                    InstanciaProdutoMovimentacao instancia;
                    if (radioButtonSim.isSelected()) {
                        instancia = new InstanciaProdutoMovimentacao(
                                AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 4).toString()),
                                produtosEntrada.get(i).getIdProduto(),
                                Integer.parseInt(tabelaProdutosEntrada.getValueAt(i, 3).toString())
                        );
                    } else {
                        instancia = new InstanciaProdutoMovimentacao(
                                produtosEntrada.get(i).getIdProduto(),
                                Integer.parseInt(tabelaProdutosEntrada.getValueAt(i, 3).toString())
                        );
                    }
                    arrayInstancias.add(instancia);
                }

                InstanciaProdutoMovimentacao[] novasInstancias = arrayInstancias.toArray(new InstanciaProdutoMovimentacao[0]);
                InstanciaProdutoMovimentacao[] instanciasAntigas = entradaProdutos.getInstanciasProduto();

                if (EntradaProdutosDAO.podeFazerUpdate(entradaProdutos, instanciasAntigas, novasInstancias)) {
                    if (radioButtonSim.isSelected()) {
                        entradaProdutos.setTipoMovimentacao(4);
                        int idFornecedor = todosFornecedores[comboBoxFornecedor.getSelectedIndex()].getIdFornecedor();
                        entradaProdutos.setIdFornecedor(idFornecedor);
                    } else {
                        entradaProdutos.setTipoMovimentacao(3);
                        entradaProdutos.setIdFornecedor(-1);
                    }

                    entradaProdutos.setIdEstoqueDestino(todosEstoques[comboBoxEstoqueDestino.getSelectedIndex()].getIdEstoque());

                    if (EntradaProdutosDAO.updateEntradaProdutos(entradaProdutos, novasInstancias, tipoMovimentacaoOriginal, idEstoqueDestinoOriginal)) {
                        AuxFunctions.popup(
                                this,
                                "Atualização de informações",
                                "Atualização efetuada com sucesso.",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        menuPrincipal.setArrayInstanciasProdutoEstoqueTodosCadastrados();
                        menuPrincipal.preencherTabelaInstanciasProdutoEstoque();

                        menuPrincipal.setArrayMovimentacoesTodasCadastradas();
                        menuPrincipal.preencherTabelaMovimentacoes();

                        this.tipoMovimentacaoOriginal = entradaProdutos.getTipoMovimentacao();
                        this.idEstoqueDestinoOriginal = entradaProdutos.getIdEstoqueDestino();

                        this.editando = false;
                        flipEditarInfo(false);
                    } else {
                        AuxFunctions.popup(
                                this,
                                "Erro",
                                "Houve algum erro ao atualizar as informações no banco de dados.\n"
                                + "Favor reiniciar o programa e tentar novamente.",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    AuxFunctions.popup(
                            this,
                            "Atenção",
                            "A atualização não pode ser feita porque as quantidades novas não batem com as que estão no estoque.",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        } else {
            this.editando = true;
            flipEditarInfo(true);
        }
    }//GEN-LAST:event_botaoEditarInfoActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        String searchParam = campoBusca.getText().toUpperCase();
        ArrayList<Produto> produtosBuscados = new ArrayList<>();

        for (Produto p : todosProdutosCadastrados) {
            if (p.getDescricao().contains(searchParam)) {
                produtosBuscados.add(p);
            }
        }

        produtosNaTabela = produtosBuscados.toArray(new Produto[0]);
        preencherTabelaProdutosCadastrados();
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarProdutoActionPerformed
        int selectedRow = tabelaProdutosCadastrados.getSelectedRow();

        if (selectedRow >= 0) {
            produtosEntrada.add(produtosNaTabela[selectedRow]);

            DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
            model.addRow(produtosNaTabela[selectedRow].getCadastroEntradaTableRow());
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um produto na tabela à esquerda.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoAdicionarProdutoActionPerformed

    private void botaoExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirProdutoActionPerformed
        int selectedRow = tabelaProdutosEntrada.getSelectedRow();

        if (selectedRow >= 0) {
            produtosEntrada.remove(selectedRow);

            DefaultTableModel model = (DefaultTableModel) tabelaProdutosEntrada.getModel();
            model.removeRow(selectedRow);
        } else {
            AuxFunctions.popup(
                    this,
                    "Atenção",
                    "Favor selecionar um produto na tabela à esquerda.",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoExcluirProdutoActionPerformed

    private void botaoCalcularValorTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularValorTabelaActionPerformed
        float valorTotal = 0;

        if (EntradaValidator.validateTabela(tabelaProdutosEntrada, radioButtonSim)) {
            for (int i = 0; i < tabelaProdutosEntrada.getRowCount(); i++) {
                int quantidade = Integer.parseInt(tabelaProdutosEntrada.getValueAt(i, 3).toString());
                float valorUnitario = AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 4).toString());

                valorTotal += quantidade * valorUnitario;
            }
        }

        campoValorTabela.setText("R$ " + AuxFunctions.valorFloatParaString(valorTotal));
    }//GEN-LAST:event_botaoCalcularValorTabelaActionPerformed

    private void botaoCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarEdicaoActionPerformed
        flipEditarInfo(false);
    }//GEN-LAST:event_botaoCancelarEdicaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCalcularValorTabela;
    private javax.swing.JButton botaoCancelarEdicao;
    private javax.swing.JButton botaoEditarInfo;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoExcluirProduto;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoValorTabela;
    private javax.swing.ButtonGroup categoriaButtonGroup;
    private javax.swing.JComboBox<String> comboBoxEstoqueDestino;
    private javax.swing.JComboBox<String> comboBoxFornecedor;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelId;
    private javax.swing.JRadioButton radioButtonNao;
    private javax.swing.JRadioButton radioButtonSim;
    private javax.swing.JTable tabelaProdutosCadastrados;
    private javax.swing.JTable tabelaProdutosEntrada;
    // End of variables declaration//GEN-END:variables
}
