package validators;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import aux_functions.AuxFunctions;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProdutoValidator {

    public static boolean validate(JPanel panel) {
        JTextField campoDescricao, campoUnidadeMedida, campoQuantidadeEmbalagem, campoValorPago, campoValorVenda;
        JRadioButton radioButtonSim, radioButtonNao;
        JScrollPane scrollPaneIngredientes;
        JTable tabelaIngredientes = null;

        Component[] components = panel.getComponents();
        HashMap componentMap = new HashMap<String, Component>();
        for (int i = 0;
                i < components.length;
                i++) {
            componentMap.put(components[i].getName(), components[i]);
        }

        campoDescricao = (JTextField) componentMap.get("campoDescricao");
        campoUnidadeMedida = (JTextField) componentMap.get("campoUnidadeMedida");
        campoQuantidadeEmbalagem = (JTextField) componentMap.get("campoQuantidadeEmbalagem");

        radioButtonSim = (JRadioButton) componentMap.get("radioButtonSim");
        radioButtonNao = (JRadioButton) componentMap.get("radioButtonNao");

        scrollPaneIngredientes = (JScrollPane) componentMap.get("scrollPaneIngredientes");
        components = scrollPaneIngredientes.getViewport().getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i].getName().equals("tabelaIngredientes")) {
                tabelaIngredientes = (JTable) components[i];
                break;
            }
        }

        campoValorPago = (JTextField) componentMap.get("campoValorPago");
        campoValorVenda = (JTextField) componentMap.get("campoValorVenda");

        return (validarCamposVazios(campoDescricao, campoUnidadeMedida, campoQuantidadeEmbalagem)
                && validarValoresNumericos(campoQuantidadeEmbalagem, campoValorPago, campoValorVenda)
                && validarRadioButtonSelecionado(radioButtonSim, radioButtonNao)
                && validarQuantidadeRelativaTabelaIngredientes(tabelaIngredientes));
    }

    private static boolean validarCamposVazios(JTextField campoDescricao, JTextField campoUnidadeMedida, JTextField campoQuantidadeEmbalagem) {
        if (campoDescricao.getText().isEmpty()
                || campoUnidadeMedida.getText().isEmpty()
                || campoQuantidadeEmbalagem.getText().isEmpty()) {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Algum campo obrigatório está vazio. Favor preenche-lo.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else {
            return true;
        }
    }

    private static boolean validarValoresNumericos(JTextField campoQuantidadeEmbalagem, JTextField campoValorPago, JTextField campoValorVenda) {
        try {
            AuxFunctions.valorStringParaFloat(campoQuantidadeEmbalagem.getText());
        } catch (NumberFormatException e) {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "O valor do campo de quantidade na embalagem está incorreto.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (!campoValorPago.getText().isEmpty()) {
            try {
                AuxFunctions.valorStringParaFloat(campoValorPago.getText());
            } catch (NumberFormatException e) {
                AuxFunctions.popup(
                        null,
                        "Atenção",
                        "O valor do campo de valor pago está incorreto.",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
        }
        
        if (!campoValorVenda.getText().isEmpty()) {
            try {
                AuxFunctions.valorStringParaFloat(campoValorVenda.getText());
            } catch (NumberFormatException e) {
                AuxFunctions.popup(
                        null,
                        "Atenção",
                        "O valor do campo de valor de venda está incorreto.",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
        }

        return true;
    }

    private static boolean validarRadioButtonSelecionado(JRadioButton radioButtonSim, JRadioButton radioButtonNao) {
        if (radioButtonSim.isSelected() || radioButtonNao.isSelected()) {
            return true;
        } else {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Algum campo obrigatório está vazio. Favor preenche-lo.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    }

    private static boolean validarQuantidadeRelativaTabelaIngredientes(JTable tabelaIngredientes) {
        try {
            for (int i = 0; i < tabelaIngredientes.getRowCount(); i++) {
                AuxFunctions.valorStringParaFloat(tabelaIngredientes.getValueAt(i, 2).toString());
            }
            return true;
        } catch (Exception e) {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Algum valor na tabela de ingredientes está incorreto.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    }

}
