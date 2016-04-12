package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import	java.sql.PreparedStatement;

import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO 
{
	String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
	public Studente getStudente(int matricola)
	{
		try
		{
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select matricola, nome, cognome , CDS from studente where matricola = '"+matricola+"'";
			ResultSet res = st.executeQuery(sql);
			if(res.next())
			{
				int matr = Integer.parseInt(res.getString("matricola"));
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				String cds = res.getString("CDS");
				Studente s = new Studente(matr, nome, cognome, cds);
				res.close();
				conn.close();
				return s;
			}
			else
			{
				res.close();
				conn.close();
				return null;
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public void create(Studente s)
	{
		Connection conn = BDConnect.getConnection();
	}
	
	public Studente read(int matricola)
	{
		return null;
	}

	public boolean read(Studente s)
	{
		return false;
	}
	
	public void updateCds(Studente s)
	{
		
	}
	
	public void updateCds(int matricola, String cds)
	{
		
	}
	
	
	
}
