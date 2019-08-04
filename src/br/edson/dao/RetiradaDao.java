package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edson.model.Produto;
import br.edson.model.Retirada;

public class RetiradaDao {
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;

	public static int inserirRetirada(List<Retirada> retiradas, List<Produto> listaProdutos) {
		int gravado = 0;
		String sql = "INSERT INTO retirada_produto (codigo_produto, codigo_responsavel, quantidade)" + "VALUES (?,?,?)";

		Connection con = ConectarBD.PegarConexao();
		try {
			for (int i = 0; i < retiradas.size(); i++) {
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, retiradas.get(i).getCodigoProduto());
				stmt.setInt(2, retiradas.get(i).getCodigoResponsavel());
				stmt.setInt(3, retiradas.get(i).getQtd());
				gravado = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gravado = -1;
		}finally {
			ConectarBD.fechaConexao(con, stmt);
		}

		return gravado;
	}
}
