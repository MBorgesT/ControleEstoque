package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.InstanciaProdutoEstoque;

public class InstanciaProdutoEstoqueDAO {

    public static InstanciaProdutoEstoque[] getTodasInstanciasProdutoEstoque(boolean selectQtdZero) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            Statement stmt = conn.createStatement();
            ResultSet rs;
            if (selectQtdZero) 
                rs = stmt.executeQuery("SELECT * FROM InstanciaProduto WHERE categoria = 0 ORDER BY idEstoque");
            else 
                rs = stmt.executeQuery("SELECT * FROM InstanciaProduto WHERE categoria = 0 AND quantidade > 0 ORDER BY idEstoque");
            
            ArrayList<InstanciaProdutoEstoque> arrayListInstancias = new ArrayList<>();

            while (rs.next()) {
                arrayListInstancias.add(new InstanciaProdutoEstoque(
                        rs.getInt("idEstoque"),
                        rs.getInt("idInstanciaProduto"),
                        rs.getInt("idProduto"),
                        rs.getInt("quantidade")
                ));

            }

            conn.close();

            return arrayListInstancias.toArray(new InstanciaProdutoEstoque[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static InstanciaProdutoEstoque[] selectInstanciasProdutoEstoquePorEstoque(int idEstoque, boolean selectQtdZero) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql;
            if (selectQtdZero) {
                sql = "SELECT * FROM InstanciaProduto WHERE idEstoque = ?";
            } else {
                sql = "SELECT * FROM InstanciaProduto WHERE idEstoque = ? AND quantidade > 0";
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idEstoque);
            ResultSet rs = ps.executeQuery();

            ArrayList<InstanciaProdutoEstoque> arrayListInstancias = new ArrayList<>();

            while (rs.next()) {
                arrayListInstancias.add(new InstanciaProdutoEstoque(
                        idEstoque,
                        rs.getInt("idInstanciaProduto"),
                        rs.getInt("idProduto"),
                        rs.getInt("quantidade")
                ));

            }

            conn.close();

            return arrayListInstancias.toArray(new InstanciaProdutoEstoque[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static InstanciaProdutoEstoque selectInstanciaProdutoEstoquePorEstoqueEProduto(int idEstoque, int idProduto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            String sql = "SELECT * FROM InstanciaProduto WHERE idEstoque = ? AND idProduto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idEstoque);
            ps.setInt(2, idProduto);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                InstanciaProdutoEstoque instancia = new InstanciaProdutoEstoque(
                        idEstoque,
                        rs.getInt("idInstanciaProduto"),
                        rs.getInt("idProduto"),
                        rs.getInt("quantidade")
                );
                conn.close();
                return instancia;
            } else {
                conn.close();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
