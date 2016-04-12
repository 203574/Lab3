package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.db.CorsoDAO;
import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.SegreteriaModel;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController 
{
	SegreteriaModel sm = new SegreteriaModel();
    Corso c = new Corso("", "");
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private ImageView buttonCompleta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button buttonCerca;

    @FXML
    private Button buttonIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button buttonReset;

    @FXML
    void doCerca(ActionEvent event) 
    {
    		txtResult.setText("");
    		if(boxCorso.getValue() == null)
    		{
    			txtResult.setText("ERRORE: Selezionare un corso dal menu a tendina");
    			return;
    		}
            String corso = boxCorso.getValue().getNome();
        	String id = boxCorso.getValue().getId();
        	if(corso.compareTo("") == 0 && id.compareTo("") == 0 && txtMatricola.getText().trim().compareTo("") == 0)
        	{
        		txtResult.setText("ERRORE: inserire la matricola");
        		return;
        	}
        	if(corso.compareTo("") == 0)
        	{
 
        		int matricola;
				try 
				{
					matricola = Integer.parseInt(txtMatricola.getText());
				} 
				catch (NumberFormatException e) 
				{
					txtResult.setText("ERRORE: la matricola deve essere solo numerica");
					return;
				}
        		if(sm.getStudente(matricola) != null)
        		{
        			List<Corso> corsiF = sm.corsiFrequentati(matricola);
            		if(corsiF != null)
            		{
            			for (Corso c : corsiF)
            			{
            				txtResult.setText(txtResult.getText() + c.getId()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd()+"\n");
        				}
            		}
        		}
        		else
        		{
        			txtResult.setText("ERRORE:studente non presente nel database");
        		}
        	}
        	else
        	{
        		if(txtMatricola.getText().compareTo("") == 0)
        		{
        			List<Studente> s = sm.getIscritti(id);
            		for (Studente studente : s) 
            		{
            			txtResult.setText(txtResult.getText() + studente.getMatricola()+" "+studente.getNome()+
            					" "+studente.getCognome()+" "+studente.getCds()+"\n");
        			}
        		}
        		else
        		{
        			if(sm.cercaIscritto(id, Integer.parseInt(txtMatricola.getText())))
        			{
        				txtResult.setText("Lo studente è iscritto al corso");
        			}
        			else
        			{
        				txtResult.setText("Lo studente non è iscritto al corso");
        			}
        		}
        	}
        	
    }

    @FXML
    void doCompleta(MouseEvent event)
    {
    	Studente s = sm.getStudente(Integer.parseInt(txtMatricola.getText()));
    	if(s != null)
    	{
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}
    	else
    	{
    		txtResult.setText("Studente non presente nel database");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) 
    {
    	if(boxCorso.getValue().getId().compareTo("") != 0 && boxCorso.getValue().getNome().compareTo("") != 0 && txtMatricola.getText().compareTo("") != 0)
    	{
    		String nomeC = boxCorso.getValue().getNome();
        	String idC = boxCorso.getValue().getId();
        	int matricola = Integer.parseInt(txtMatricola.getText());
        	if(!sm.cercaIscritto(idC, matricola))
        	{
        		if(sm.aggiungiStudente(idC, matricola))
        			txtResult.setText("Studente inserito correttamente");
        		else
        			txtResult.setText("ERRORE: Studente non inserito");
        	}
        	else
        	{
        		txtResult.setText("ERRORE: Studente già iscritto al corso");
        	}
    	}
    }

    @FXML
    void doReset(ActionEvent event) 
    {
    	txtCognome.setText("");
    	txtMatricola.setText("");
    	txtNome.setText("");
    	txtResult.setText("");
    	boxCorso.setValue(c);
    }

    @FXML
    void initialize() 
    {
        assert boxCorso != null : "fx:id=\"boxCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert buttonCompleta != null : "fx:id=\"buttonCompleta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert buttonCerca != null : "fx:id=\"buttonCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert buttonIscrivi != null : "fx:id=\"buttonIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert buttonReset != null : "fx:id=\"buttonReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
       
      
          
        boxCorso.getItems().add(c);
        for (Corso s : sm.getCorsi())
        {
        	boxCorso.getItems().add(s);
		}
    }
}

