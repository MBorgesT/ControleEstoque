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

    public static boolean insertProduto(Produto produto) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Produto(descricao, unidadeDeMedida, quantidadeNaEmbalagem, produzidoNaPadaria) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getDescricao());
            ps.setString(2, produto.getUnidadeDeMedida());
            ps.setFloat(3, produto.getQuantidadeNaEmbalagem());
            ps.setBoolean(4, produto.isProduzidoNaPadaria());

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

            String sql = "INSERT INTO Produto(descricao, unidadeDeMedida, quantidadeNaEmbalagem, produzidoNaPadaria) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, produto.getDescricao());
            ps.setString(2, produto.getUnidadeDeMedida());
            ps.setFloat(3, produto.getQuantidadeNaEmbalagem());
            ps.setBoolean(4, produto.isProduzidoNaPadaria());

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

}
