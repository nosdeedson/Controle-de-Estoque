package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edson.model.Produto;

public class ProdutoDao {
	
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;
	
	public static int deletarProduto( int codigoProduto) {
		int deletado = 0;
		String sql = "DELETE FROM produto WHERE codigo=" + codigoProduto;
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			deletado = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			deletado = -1;
		}finally {
			ConectarBD.fechaConexao(con, stmt);
		}
		
		return deletado;
	}
	public static int inserirProduto( Produto prod) {
		int valida = 0; // 0  material não inserido outro valor material inserido 
		Connection con = ConectarBD.PegarConexao();
		
		if(verificaProdutoExiste(prod)) {
			
			String sql = "INSERT INTO produto( nome, quantidade) values(?,?)";
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, prod.getNome());
				stmt.setInt(2, prod.getQuantidade());
				valida = stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConectarBD.fechaConexao(con, stmt);
			}
		
		}
		
		return valida;
	}
	
	public static int editarProduto( Produto prod) {
		int editado = 0;
		
		String sql = "UPDATE produto SET nome='" + prod.getNome() + "', quantidade="+ prod.getQuantidade() +
				" WHERE codigo=" + prod.getCodigo();
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			editado = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt);
		}
		
		return editado;
	}
	public static List<Produto> listaProdutos() {
		List<Produto> listaProdutos = new ArrayList<Produto>();
		Produto prod;
		String sql = "SELECT *FROM produto ORDER BY nome";
		
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				prod = new Produto();
				prod.setCodigo(result.getInt("codigo"));
				prod.setNome(result.getString("nome"));
				prod.setQuantidade(result.getInt("quantidade"));
				listaProdutos.add(prod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		return listaProdutos;
	}
	private static boolean verificaProdutoExiste( Produto prod) {
		boolean tipoOcorrencia =true; // true  material existe, false material não existe, 0 erro
		String sql = "SELECT * FROM produto";
		
		Connection con = ConectarBD.PegarConexao();
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				
				if(prod.getNome().equals(result.getString("nome")) ) {
					tipoOcorrencia = false;
					break;
				};
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
		
		return tipoOcorrencia;
	}

}
