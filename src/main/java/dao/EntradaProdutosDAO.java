package dao;

import aux_functions.AuxFunctions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.EntradaProdutos;
import models.InstanciaProdutoEstoque;
import models.InstanciaProdutoMovimentacao;

public class EntradaProdutosDAO {

    public static EntradaProdutos[] selectTodasCompras() {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movimentacao WHERE tipoMovimentacao = 4");
            ArrayList<EntradaProdutos> arrayListEntradas = new ArrayList<>();

            while (rs.next()) {
                arrayListEntradas.add(new EntradaProdutos(
                        rs.getInt("idFornecedor"),
                        rs.getInt("idEstoqueDestino"),
                        rs.getInt("tipoMovimentacao"),
                        rs.getInt("idMovimentacao"),
                        rs.getDate("data")
                ));
            }

            conn.close();

            return arrayListEntradas.toArray(new EntradaProdutos[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static EntradaProdutos[] selectComprasPorIdFornecedor(int idFornecedor) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "SELECT * FROM Movimentacao WHERE tipoMovimentacao = 4 AND idFornecedor = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idFornecedor);
            ResultSet rs = ps.executeQuery();

            ArrayList<EntradaProdutos> arrayListEntradas = new ArrayList<>();

            while (rs.next()) {
                arrayListEntradas.add(new EntradaProdutos(
                        rs.getInt("idFornecedor"),
                        rs.getInt("idEstoqueDestino"),
                        rs.getInt("tipoMovimentacao"),
                        rs.getInt("idMovimentacao"),
                        rs.getDate("data")
                ));
            }

            conn.close();

            return arrayListEntradas.toArray(new EntradaProdutos[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void insertInstancias(Connection conn, EntradaProdutos entradaProdutos, InstanciaProdutoMovimentacao[] instancias) {
        try {
            String sql;
            PreparedStatement ps;
            ResultSet rs;
            for (InstanciaProdutoMovimentacao instancia : instancias) {
                if (entradaProdutos.getTipoMovimentacao() == 4) {
                    sql = "INSERT INTO InstanciaProduto(idProduto, quantidade, idMovimentacao, categoria, valorUnitarioPago) VALUES (?, ?, ?, ?, ?)";
                } else {
                    sql = "INSERT INTO InstanciaProduto(idProduto, quantidade, idMovimentacao, categoria) VALUES (?, ?, ?, ?)";
                }

                ps = conn.prepareStatement(sql);

                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, instancia.getQuantidade());
                ps.setInt(3, entradaProdutos.getIdMovimentacao());
                ps.setInt(4, 1); // por causa de ser movimentação; seria 0 se fosse instancia de estoque
                if (entradaProdutos.getTipoMovimentacao() == 4) {
                    ps.setFloat(5, instancia.getValorUnitarioPago());
                }

                ps.executeUpdate();

                /*
                Categoria 0 para checar se existe alguma instancia desse produto já em estoque;
                Caso não, cria nova instancia de estoque; caso sim, acrescenta nesse
                 */
                sql = "SELECT quantidade, idInstanciaProduto FROM InstanciaProduto WHERE idProduto = ? AND idEstoque = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, entradaProdutos.getIdEstoqueDestino());
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
                    ps.setInt(3, entradaProdutos.getIdEstoqueDestino());
                    ps.setInt(4, 0); // por causa de ser estoque; seria 1 se fosse instancia de movimentação

                    ps.executeUpdate();
                }

                if (entradaProdutos.getTipoMovimentacao() == 4) {
                    sql = "UPDATE Produto SET valorUnitarioPago = ? WHERE idProduto = ?";
                    ps = conn.prepareStatement(sql);

                    ps.setFloat(1, instancia.getValorUnitarioPago());
                    ps.setInt(2, instancia.getIdProduto());

                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void updateInstancias(Connection conn, EntradaProdutos entradaProdutos, InstanciaProdutoMovimentacao[] novasInstancias, int tipoMovimentacaoAntes, int idEstoqueDestinoAntes) {
        try {
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            InstanciaProdutoMovimentacao[] instanciasAntigas = InstanciaProdutoMovimentacaoDAO.selectInstanciasProdutoMovimentacaoPorIdMovimentacao(entradaProdutos.getIdMovimentacao(), tipoMovimentacaoAntes);

            for (InstanciaProdutoMovimentacao instancia : instanciasAntigas) {
                sql = "SELECT quantidade, idInstanciaProduto FROM InstanciaProduto WHERE idProduto = ? AND idEstoque = ? AND categoria = 0";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, instancia.getIdProduto());
                ps.setInt(2, idEstoqueDestinoAntes);

                rs = ps.executeQuery();

                rs.next();

                int quantidadeJaCadastrada = rs.getInt("quantidade");
                int idInstanciaEstoque = rs.getInt("idInstanciaProduto");

                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, quantidadeJaCadastrada - instancia.getQuantidade());
                ps.setInt(2, idInstanciaEstoque);

                ps.executeUpdate();
            }

            sql = "DELETE FROM InstanciaProduto WHERE idMovimentacao = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, entradaProdutos.getIdMovimentacao());

            ps.executeUpdate();

            insertInstancias(conn, entradaProdutos, novasInstancias);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void deleteInstancias(Connection conn, EntradaProdutos entradaProdutos) {
        try {
            InstanciaProdutoMovimentacao[] instanciasMovimentacao = entradaProdutos.getInstanciasProduto();
            InstanciaProdutoEstoque iEstoque;

            String sql;
            PreparedStatement ps;

            int idEstoque = entradaProdutos.getIdEstoqueDestino();
            int idProduto;

            for (InstanciaProdutoMovimentacao iMovimentacao : instanciasMovimentacao) {

                // alteração de equantidade no estoque de destino
                idProduto = iMovimentacao.getIdProduto();
                iEstoque = InstanciaProdutoEstoqueDAO.selectInstanciaProdutoEstoquePorEstoqueEProduto(idEstoque, idProduto);

                sql = "UPDATE InstanciaProduto SET quantidade = ? WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, iEstoque.getQuantidade() - iMovimentacao.getQuantidade());
                ps.setInt(2, iEstoque.getIdInstanciaProduto());

                ps.executeUpdate();

                // remoção da instância em si
                sql = "DELETE FROM InstanciaProduto WHERE idInstanciaProduto = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, iMovimentacao.getIdInstanciaProduto());

                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean insertEntradaProdutos(EntradaProdutos entradaProdutos, InstanciaProdutoMovimentacao[] instancias) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql;
            if (entradaProdutos.getTipoMovimentacao() == 4) {
                sql = "INSERT INTO Movimentacao(data, tipoMovimentacao, idEstoqueDestino, idFornecedor) VALUES (?, ?, ?, ?)";
            } else {
                sql = "INSERT INTO Movimentacao(data, tipoMovimentacao, idEstoqueDestino) VALUES (?, ?, ?)";
            }

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, AuxFunctions.convertUtilToSql(entradaProdutos.getData()));
            ps.setInt(2, entradaProdutos.getTipoMovimentacao());
            ps.setInt(3, entradaProdutos.getIdEstoqueDestino());
            if (entradaProdutos.getTipoMovimentacao() == 4) {
                ps.setInt(4, entradaProdutos.getIdFornecedor());
            }

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            entradaProdutos.setIdMovimentacao(rs.getInt(1));

            insertInstancias(conn, entradaProdutos, instancias);

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateEntradaProdutos(EntradaProdutos entradaProdutos, InstanciaProdutoMovimentacao[] novasInstancias, int tipoMovimentacaoAntes, int idEstoqueDestinoAntes) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);

            String sql = "UPDATE Movimentacao SET tipoMovimentacao = ?, idEstoqueDestino = ?, idFornecedor = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, entradaProdutos.getTipoMovimentacao());
            ps.setInt(2, entradaProdutos.getIdEstoqueDestino());
            if (entradaProdutos.getIdFornecedor() == -1) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, entradaProdutos.getIdFornecedor());
            }

            ps.executeUpdate();

            updateInstancias(conn, entradaProdutos, novasInstancias, tipoMovimentacaoAntes, idEstoqueDestinoAntes);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EntradaProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteEntradaProdutos(EntradaProdutos entradaProdutos) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            
            deleteInstancias(conn, entradaProdutos);
            
            String sql = "DELETE FROM Movimentacao WHERE idMovimentacao = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, entradaProdutos.getIdMovimentacao());
            
            ps.executeUpdate();
            
            conn.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean podeExcluir(EntradaProdutos entradaProdutos) {
        int idEstoqueDestino = entradaProdutos.getIdEstoqueDestino();
        int idProduto;
        boolean flag = true;
        InstanciaProdutoEstoque iEstoque;
        for (InstanciaProdutoMovimentacao iMovimentacao : entradaProdutos.getInstanciasProduto()) {

            idProduto = iMovimentacao.getIdProduto();
            iEstoque = InstanciaProdutoEstoqueDAO.selectInstanciaProdutoEstoquePorEstoqueEProduto(idEstoqueDestino, idProduto);

            if (iEstoque.getQuantidade() < iMovimentacao.getQuantidade()) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static boolean podeFazerUpdate(EntradaProdutos entradaProdutos, InstanciaProdutoMovimentacao[] instanciasAntigas, InstanciaProdutoMovimentacao[] novasInstancias) {
        boolean flag = true;

        for (InstanciaProdutoMovimentacao instanciaMovimentacaoNova : novasInstancias) {
            int idProduto = instanciaMovimentacaoNova.getIdProduto();

            InstanciaProdutoMovimentacao instanciaMovimentacaoAntiga = null;
            for (InstanciaProdutoMovimentacao i : instanciasAntigas) {
                if (i.getIdProduto() == idProduto) {
                    instanciaMovimentacaoAntiga = i;
                    break;
                }
            }

            if (instanciaMovimentacaoAntiga != null) {
                int idEstoque = entradaProdutos.getIdEstoqueDestino();
                InstanciaProdutoEstoque instanciaEstoque = InstanciaProdutoEstoqueDAO.selectInstanciaProdutoEstoquePorEstoqueEProduto(idEstoque, idProduto);

                if (instanciaMovimentacaoAntiga.getQuantidade() - instanciaMovimentacaoNova.getQuantidade() > instanciaEstoque.getQuantidade()) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
}
