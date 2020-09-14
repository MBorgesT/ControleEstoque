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
        JTextField campoDescricao, campoUnidadeMedida, campoQuantidadeEmbalagem;
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

        return (validarCamposVazios(campoDescricao, campoUnidadeMedida, campoQuantidadeEmbalagem)
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
                System.out.println(tabelaIngredientes.getValueAt(i, 2).toString());
                AuxFunctions.valorStringParaFloat(tabelaIngredientes.getValueAt(i, 2).toString());
            }
            return true;
        } catch (NumberFormatException e) {
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
