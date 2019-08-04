package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edson.model.PrevisaoConsumo;

public class PrevisaoConsumoDao {
	private static PreparedStatement stmt = null;
	private static ResultSet res = null;
	
	public static List<PrevisaoConsumo> previsaoConsumo( int qtdmeses){
		
		List<PrevisaoConsumo> previsaoConsumo = new ArrayList<PrevisaoConsumo>();
		String sql="SELECT p.nome, SUM(rp.quantidade) quantidade FROM produto p " + 
				" INNER JOIN retirada_produto rp ON codigo=codigo_produto " + 
				" WHERE data_retirada BETWEEN SUBDATE( NOW(), INTERVAL 48 MONTH) AND NOW()" + 
				" GROUP BY p.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();
		try {
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery(sql);
			while( res.next()) {
				PrevisaoConsumo pc = new PrevisaoConsumo();
				pc.setNomeProduto(res.getString("p.nome"));
				pc.setQuantidade(res.getInt("quantidade"));
				previsaoConsumo.add(pc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return previsaoConsumo = null;
		}finally {
			ConectarBD.fechaConexao(con, stmt, res);
		}
		
		return previsaoConsumo;
	}
}
