package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null;// cnectar ao banco de dados
		Statement st= null; // preparar uma consulta sql pra buscar todos os departamentos		
		ResultSet rs = null;// resultado da consulta será guarado na variavel # ResultSet rs #
		
		try {// bloco try para prevenir exceções ao acessar um banco externo
			
			conn =DB.getConnection();// conectando ao banco de dados
			st = conn.createStatement();
	rs = st.executeQuery("select * from department"); // comando para executar uma consulta e o resultado e armazenado na variavel rs
	// lembrando que os dados armazenados na variavel rs terá forma de tabela
	
		while(rs.next()){//comando para percorrer a tabela enquanto rs for diferente de nulo
			System.out.println(rs.getInt("Id") +", "+rs.getString("Name"));            
			
		}
	
}
		catch(SQLException e) {
		e.printStackTrace();
			
		}
		
		finally {
			
			DB.closeResultSet(rs); // criaremos um metodo statico auxiliar para evitar repetir o bloclo try catch varias vezes
			DB.closeStatement(st);
			DB.CloseConnection();
		}
		
		
		
		
		
		
		
		
		
	}

}
