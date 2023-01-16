package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;// #PreparedStatement# é a classe que permite montar a consulta sql  e deixar os parametros da consulta para depois
		try {
			conn = DB.getConnection();// conectando com o banco de dados

			// EXAMPLE 1:
			st = conn.prepareStatement(  //#conn.prepareStatement# comando para implementar o comando sql na forma de string(abaixo)
					"INSERT INTO seller "//#comando INSERT INTO# + nome da tabela >> seller
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "// nome dos campos da tabela que eu irei prencher o id é auto incrementado
					+ "VALUES " // comando sql para os valores
					+ "(?, ?, ?, ?, ?)",//virgula da sobrecarga // a interrogação significa que depois eu colacarei o valor... 
					Statement.RETURN_GENERATED_KEYS); // retornar o id
               // atribuindo valores para cada uma das interrogações
			st.setString(1, "Carl Purple");// comando para prencher os campos pendentes de valor(de acordo com o tipo de variavel do campo) ex : st.setString
			st.setString(2, "carl@gmail.com");// repare que cada campo tem o numero da interrogação
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // data formatada
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);

			// EXAMPLE 2: inserindo mais de um departamentoao mesmo tempo
			//st = conn.prepareStatement(
			//		"insert into department (Name) values ('D1'),('D2')", 
			//		Statement.RETURN_GENERATED_KEYS);

			int rowsAffected = st.executeUpdate();// comando para executar o comando sql acima(para alteração de dados)
			     // a variavel  rowsAffected recebe o resultado do  st.executeUpdate que indica quantas linhas foram alteradas
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);// 1 de posição 1
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}				
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.CloseConnection(); // sempre fechada por ultimo
		}
	}

}
