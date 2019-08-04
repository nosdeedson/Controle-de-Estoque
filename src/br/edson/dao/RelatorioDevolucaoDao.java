package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edson.model.RelatorioDevolucao;

public class RelatorioDevolucaoDao {
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;
	
	public static List<RelatorioDevolucao> gerarRelatorioDevolucao() {
		List<RelatorioDevolucao> ret = new ArrayList<RelatorioDevolucao>();
		String sql = "SELECT p.nome, r.nome, SUM(dp.quantidade) quantidade FROM responsavel r "
				+ " INNER JOIN devolucao_produto dp ON dp.codigo_responsavel=r.codigo"
				+ " INNER JOIN produto p ON p.codigo=codigo_produto "
				+ " GROUP BY p.nome, r.nome ORDER BY r.nome";
		Connection con = ConectarBD.PegarConexao();
		try {
			stmt = con.prepareStatement(sql);
			result= stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioDevolucao r = new RelatorioDevolucao();
				r.setNomeProduto(result.getString("p.nome"));
				r.setNomeResponsavel(result.getString("r.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				ret.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return ret;	
	}
}
