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
import models.Ingrediente;
import models.Produto;

public class ProdutoDAO {

    public static Produto[] selectTodosProdutos() {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Produto");
            ArrayList<Produto> arrayListProdutos = new ArrayList<>();

            while (rs.next()) {
                arrayListProdutos.add(new Produto(
                        rs.getInt("idProduto"),
                        rs.getString("descricao"),
                        rs.getFloat("valorUnitarioPago"),
                        rs.getFloat("valorUnitarioVenda"),
                        rs.getString("unidadeDeMedida"),
                        rs.getFloat("quantidadeNaEmbalagem"),
                        rs.getBoolean("produzidoNaPadaria")
                ));
            }

            conn.close();

            return arrayListProdutos.toArray(new Produto[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Produto selectProdutoPorId(int idProduto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Produto WHERE idProduto = " + String.valueOf(idProduto));

            rs.next();
            Produto produto = new Produto(
                    rs.getInt("idProduto"),
                    rs.getString("descricao"),
                    rs.getFloat("valorUnitarioPago"),
                    rs.getFloat("valorUnitarioVenda"),
                    rs.getString("unidadeDeMedida"),
                    rs.getFloat("quantidadeNaEmbalagem"),
                    rs.getBoolean("produzidoNaPadaria")
            );

            conn.close();

            return produto;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ingrediente[] selectTodosIngredientesDeProduto(int idProduto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Ingrediente WHERE idProdutoFazParte = " + String.valueOf(idProduto));
            ArrayList<Ingrediente> arrayListIngredientes = new ArrayList<>();
            while (rs.next()) {
                arrayListIngredientes.add(new Ingrediente(
                        rs.getInt("idIngrediente"),
                        rs.getInt("idProduto"),
                        rs.getInt("idProdutoFazParte"),
                        rs.getFloat("quantidadeRelativa")
                ));
            }

            conn.close();

            return arrayListIngredientes.toArray(new Ingrediente[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ingrediente[] selectTodosIngredientesProdutoFazParte(int idProduto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Ingrediente WHERE idProduto = " + String.valueOf(idProduto));
            ArrayList<Ingrediente> arrayListIngredientes = new ArrayList<>();
            while (rs.next()) {
                arrayListIngredientes.add(new Ingrediente(
                        rs.getInt("idIngrediente"),
                        rs.getInt("idProduto"),
                        rs.getInt("idProdutoFazParte"),
                        rs.getFloat("quantidadeRelativa")
                ));
            }

            conn.close();

            return arrayListIngredientes.toArray(new Ingrediente[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean insertProduto(Produto produto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Produto(descricao, valorUnitarioPago, valorUnitarioVenda, unidadeDeMedida, quantidadeNaEmbalagem, produzidoNaPadaria) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getDescricao());
            ps.setFloat(2, produto.getValorUnitarioPago());
            ps.setFloat(3, produto.getValorUnitarioVenda());
            ps.setString(4, produto.getUnidadeDeMedida());
            ps.setFloat(5, produto.getQuantidadeNaEmbalagem());
            ps.setBoolean(6, produto.isProduzidoNaPadaria());

            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertProduto(Produto produto, Ingrediente[] ingredientes) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Produto(descricao, valorUnitarioPago, valorUnitarioVenda, unidadeDeMedida, quantidadeNaEmbalagem, produzidoNaPadaria) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, produto.getDescricao());
            ps.setFloat(2, produto.getValorUnitarioPago());
            ps.setFloat(3, produto.getValorUnitarioVenda());
            ps.setString(4, produto.getUnidadeDeMedida());
            ps.setFloat(5, produto.getQuantidadeNaEmbalagem());
            ps.setBoolean(6, produto.isProduzidoNaPadaria());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idProdutoFazParte = rs.getInt(1);

            sql = "INSERT INTO Ingrediente(idProduto, idProdutoFazParte, quantidadeRelativa) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);

            for (Ingrediente ingrediente : ingredientes) {
                ps.setInt(1, ingrediente.getIdProduto());
                ps.setInt(2, idProdutoFazParte);
                ps.setFloat(3, ingrediente.getQuantidadeRelativa());

                ps.executeUpdate();
            }

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProduto(Produto produto) {
        try {
            deleteIngredientesPorProdutoFazParte(produto.getIdProduto());

            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String query = "UPDATE Produto SET descricao = ?, valorUnitarioPago = ?, valorUnitarioVenda = ?, unidadeDeMedida = ?, quantidadeNaEmbalagem = ?, produzidoNaPadaria = ? WHERE idProduto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, produto.getDescricao());
            stmt.setFloat(2, produto.getValorUnitarioPago());
            stmt.setFloat(3, produto.getValorUnitarioVenda());
            stmt.setString(4, produto.getUnidadeDeMedida());
            stmt.setFloat(5, produto.getQuantidadeNaEmbalagem());
            stmt.setBoolean(6, produto.isProduzidoNaPadaria());
            stmt.setInt(7, produto.getIdProduto());

            stmt.execute();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProduto(Produto produto, Ingrediente[] ingredientes) {
        try {
            updateProduto(produto);

            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Ingrediente(idProduto, idProdutoFazParte, quantidadeRelativa) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Ingrediente ingrediente : ingredientes) {
                ps.setInt(1, ingrediente.getIdProduto());
                ps.setInt(2, produto.getIdProduto());
                ps.setFloat(3, ingrediente.getQuantidadeRelativa());

                ps.executeUpdate();
            }

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteProduto(int idProduto) {
        try {
            deleteIngredientesPorProdutoFazParte(idProduto);
            
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "DELETE FROM Produto WHERE idProduto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idProduto);
            ps.executeUpdate();

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void deleteIngredientesPorProdutoFazParte(int idProdutoFazParte) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String query = "DELETE FROM Ingrediente WHERE idProdutoFazParte = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, idProdutoFazParte);

            preparedStmt.execute();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean produtoEstaEmAlgumEstoque(int idProduto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM InstanciaProduto WHERE (idProduto = " + String.valueOf(idProduto) + " AND categoria = 0)");

            boolean flag = rs.next();

            conn.close();

            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
