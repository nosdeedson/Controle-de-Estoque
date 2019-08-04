package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edson.model.Devolucao;
import br.edson.model.Produto;

public class DevolucaoDao {
	
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;
	
	public static int inserirDevolucao( List<Devolucao> devolucao, List<Produto> listaProdutos) {
		int inserido = 0;
		String sql = "INSERT INTO devolucao_produto (codigo_produto, codigo_responsavel, quantidade)" + "VALUES (?,?,?)";

		Connection con = ConectarBD.PegarConexao();
		try {
			for (int i = 0; i < devolucao.size(); i++) {
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, devolucao.get(i).getCodigoProduto());
				stmt.setInt(2, devolucao.get(i).getCodigoResponsavel());
				stmt.setInt(3, devolucao.get(i).getQtd());
				inserido = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inserido = -1;
		}finally {
			ConectarBD.fechaConexao(con, stmt);
		}
		return inserido;
	}
}
