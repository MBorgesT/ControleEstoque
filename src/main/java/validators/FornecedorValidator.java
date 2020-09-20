
package validators;

import aux_functions.AuxFunctions;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FornecedorValidator {
    
    public static boolean validate(JPanel panel) {
        Component[] components = panel.getComponents();
        HashMap componentMap = new HashMap<String, Component>();
        for (int i = 0;
                i < components.length;
                i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
        
        JTextField campoDescricao = (JTextField) componentMap.get("campoDescricao");
        JTextField campoTelefone1 = (JTextField) componentMap.get("campoTelefone1");
        JTextField campoTelefone2 = (JTextField) componentMap.get("campoTelefone2");
        
        return (validateCamposVazios(campoDescricao, campoTelefone1));
    }
    
    private static boolean validateCamposVazios(JTextField campoDescricao, JTextField campoTelefone1){
        if (campoDescricao.getText().isEmpty()){
            AuxFunctions.popup(
                    null, 
                    "Atenção", 
                    "O campo de descrição está vazio e é obrigatório", 
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (campoTelefone1.getText().isEmpty()) {
            AuxFunctions.popup(
                    null, 
                    "Atenção", 
                    "O campo de telefone está vazio e é obrigatório", 
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }
    
}
