package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edson.model.RelatorioEntrada;

public class RelatorioEntradaDao {
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;
	public static  List<RelatorioEntrada> listaEntradas( long identificacaoPessoa, int codigoProduto, String dataInicio, String dataFinal){
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>();// ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd"
				+ " FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa"
				+ " WHERE ep.data_entrada BETWEEN '" +dataInicio +"' AND '"+dataFinal +"'" +
				" AND ep.identificacao_pessoa="+identificacaoPessoa +
				" AND p.codigo="+codigoProduto;
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	public static  List<RelatorioEntrada> listaEntradasFornecedor( long identificacaoPessoa, String dataInicio, String dataFinal){
		// lista as entradas de acordo com um fornecedor 
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>(); // ok
		
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa"
				+ " WHERE ep.data_entrada BETWEEN '" +dataInicio +"' AND '"+dataFinal+"'" 
				+ " AND ep.identificacao_pessoa="+identificacaoPessoa  
				+ " GROUP BY p.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	public static  List<RelatorioEntrada> listaEntradasProduto( int codigoProduto, String dataInicio, String dataFinal){
		// lista as entradas de acordo com um Produto
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>(); // ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa"
				+ " WHERE ep.data_entrada BETWEEN '" +dataInicio +"' AND '"+dataFinal +"'"
				+ " AND p.codigo="+codigoProduto 
				+ " GROUP  BY ep.identificacao_pessoa ORDER BY f.nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	public static  List<RelatorioEntrada> listaEntradas(String dataInicio, String dataFinal){
		// lista as entradas de acordo com um Produto
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>();// ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa"
				+ " WHERE ep.data_entrada BETWEEN '" +dataInicio +"' AND '"+dataFinal 
				+"' GROUP BY ep.identificacao_pessoa, codigo_produto ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	// busca entrada sem data de referencia
	public static  List<RelatorioEntrada> listaEntradas( long identificacaoPessoa, int codigoProduto){
		System.out.println(identificacaoPessoa);
		System.out.println(codigoProduto);
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>(); //ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa"
				+ " WHERE ep.identificacao_pessoa="+identificacaoPessoa 
				+ " AND p.codigo="+codigoProduto;
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	
	public static  List<RelatorioEntrada> listaEntradas(){
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>();// ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa  "
				+ " Group by f.nome, p.codigo ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	
	public static  List<RelatorioEntrada> listaEntradasFornecedor( long identificacaoPessoa){ //lista entrada de acordo com o fornecedor
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>();//ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa "
				+ " WHERE ep.identificacao_pessoa="+identificacaoPessoa 
				+ " GROUP BY p.codigo ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}
	public static  List<RelatorioEntrada> listaEntradasProdutos( int codigoProduto ){// lista entrada de acordo com um produto
		List<RelatorioEntrada> listaEntradas = new ArrayList<RelatorioEntrada>();// ok
		String sql = "SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, SUM(qtd) qtd FROM entrada_produto ep "
				+ " INNER JOIN produto p ON p.codigo=ep.codigo_produto"
				+ " INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa"
				+ " WHERE codigo_produto="+codigoProduto
				+ " GROUP BY f.nome ORDER BY p.nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				RelatorioEntrada r = new RelatorioEntrada();
				r.setDataEntrada(result.getString("data_entrada"));
				r.setNomeFornecedor(result.getString("fornecedor"));
				r.setNomeProduto(result.getString("nome"));
				r.setNumeroNota(result.getInt("numero_nf"));
				r.setQuantidade(result.getInt("qtd"));
				listaEntradas.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaEntradas;
	}

}// fim classe
