package validators;

import aux_functions.AuxFunctions;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EntradaValidator {

    public static boolean validate(JPanel panel) {

        Component[] components = panel.getComponents();
        HashMap componentMap = new HashMap<String, Component>();
        for (int i = 0;
                i < components.length;
                i++) {
            componentMap.put(components[i].getName(), components[i]);
        }

        JRadioButton radioButtonSim = (JRadioButton) componentMap.get("radioButtonSim");
        JRadioButton radioButtonNao = (JRadioButton) componentMap.get("radioButtonNao");

        JComboBox comboBoxFornecedor = (JComboBox) componentMap.get("comboBoxFornecedor");
        JComboBox comboBoxEstoqueDestino = (JComboBox) componentMap.get("comboBoxEstoqueDestino");

        JScrollPane scrollPaneProdutosEntrada = (JScrollPane) componentMap.get("scrollPaneProdutosEntrada");
        JTable tabelaProdutosEntrada = null;
        components = scrollPaneProdutosEntrada.getViewport().getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i].getName().equals("tabelaProdutosEntrada")) {
                tabelaProdutosEntrada = (JTable) components[i];
                break;
            }
        }

        return (validateRadioButtons(radioButtonSim, radioButtonNao)
                && validateComboBoxFornecedor(comboBoxFornecedor, radioButtonSim)
                && validateComboBoxEstoqueDestino(comboBoxEstoqueDestino)
                && validateTabela(tabelaProdutosEntrada, radioButtonSim));

    }

    private static boolean validateRadioButtons(JRadioButton radioButtonSim, JRadioButton radioButtonNao) {
        if (radioButtonSim.isSelected() || radioButtonNao.isSelected()) {
            return true;
        } else {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Favor selecionar se a movimentação é uma compra ou não.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    }

    private static boolean validateComboBoxFornecedor(JComboBox comboBoxFornecedor, JRadioButton radioButtonSim) {
        if (radioButtonSim.isSelected()) {
            if (comboBoxFornecedor.getSelectedIndex() != -1) {
                return true;
            } else {
                AuxFunctions.popup(
                        null,
                        "Atenção",
                        "Como a movimentação é de compra, é necessário selecionar um fornecedor.",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean validateComboBoxEstoqueDestino(JComboBox comboBoxEstoqueDestino) {
        if (comboBoxEstoqueDestino.getSelectedIndex() != -1) {
            return true;
        } else {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Favor selecionar um estoque de destino.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    }

    public static boolean validateTabela(JTable tabelaProdutosEntrada, JRadioButton radioButtonSim) {
        if (tabelaProdutosEntrada.getRowCount() == 0) {

            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "A tabela de produtos da entrada não pode estar vazio.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;

        } else {

            for (int i = 0; i < tabelaProdutosEntrada.getRowCount(); i++) {

                System.out.print(i);

                if (tabelaProdutosEntrada.getValueAt(i, 3) == null || (radioButtonSim.isSelected() && tabelaProdutosEntrada.getValueAt(i, 4) == null)) {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "Algum campo na tabela de produtos da entrada está vazio.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return false;
                }

                try {
                    AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 3).toString());
                } catch (NumberFormatException e) {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "O campo de quantidade na tabela de produtos da entrada só pode conter valores numéricos.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return false;
                }

                if (radioButtonSim.isSelected()) {
                    try {
                        AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 4).toString());
                    } catch (NumberFormatException e) {
                        AuxFunctions.popup(
                                null,
                                "Atenção",
                                "O campo de valor unitário na tabela de produtos da entrada só pode conter valores numéricos.",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return false;
                    }
                }

                if (AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 3).toString()) <= 0) {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "O campo de quantidade na tabela de produtos da entrada não pode ser menor ou igual a zero.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return false;
                }

                if (radioButtonSim.isSelected() && AuxFunctions.valorStringParaFloat(tabelaProdutosEntrada.getValueAt(i, 4).toString()) <= 0) {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "O campo de valor unitário na tabela de produtos da entrada não pode ser menor ou igual a zero.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return false;
                }

            }

            return true;
        }
    }
}
