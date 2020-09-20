package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.EntradaProdutos;
import models.Movimentacao;
import models.MovimentacaoEntreEstoques;
import models.SaidaProdutos;

public class MovimentacaoDAO {

    public static Movimentacao[] selectTodasMovimentacoes() {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movimentacao");
            ArrayList<Movimentacao> arrayListMovimentacao = new ArrayList<>();

            while (rs.next()) {
                int tipoMovimentacao = rs.getInt("tipoMovimentacao");
                Movimentacao movimentacao = null;

                switch (tipoMovimentacao) {
                    case 0:
                        movimentacao = new SaidaProdutos(
                                rs.getInt("idEstoqueOrigem"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("dataHora"),
                                rs.getInt("tipoMovimentacao")
                        );
                        break;
                    case 1:
                        movimentacao = new SaidaProdutos(
                                rs.getInt("idEstoqueOrigem"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("dataHora"),
                                rs.getInt("tipoMovimentacao")
                        );
                        break;
                    case 2:
                        movimentacao = new MovimentacaoEntreEstoques(
                                rs.getInt("idEstoqueOrigem"),
                                rs.getInt("idEstoqueDestino"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("data"),
                                rs.getInt("tipoMovimentacao")
                        );
                        break;
                    case 3:
                        movimentacao = new EntradaProdutos(
                                -1,
                                rs.getInt("idEstoqueDestino"),
                                rs.getInt("tipoMovimentacao"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("data")
                        );
                    case 4:
                        movimentacao = new EntradaProdutos(
                                rs.getInt("idFornecedor"),
                                rs.getInt("idEstoqueDestino"),
                                rs.getInt("tipoMovimentacao"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("data")
                        );
                }
                
                arrayListMovimentacao.add(movimentacao);
            }

            conn.close();

            return arrayListMovimentacao.toArray(new Movimentacao[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Movimentacao[] selectMovimentacoesPorEstoque(int idEstoque) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            
            String sql = "SELECT * FROM Movimentacao WHERE idEstoqueOrigem = ? or idEstoqueDestino = ?"; 
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idEstoque);
            ps.setInt(2, idEstoque);
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Movimentacao> arrayListMovimentacao = new ArrayList<>();

            while (rs.next()) {
                int tipoMovimentacao = rs.getInt("tipoMovimentacao");
                Movimentacao movimentacao = null;

                switch (tipoMovimentacao) {
                    case 0:
                        movimentacao = new SaidaProdutos(
                                rs.getInt("idEstoqueOrigem"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("dataHora"),
                                rs.getInt("tipoMovimentacao")
                        );
                        break;
                    case 1:
                        movimentacao = new SaidaProdutos(
                                rs.getInt("idEstoqueOrigem"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("dataHora"),
                                rs.getInt("tipoMovimentacao")
                        );
                        break;
                    case 2:
                        movimentacao = new MovimentacaoEntreEstoques(
                                rs.getInt("idEstoqueOrigem"),
                                rs.getInt("idEstoqueDestino"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("data"),
                                rs.getInt("tipoMovimentacao")
                        );
                        break;
                    case 3:
                        movimentacao = new EntradaProdutos(
                                -1,
                                rs.getInt("idEstoqueDestino"),
                                rs.getInt("tipoMovimentacao"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("data")
                        );
                    case 4:
                        movimentacao = new EntradaProdutos(
                                rs.getInt("idFornecedor"),
                                rs.getInt("idEstoqueDestino"),
                                rs.getInt("tipoMovimentacao"),
                                rs.getInt("idMovimentacao"),
                                rs.getDate("data")
                        );
                }
                
                arrayListMovimentacao.add(movimentacao);
            }

            conn.close();

            return arrayListMovimentacao.toArray(new Movimentacao[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
