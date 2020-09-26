package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.InstanciaProdutoMovimentacao;

public class InstanciaProdutoMovimentacaoDAO {

    public static InstanciaProdutoMovimentacao[] selectInstanciasProdutoMovimentacaoPorIdMovimentacao(int idMovimentacao, int tipoMovimentacao) {
        try {
            Connection conn = DriverManager.getConnection(DAOPaths.connectionParam, DAOPaths.user, DAOPaths.password);
            String sql = "SELECT * FROM InstanciaProduto WHERE idMovimentacao = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idMovimentacao);
            ResultSet rs = ps.executeQuery();

            ArrayList<InstanciaProdutoMovimentacao> arrayListInstancias = new ArrayList<>();

            while (rs.next()) {
                switch (tipoMovimentacao) {
                    case 4:
                        arrayListInstancias.add(new InstanciaProdutoMovimentacao(
                                rs.getFloat("valorUnitarioPago"),
                                rs.getInt("idInstanciaProduto"),
                                rs.getInt("idProduto"),
                                rs.getInt("quantidade")
                        )); break;
                    case 1:
                        arrayListInstancias.add(new InstanciaProdutoMovimentacao(
                                rs.getFloat("valorUnitarioVenda"),
                                rs.getInt("idInstanciaProduto"),
                                rs.getInt("idProduto"),
                                rs.getInt("quantidade")
                        )); break;
                    default:
                        arrayListInstancias.add(new InstanciaProdutoMovimentacao(
                                rs.getInt("idInstanciaProduto"),
                                rs.getInt("idProduto"),
                                rs.getInt("quantidade")
                        )); break;
                }
            }

            conn.close();

            return arrayListInstancias.toArray(new InstanciaProdutoMovimentacao[0]);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
