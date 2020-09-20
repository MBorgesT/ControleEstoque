package aux_functions;

import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AuxFunctions {

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
    
    public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    /*
    public static java.util.Date convertSqlToUtil(java.sql.Date sDate) {
        return java.util.Date(sDate.getTime());
    }
*/
    
    public static String formatData(java.util.Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

}
