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
import models.InstanciaProdutoEstoque;
import models.InstanciaProdutoMovimentacao;
import models.MovimentacaoEntreEstoques;

public class MovimentacaoEntreEstoquesDAO {

    private static void insertInstancias(Connection conn, MovimentacaoEntreEstoques movimentacao, InstanciaProdutoMovimentacao[] instancias) {
        try {
            String sql;
            PreparedStatement ps;
            ResultSet rs;
            for (InstanciaProdutoMovimentacao instancia : instancias) {
                sql = "INSERT INTO InstanciaProduto(idProduto, quantidade, idMovimentacao, categoria) VALUES (?, ?, ?, ?)";

                ps = conn.prepareStatement(sql);

                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, instancia.getQuantidade());
                ps.setInt(3, movimentacao.getIdMovimentacao());
                ps.setInt(4, 1); // por causa de ser movimentação; seria 0 se fosse instancia de estoque

                ps.executeUpdate();

                /*
                Categoria 0 para checar se existe alguma instancia desse produto já em estoque;
                Caso não, cria nova instancia de estoque; caso sim, acrescenta nesse
                 */
                sql = "SELECT quantidade, idInstanciaProduto FROM InstanciaProduto WHERE idProduto = ? AND idEstoque = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, movimentacao.getIdEstoqueDestino());
                rs = ps.executeQuery();

                if (rs.next()) {
                    int quantidadeJaCadastrada = rs.getInt("quantidade");
                    int idInstancia = rs.getInt("idInstanciaProduto");

                    sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idInstanciaProduto = ?";
                    ps = conn.prepareStatement(sql);

                    ps.setInt(1, quantidadeJaCadastrada + instancia.getQuantidade());
                    ps.setInt(2, idInstancia);

                    ps.executeUpdate();
                } else {
                    sql = "INSERT INTO InstanciaProduto(idProduto, quantidade, idEstoque, categoria) VALUES (?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql);

                    ps.setInt(1, instancia.getIdProduto());
                    ps.setInt(2, instancia.getQuantidade());
                    ps.setInt(3, movimentacao.getIdEstoqueDestino());
                    ps.setInt(4, 0); // por causa de ser estoque; seria 1 se fosse instancia de movimentação

                    ps.executeUpdate();
                }

                // remoção do estoque de origem
                sql = "SELECT quantidade, idInstanciaProduto FROM InstanciaProduto WHERE idProduto = ? AND idEstoque = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, movimentacao.getIdEstoqueOrigem());
                rs = ps.executeQuery();

                rs.next();

                int quantidadeJaCadastrada = rs.getInt("quantidade");
                int idInstancia = rs.getInt("idInstanciaProduto");

                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, quantidadeJaCadastrada - instancia.getQuantidade());
                ps.setInt(2, idInstancia);

                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean insertMovimentacaoEntreEstoques(MovimentacaoEntreEstoques movimentacao, InstanciaProdutoMovimentacao[] instancias) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            String sql = "INSERT INTO Movimentacao(data, tipoMovimentacao, idEstoqueOrigem, idEstoqueDestino) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, AuxFunctions.convertUtilToSql(movimentacao.getData()));
            ps.setInt(2, movimentacao.getTipoMovimentacao());
            ps.setInt(3, movimentacao.getIdEstoqueOrigem());
            ps.setInt(4, movimentacao.getIdEstoqueDestino());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            movimentacao.setIdMovimentacao(rs.getInt(1));

            insertInstancias(conn, movimentacao, instancias);

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static void deleteInstancias(Connection conn, MovimentacaoEntreEstoques movimentacao) {
        try {
            int idEstoqueDestino = movimentacao.getIdEstoqueDestino();
            int idEstoqueOrigem = movimentacao.getIdEstoqueOrigem();
            int quantidadeJaCadastrada, novaQuantidade;
            
            ResultSet rs;
            String sql;
            PreparedStatement ps;
            
            for (InstanciaProdutoMovimentacao iMovimentacao : movimentacao.getInstanciasProduto()) {
                
                // remoção no estoque de destino
                
                sql = "SELECT quantidade FROM InstanciaProduto WHERE idEstoque = ? AND idProduto = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                
                ps.setInt(1, idEstoqueDestino);
                ps.setInt(2, iMovimentacao.getIdProduto());
                
                rs = ps.executeQuery();
                
                rs.next();
                quantidadeJaCadastrada = rs.getInt("quantidade");
                novaQuantidade = quantidadeJaCadastrada - iMovimentacao.getQuantidade();
                
                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idEstoque = ? AND idProduto = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                
                ps.setInt(1, novaQuantidade);
                ps.setInt(2, idEstoqueDestino);
                ps.setInt(3, iMovimentacao.getIdProduto());
                
                ps.executeUpdate();
                
                
                // adição no estoque de origem
                
                sql = "SELECT quantidade FROM InstanciaProduto WHERE idEstoque = ? AND idProduto = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                
                ps.setInt(1, idEstoqueOrigem);
                ps.setInt(2, iMovimentacao.getIdProduto());
                
                rs = ps.executeQuery();
                
                rs.next();
                quantidadeJaCadastrada = rs.getInt("quantidade");
                novaQuantidade = quantidadeJaCadastrada + iMovimentacao.getQuantidade();
                
                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idEstoque = ? AND idProduto = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                
                ps.setInt(1, novaQuantidade);
                ps.setInt(2, idEstoqueOrigem);
                ps.setInt(3, iMovimentacao.getIdProduto());
                
                ps.executeUpdate();
                
                
                // exclusão da instancia em si
                
                sql = "DELETE FROM InstanciaProduto WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);
                
                ps.setInt(1, iMovimentacao.getIdInstanciaProduto());
                
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimentacaoEntreEstoquesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean deleteMovimentacaoEntreEstoques(MovimentacaoEntreEstoques movimentacao) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            
            deleteInstancias(conn, movimentacao);

            String sql = "DELETE FROM Movimentacao WHERE idMovimentacao = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, movimentacao.getIdMovimentacao());
            
            ps.executeUpdate();
            
            conn.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MovimentacaoEntreEstoquesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean podeExcluir(MovimentacaoEntreEstoques movimentacao) {
        boolean flag = true;

        int idEstoque, idProduto;

        for (InstanciaProdutoMovimentacao iMovimentacao : movimentacao.getInstanciasProduto()) {
            idEstoque = movimentacao.getIdEstoqueDestino();
            idProduto = iMovimentacao.getIdProduto();

            InstanciaProdutoEstoque iEstoque = InstanciaProdutoEstoqueDAO.selectInstanciaProdutoEstoquePorEstoqueEProduto(idEstoque, idProduto);

            if (iEstoque.getQuantidade() < iMovimentacao.getQuantidade()) {
                flag = false;
                break;
            }
        }

        return flag;
    }

}
