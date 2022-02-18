package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conn = DriverManager.getConnection("jdbc:mysql://localhost/TP2","root","");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost/bancoteste", "root","");
			} catch(ClassNotFoundException e) {
				System.out.println("Erro");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	public static void cloesePreparedStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
