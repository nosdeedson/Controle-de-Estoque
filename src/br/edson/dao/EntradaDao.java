package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edson.model.EntradaProduto;
import br.edson.model.Produto;

public class EntradaDao {
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;

	public static int inserirEntrada(List<EntradaProduto> listaEntradaProdutos, List<Produto> listaProd) {
		int inserido = 0;

		String sql = "INSERT INTO entrada_produto(data_entrada, numero_nf, codigo_produto, identificacao_pessoa, qtd)"
				+ " VALUES( ?,?,?,?,?)";

		
		for (int i = 0; i < listaEntradaProdutos.size(); i++) {
			Connection con = ConectarBD.PegarConexao();
			try {
				// grava a entrada de produtos
				stmt = null;
				stmt = con.prepareStatement(sql);
				stmt.setString(1, listaEntradaProdutos.get(i).getDataEntrada());
				stmt.setInt(2, listaEntradaProdutos.get(i).getNumeroNF());
				stmt.setInt(3, listaEntradaProdutos.get(i).getCodigoProduto());
				stmt.setLong(4, listaEntradaProdutos.get(i).getIdentificacaopessoa());
				stmt.setInt(5, listaEntradaProdutos.get(i).getQtdEntrada());
				inserido = stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				inserido = -1;
			} finally {
				ConectarBD.fechaConexao(con, stmt);
			}
		}

		return inserido;
	}
}
