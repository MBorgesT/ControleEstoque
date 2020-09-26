package dao;

import aux_functions.AuxFunctions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.InstanciaProdutoMovimentacao;
import models.SaidaProdutos;

public class SaidaProdutosDAO {

    public static void insertInstancias(Connection conn, SaidaProdutos novaSaida, InstanciaProdutoMovimentacao[] novasInstancias) {
        try {
            String sql;
            PreparedStatement ps;
            ResultSet rs;
            int quantidadeJaCadastrada, novaQuantidade, idInstancia;
            for (InstanciaProdutoMovimentacao instancia : novasInstancias) {

                // update de quantidade no estoque pós saida
                sql = "SELECT quantidade, idInstanciaProduto FROM InstanciaProduto WHERE idEstoque = ? AND idProduto = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, novaSaida.getIdEstoqueOrigem());
                ps.setInt(2, instancia.getIdProduto());

                rs = ps.executeQuery();
                rs.next();

                quantidadeJaCadastrada = rs.getInt("quantidade");
                novaQuantidade = quantidadeJaCadastrada - instancia.getQuantidade(); // garantido que não vai ser < 0 pelo pelo sistema do formulário

                idInstancia = rs.getInt("idInstanciaProduto");

                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, novaQuantidade);
                ps.setInt(2, idInstancia);

                ps.executeUpdate();

                // cadastro da instancia em si
                if (novaSaida.getTipoMovimentacao() == 1) {
                    sql = "INSERT INTO InstanciaProduto(idProduto, quantidade, idMovimentacao, categoria, valorUnitarioVenda)"
                            + "VALUES(?, ?, ?, ?, ?)";
                } else {
                    sql = "INSERT INTO InstanciaProduto(idProduto, quantidade, idMovimentacao, categoria)"
                            + "VALUES(?, ?, ?, ?)";
                }

                ps = conn.prepareStatement(sql);

                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, instancia.getQuantidade());
                ps.setInt(3, novaSaida.getIdMovimentacao());
                ps.setInt(4, 1);
                if (novaSaida.getTipoMovimentacao() == 1) {
                    ps.setFloat(5, instancia.getValorUnitarioVenda());
                }

                ps.executeUpdate();

                // se a saida for de venda, fazer o update de preço
                if (novaSaida.getTipoMovimentacao() == 1) {
                    sql = "UPDATE Produto SET valorUnitarioVenda = ? WHERE idProduto = ?";
                    ps = conn.prepareStatement(sql);

                    ps.setFloat(1, instancia.getValorUnitarioVenda());
                    ps.setInt(2, instancia.getIdProduto());

                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaidaProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteInstancias(Connection conn, SaidaProdutos saidaProdutos, InstanciaProdutoMovimentacao[] instancias) {
        try {
            String sql;
            PreparedStatement ps;
            ResultSet rs;
            int quantidadeJaCadastrada, novaQuantidade, idInstancia;
            for (InstanciaProdutoMovimentacao instancia : instancias) {

                // update de quantidade no estoque pós saida
                sql = "SELECT quantidade, idInstanciaProduto FROM InstanciaProduto WHERE idEstoque = ? AND idProduto = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, saidaProdutos.getIdEstoqueOrigem());
                ps.setInt(2, instancia.getIdProduto());

                rs = ps.executeQuery();
                rs.next();

                quantidadeJaCadastrada = rs.getInt("quantidade");
                novaQuantidade = quantidadeJaCadastrada + instancia.getQuantidade();

                idInstancia = rs.getInt("idInstanciaProduto");

                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, novaQuantidade);
                ps.setInt(2, idInstancia);

                ps.executeUpdate();

                // exclusão da instancia em si
                sql = "DELETE FROM InstanciaProduto WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, instancia.getIdInstanciaProduto());

                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaidaProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean insertSaidaProdutos(SaidaProdutos novaSaida, InstanciaProdutoMovimentacao[] instancias) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "INSERT INTO Movimentacao(data, tipoMovimentacao, idEstoqueOrigem) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, AuxFunctions.convertUtilToSql(novaSaida.getData()));
            ps.setInt(2, novaSaida.getTipoMovimentacao());
            ps.setInt(3, novaSaida.getIdEstoqueOrigem());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            novaSaida.setIdMovimentacao(rs.getInt(1));

            insertInstancias(conn, novaSaida, instancias);

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaidaProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteSaidaProdutos(SaidaProdutos saidaProdutos) {
        try {
            InstanciaProdutoMovimentacao[] instancias = saidaProdutos.getInstanciasProduto();

            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            deleteInstancias(conn, saidaProdutos, instancias);
            
            String sql = "DELETE FROM Movimentacao WHERE idMovimentacao = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, saidaProdutos.getIdMovimentacao());
            
            ps.executeUpdate();
            
            conn.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaidaProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
