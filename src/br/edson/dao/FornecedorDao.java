package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.edson.model.Fornecedor;


public class FornecedorDao {
	private static PreparedStatement stmt = null;
	private static ResultSet result = null;

	public static int cadastrarFornecedor(Fornecedor forne) {
		int cadastrado = 0; // zero fornecedor existente, valor maior que 0 fornecedor cadastrado, -1 falha
							// no banco

		String sql = "INSERT INTO fornecedor values(?,?,?,?)";
		Connection con = ConectarBD.PegarConexao();
		if (!verificaFornecedor(forne)) {
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, forne.getNome());
				stmt.setLong(2, forne.getFone());
				stmt.setString(3, forne.getEmail());
				stmt.setLong(4, forne.getIdentificacaoPessoa());
				cadastrado = stmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
				cadastrado = -1;
			} finally {
				ConectarBD.fechaConexao(con, stmt);
			}
		}
		return cadastrado;
	}

	public static int deletarFornecedor( long identificacaoPessoa) {
		int deletado = 0;
		String sql = "DELETE FROM fornecedor WHERE identificacao_pessoa="+ identificacaoPessoa;
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt= con.prepareStatement(sql);
			deletado = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			deletado =  -1;
		}finally {
			ConectarBD.fechaConexao(con, stmt);
		}
		return deletado;
	}
	
	public static int editarFornecedor( Fornecedor ff) {
		int editado = 0;
		
		String sql = "UPDATE fornecedor SET nome='" + ff.getNome() + "', fone=" + ff.getFone() + 
				", email='" + ff.getEmail() +"' WHERE identificacao_pessoa=" + ff.getIdentificacaoPessoa();
		
		Connection con = ConectarBD.PegarConexao();
		try {
			stmt = con.prepareStatement(sql);
			editado = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			editado = -1;
		}finally {
			ConectarBD.fechaConexao(con, stmt);
		}
		
		return editado;
	}

	// lista os fornecedores
	public static List<Fornecedor> listaNomesFornecedores(List<Fornecedor> f, boolean tipoPessoa) {
		String sql;
		Connection con = ConectarBD.PegarConexao();
		if(tipoPessoa)
		sql = "SELECT *FROM fornecedor WHERE identificacao_pessoa BETWEEN '01111111111' AND '99999999999' ORDER BY nome";
		else
			sql = "SELECT *FROM fornecedor WHERE identificacao_pessoa BETWEEN '01111111111111' AND '99999999999999' ORDER BY nome";
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				Fornecedor forn = new Fornecedor();
				forn.setNome(result.getString("nome"));
				forn.setFone(result.getLong("fone"));
				forn.setEmail(result.getString("email"));
				forn.setIdentificacaoPessoa(result.getLong("identificacao_pessoa"));
				f.add(forn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}

		return f;
	}

	private static boolean verificaFornecedor(Fornecedor f) {
		boolean existe = false;
		String sql = "SELECT *FROM fornecedor";
		Connection con = ConectarBD.PegarConexao();

		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				if (result.getLong("identificacao_pessoa") == f.getIdentificacaoPessoa()) {
					existe = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}

		return existe;
	}


}// fim classe
