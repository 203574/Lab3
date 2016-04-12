package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class IscrizioneDAO 
{
	String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
	
	public List<Studente> getIscritti(String id)
	{
		List<Studente> studenti = new ArrayList<Studente>();
		try 
		{
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select studente.`matricola`, studente.`nome`, cognome, CDS from corso, iscrizione, studente where studente.`matricola`=iscrizione.`matricola` and corso.`codins` = iscrizione.`codins` and corso.`codins` = '"+id+"'";
			ResultSet res = st.executeQuery(sql);
			while(res.next())
			{
				int matric = Integer.parseInt(res.getString("matricola"));
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				String cds = res.getString("CDS");
				Studente s = new Studente(matric, nome, cognome, cds);
				studenti.add(s);
			}
			res.close();
			conn.close();
			return studenti;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> corsiFrequentati(int matricola)
	{
		
		try 
		{
			List<Corso> corsi = new ArrayList<Corso>();
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select corso.`codins`, crediti, nome, pd from corso, iscrizione where corso.`codins` = iscrizione.`codins` and matricola = '"+matricola+"'";
			ResultSet res = st.executeQuery(sql);
			while(res.next())
			{
				String codins = res.getString("codins");
				int crediti = Integer.parseInt(res.getString("crediti"));
				String nome = res.getString("nome");
				int pd = Integer.parseInt(res.getString("pd"));
				Corso c = new Corso(codins, nome);
				c.setCrediti(crediti);
				c.setPd(pd);
				corsi.add(c);
			}
			res.close();
			conn.close();
			return corsi;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean cercaIscritto(String id, int matricola)
	{
		try 
		{
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select * from iscrizione where matricola = '"+matricola+"' and codins = '"+id+"'";
			ResultSet res = st.executeQuery(sql);
			if(res.next())
			{
				res.close();
				conn.close();
				return true;
			}
			else
			{
				res.close();
				conn.close();
				return false;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean aggiungiStudente(String codins, int matricola)
	{
		try 
		{
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "INSERT INTO `iscrizione` (`matricola`, `codins`) VALUES ('"+matricola+"', '"+codins+"')";
			int res = st.executeUpdate(sql);
			conn.close();
			if(res == 1)
			{
				return true;
			}
			else
				return false;
			
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
