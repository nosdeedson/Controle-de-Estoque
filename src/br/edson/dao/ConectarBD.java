package br.edson.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConectarBD {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/almoxarifado1";
	private static final String USER = "root";
	private static final String SENHA = "surfando!@#";
	
	public static Connection PegarConexao() {
		 
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, SENHA);
		} catch (ClassNotFoundException | SQLException e ) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " Falha na conexão com o Banco de dados!!\n Contate o desenvolvedor do software.");
			throw new RuntimeException("erro na conexao",e);
			
		}
	}
	
	public static void fechaConexao( Connection con) {
		if( con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.err.println("erro: " + e);
			}
		}
	}
	public static void fechaConexao( Connection con, PreparedStatement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fechaConexao(con);
		
	}
	
	public static void fechaConexao( Connection con, PreparedStatement stmt, ResultSet result) {
		
		if( result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		fechaConexao(con, stmt);
	}
}
