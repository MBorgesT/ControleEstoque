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
import models.Estoque;

public class EstoqueDAO {

    public static Estoque[] selectTodosEstoques() {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Estoque");
            ArrayList<Estoque> arrayListEstoque = new ArrayList<>();

            while (rs.next()) {
                arrayListEstoque.add(new Estoque(
                        rs.getInt("idEstoque"),
                        rs.getString("descricao")
                ));
            }

            conn.close();

            return arrayListEstoque.toArray(new Estoque[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Estoque selectEstoquePorId(int idEstoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            
            String sql = "SELECT * FROM Estoque WHERE idEstoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idEstoque);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Estoque estoque = new Estoque(
                        rs.getInt("idEstoque"),
                        rs.getString("descricao")
                );
                
                conn.close();
                return estoque;
            } else {
                conn.close();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean insertEstoque(Estoque estoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Estoque(descricao) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, estoque.getDescricao());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean updateEstoque(Estoque estoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "UPDATE Estoque SET descricao = ? WHERE idEstoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, estoque.getDescricao());
            ps.setInt(2, estoque.getIdEstoque());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean podeExcluirEstoque(Estoque estoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            PreparedStatement ps = conn.prepareStatement("SELECT idInstanciaProduto FROM InstanciaProduto WHERE categoria = ? AND idEstoque = ?");
            
            ps.setInt(1, 0);
            ps.setInt(2, estoque.getIdEstoque());
            
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                conn.close();
                return false;
            } else {
                conn.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean deleteEstoque(Estoque estoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "DELETE FROM Estoque WHERE idEstoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, estoque.getIdEstoque());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean estoqueJaCadastradoComDescricao(String descricao) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM Estoque WHERE descricao = ?");
           
            ps.setString(1, descricao);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public static boolean estoqueJaCadastradoComDescricao(String descricao, int idEstoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM Estoque WHERE descricao = ? AND idEstoque <> ?");
           
            ps.setString(1, descricao);
            ps.setInt(2, idEstoque);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
