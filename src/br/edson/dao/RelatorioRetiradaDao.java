package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import br.edson.model.RelatorioRetirada;

public class RelatorioRetiradaDao {
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;

	public static List<RelatorioRetirada> listaRetiradas(long idResp, int codigoProduto, String dataInicio,
			String dataFinal) {
		List<RelatorioRetirada> listaRetiradas = new ArrayList<RelatorioRetirada>();// ok

		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, Sum(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel" + " WHERE rp.data_retirada BETWEEN '"
				+ dataInicio + "' AND '" + dataFinal + "'" + " AND rp.codigo_responsavel=" + idResp + " AND p.codigo="
				+ codigoProduto;
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada(result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("responsavel"));
				listaRetiradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaRetiradas;
	}

	public static List<RelatorioRetirada> listaRetiradas(long idResp, String dataInicio, String dataFinal) {
		// lista as entradas de acordo com um responsavel
		System.out.println(" aqui aqui ó");
		List<RelatorioRetirada> listaRetiradas = new ArrayList<RelatorioRetirada>();// ok
		String sql = "SELECT p.nome, data_retirada, r.nome, Sum(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel" + " WHERE rp.data_retirada BETWEEN '"
				+ dataInicio + "' AND '" + dataFinal + "'" + " AND rp.codigo_responsavel=" + idResp
				+ " GROUP BY p.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada(result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("r.nome"));
				listaRetiradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaRetiradas;
	}

	public static List<RelatorioRetirada> listaRetiradasProduto(int codigoProduto, String dataInicio,
			String dataFinal) {
		// lista as entradas de acordo com um Produto

		List<RelatorioRetirada> listaEntradas = new ArrayList<RelatorioRetirada>();// ok
		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, SUM(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel" + " WHERE rp.data_retirada BETWEEN '"
				+ dataInicio + "' AND '" + dataFinal + "'" + " AND p.codigo=" + codigoProduto
				+ " GROUP BY r.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada(result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("responsavel"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}

	public static List<RelatorioRetirada> listaRetiradas(String dataInicio, String dataFinal) {
		// lista as entradas de acordo com um Produto
		List<RelatorioRetirada> listaEntradas = new ArrayList<RelatorioRetirada>();// ok
		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, SUM(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel" + " WHERE rp.data_retirada BETWEEN '"
				+ dataInicio + "' AND '" + dataFinal + "' GROUP BY r.nome, p.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada(result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("responsavel"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}

	// busca entrada sem data de referencia
	public static List<RelatorioRetirada> listaRetiradas(long idResp, int codigoProduto) {
		List<RelatorioRetirada> listaRetiradas = new ArrayList<RelatorioRetirada>();//ok

		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, SUM(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel" + " WHERE rp.codigo_responsavel="
				+ idResp + " AND p.codigo=" + codigoProduto + " ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			if (result.getFetchSize() != 0) {
				while (result.next()) {
					RelatorioRetirada r = new RelatorioRetirada();
					r.setdataRetirada(result.getString("data_retirada"));
					r.setNomeProduto(result.getString("p.nome"));
					r.setQuantidade(result.getInt("quantidade"));
					r.setResponsavel(result.getString("responsavel"));
					listaRetiradas.add(r);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}

		return listaRetiradas;
	}

	public static List<RelatorioRetirada> listaRetiradas() {
		List<RelatorioRetirada> listaEntradas = new ArrayList<RelatorioRetirada>();//ok
		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, SUM(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel  "
				+ " GROUP BY p.nome, r.nome ORDER BY p.nome ";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada(result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("responsavel"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}

	public static List<RelatorioRetirada> listaRetiradasResponsavel(long idResp) { // lista entrada de acordo com o
																					// responsavel
		List<RelatorioRetirada> listaEntradas = new ArrayList<RelatorioRetirada>(); // ok
		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, SUM(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel " + " WHERE rp.codigo_responsavel="
				+ idResp + " GROUP BY p.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada( result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("responsavel"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}

	public static List<RelatorioRetirada> listaRetiradasProdutos(int codigoProduto) {// lista entrada de acordo com um
																						// produto
		List<RelatorioRetirada> listaEntradas = new ArrayList<RelatorioRetirada>();//ok
		String sql = "SELECT p.nome, data_retirada, r.nome responsavel, SUM(rp.quantidade) quantidade FROM retirada_produto rp "
				+ " INNER JOIN produto p ON p.codigo=rp.codigo_produto"
				+ " INNER JOIN responsavel r ON r.codigo=rp.codigo_responsavel" + " WHERE codigo_produto="
				+ codigoProduto + " GROUP BY r.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				RelatorioRetirada r = new RelatorioRetirada();
				r.setdataRetirada(result.getString("data_retirada"));
				r.setNomeProduto(result.getString("p.nome"));
				r.setQuantidade(result.getInt("quantidade"));
				r.setResponsavel(result.getString("responsavel"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
}
