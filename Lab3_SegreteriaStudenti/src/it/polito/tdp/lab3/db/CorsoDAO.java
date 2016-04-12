package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class CorsoDAO 
{
	String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
	List<Corso> corsi = new ArrayList<Corso>();
	
	public List<Corso> getCorsi()
	{
		try 
		{
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select codins, nome from corso";
			ResultSet res = st.executeQuery(sql);
			while(res.next())
			{
				String id = res.getString("codins");
				String corso = res.getString("nome");
				corsi.add(new Corso(id, corso));
			}
			res.close();
			conn.close();
			return corsi;
		
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
