package br.edson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.RepositoryIdHelper;

import br.edson.model.ResponsavelRetirada;

public class ResponsavelDao {

	private static PreparedStatement stmt = null;
	private static ResultSet result = null;
	
	public static int deletarResponsave( int codigoResponsavel) {
		int deletado =0;
		String sql = "DELETE FROM responsavel WHERE codigo=" + codigoResponsavel;
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
	public static int editarResponsavel( ResponsavelRetirada resp) {
		int editado = 0;
		Connection con = ConectarBD.PegarConexao();
		String sql="UPDATE responsavel SET nome='"+ resp.getNome() +"' WHERE codigo=" + resp.getCodigo();
		
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
	public static int inserirResponsavel(ResponsavelRetirada resp) {
		int inserido = 0;
		String sql = "INSERT INTO responsavel(nome,codigo) values(?, ?)";
		Connection con = ConectarBD.PegarConexao();
		if (!verificaResponsavel(resp)) {
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, resp.getNome());
				stmt.setInt(2, resp.getCodigo());
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
	public static List<ResponsavelRetirada> listaResponsaveis(){
		List<ResponsavelRetirada> listaResp = new ArrayList<ResponsavelRetirada>();
		
		String sql ="SELECT *FROM responsavel ORDER BY nome";
		Connection con = ConectarBD.PegarConexao();
		
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ResponsavelRetirada r = new ResponsavelRetirada();
				r.setCodigo(result.getInt("codigo"));
				r.setNome(result.getString("nome"));
				listaResp.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConectarBD.fechaConexao(con, stmt, result);
		}
				
		return listaResp;
	}
	private static boolean verificaResponsavel(ResponsavelRetirada resp) {
		boolean existe = false;
		String sql = "SELECT *FROM responsavel";

		Connection con = ConectarBD.PegarConexao();
		try {
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql);
			while (result.next()) {
				if (resp.getNome().equals(result.getString("nome"))) {
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
}
