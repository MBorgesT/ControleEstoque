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
import models.Fornecedor;

public class FornecedorDAO {

    public static Fornecedor[] selectTodosFornecedores() {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fornecedor");
            ArrayList<Fornecedor> arrayListFornecedores = new ArrayList<>();

            while (rs.next()) {
                arrayListFornecedores.add(new Fornecedor(
                        rs.getInt("idFornecedor"),
                        rs.getString("descricao"),
                        rs.getString("telefone1"),
                        rs.getString("telefone2")
                ));
            }

            conn.close();

            return arrayListFornecedores.toArray(new Fornecedor[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Fornecedor selectFornecedorPorId(int idFornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            String sql = "SELECT * FROM Fornecedor WHERE idFornecedor = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idFornecedor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("idFornecedor"),
                        rs.getString("descricao"),
                        rs.getString("telefone1"),
                        rs.getString("telefone2")
                );

                conn.close();
                return fornecedor;
            } else {
                conn.close();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean insertFornecedor(Fornecedor fornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Fornecedor(descricao, telefone1, telefone2) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, fornecedor.getDescricao());
            ps.setString(2, fornecedor.getTelefone1());
            ps.setString(3, fornecedor.getTelefone2());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateFornecedor(Fornecedor fornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "UPDATE Fornecedor SET descricao = ?, telefone1 = ?, telefone2 = ? WHERE idFornecedor = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, fornecedor.getDescricao());
            ps.setString(2, fornecedor.getTelefone1());
            ps.setString(3, fornecedor.getTelefone2());
            ps.setInt(4, fornecedor.getIdFornecedor());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean podeExcluirFornecedor(Fornecedor fornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            PreparedStatement ps = conn.prepareStatement("SELECT idMovimentacao FROM Movimentacao WHERE tipoMovimentacao = ? AND idFornecedor = ?");

            ps.setInt(1, 4);
            ps.setInt(2, fornecedor.getIdFornecedor());

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

    public static boolean deleteFornecedor(Fornecedor fornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "DELETE FROM Fornecedor WHERE idFornecedor = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, fornecedor.getIdFornecedor());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean fornecedorJaCadastradoComDescricao(String descricao) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM Fornecedor WHERE descricao = ?");

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

    public static boolean fornecedorJaCadastradoComDescricao(String descricao, int idFornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM Fornecedor WHERE descricao = ? AND idFornecedor <> ?");

            ps.setString(1, descricao);
            ps.setInt(2, idFornecedor);
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
