package aux_functions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AuxFunctions {

    private JFrame frame;
    private String title;
    private String message;
    private int option; //0: information; 1: warning; 2: error

    public static void popup(JFrame frame, String title, String message, int option) {
        JOptionPane.showMessageDialog(
                frame,
                message, //mensagem
                title, // titulo da janela 
                option);
    }

    public static boolean popupConfirmacao(String titulo, String mensagem) {
        String[] options = {"SIM", "NÃO"};
        int reply = JOptionPane.showOptionDialog(null, mensagem, titulo,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
        
        return reply == 0;
    }

    public static float valorStringParaFloat(String valor) {
        String novoValor = valor.replaceAll(",", ".");
        return Float.parseFloat(novoValor);
    }

    public static String valorFloatParaString(float valor) {
        String novoValor = String.format("%.02f", valor);
        novoValor = novoValor.replace('.', ',');
        return novoValor;
    }

}
