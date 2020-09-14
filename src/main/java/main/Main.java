package main;

import dao.ProdutoDAO;
import gui.MenuPrincipal;
import models.Ingrediente;
import models.Produto;

public class Main {

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        new MenuPrincipal().setVisible(true);
    }

}
