package it.polito.tdp.lab3.model;

public class Corso 
{
	private String id;
	private String nome;
	private int crediti;
	private int pd;
	
	public Corso(String id, String nome)
	{
		this.id = id;
		this.nome = nome;
	}

	public String getId() 
	{
		return id;
	}

	public String getNome() 
	{
		return nome;
	}

	@Override
	public String toString() 
	{
		return nome;
	}

	public int getCrediti()
	{
		return crediti;
	}

	public void setCrediti(int crediti)
	{
		this.crediti = crediti;
	}

	public int getPd() 
	{
		return pd;
	}

	public void setPd(int pd)
	{
		this.pd = pd;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
