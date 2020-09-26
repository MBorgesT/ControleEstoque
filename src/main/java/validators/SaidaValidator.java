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

public class SaidaValidator {

    public static boolean validate(JPanel panel) {
        Component[] components = panel.getComponents();
        HashMap componentMap = new HashMap<String, Component>();
        for (int i = 0;
                i < components.length;
                i++) {
            componentMap.put(components[i].getName(), components[i]);
        }

        JComboBox comboBoxEstoqueOrigem = (JComboBox) componentMap.get("comboBoxEstoqueOrigem");

        JRadioButton radioButtonSim = (JRadioButton) componentMap.get("radioButtonSim");
        JRadioButton radioButtonNao = (JRadioButton) componentMap.get("radioButtonNao");

        JScrollPane scrollPaneTabela = (JScrollPane) componentMap.get("scrollPaneTabela");
        JTable tabelaInstanciasMovimentacao = null;
        components = scrollPaneTabela.getViewport().getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i].getName().equals("tabelaInstanciasMovimentacao")) {
                tabelaInstanciasMovimentacao = (JTable) components[i];
                break;
            }
        }

        return (validarComboBox(comboBoxEstoqueOrigem) && validarTabela(tabelaInstanciasMovimentacao, radioButtonSim));

    }

    private static boolean validarComboBox(JComboBox comboBoxEstoqueOrigem) {
        if (comboBoxEstoqueOrigem.getSelectedIndex() >= 0) {
            return true;
        } else {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Favor selecionar o estoque de origem.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    }

    public static boolean validarTabela(JTable tabelaInstanciasMovimentacao, JRadioButton radioButtonSim) {
        if (tabelaInstanciasMovimentacao.getRowCount() == 0) {
            AuxFunctions.popup(
                    null,
                    "Atenção",
                    "Favor preencher a tabela de produtos da saída com pelo menos um elemento.",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (radioButtonSim.isSelected()) {
            String valor;
            for (int i = 0; i < tabelaInstanciasMovimentacao.getRowCount(); i++) {
                if (tabelaInstanciasMovimentacao.getValueAt(i, 4) == null) {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "Favor preencher todos os campos de valor unitário.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return false;
                }

                try {
                    valor = tabelaInstanciasMovimentacao.getValueAt(i, 4).toString();
                    AuxFunctions.valorStringParaFloat(valor);
                } catch (NumberFormatException ex) {
                    AuxFunctions.popup(
                            null,
                            "Atenção",
                            "Algum valor unitário na tabela está incorreto.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return false;
                }
            }
        }

        return true;
    }

}
