package it.polito.tdp.lab3.model;

import java.util.*;

import it.polito.tdp.lab3.db.CorsoDAO;
import it.polito.tdp.lab3.db.IscrizioneDAO;
import it.polito.tdp.lab3.db.StudenteDAO;

public class SegreteriaModel 
{
	
	public Studente getStudente(int matricola)
	{
		StudenteDAO stdao = new StudenteDAO();
		Studente s = stdao.getStudente(matricola);
		return s;
	}
	
	public List<Corso> getCorsi()
	{
		CorsoDAO cdao = new CorsoDAO();
		return cdao.getCorsi();
	}
	
	public List<Studente> getIscritti(String id)
	{
		IscrizioneDAO iscrizione = new IscrizioneDAO();
		return iscrizione.getIscritti(id);
	}
	
	public List<Corso> corsiFrequentati(int matricola)
	{
		IscrizioneDAO idao = new IscrizioneDAO();
		return idao.corsiFrequentati(matricola);
	}
	
	public boolean cercaIscritto(String id, int matricola)
	{
		IscrizioneDAO idao = new IscrizioneDAO();
		return idao.cercaIscritto(id, matricola);
	}
	
	public boolean aggiungiStudente(String id, int matricola)
	{
		IscrizioneDAO idao = new IscrizioneDAO();
		return idao.aggiungiStudente(id, matricola);
	}
}
