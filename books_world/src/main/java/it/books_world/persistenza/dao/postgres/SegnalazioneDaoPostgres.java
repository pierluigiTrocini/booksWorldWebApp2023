package it.books_world.persistenza.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.books_world.persistenza.dao.SegnalazioneDao;
import it.books_world.persistenza.model.Segnalazione;


public class SegnalazioneDaoPostgres implements SegnalazioneDao {
    Connection conn;
		
    public SegnalazioneDaoPostgres(Connection conn) {
			this.conn = conn;
	}
		
	
    public void saveOrUpdate(Segnalazione segnalazione) {
        String query = "select * from segnalazione where recensione = ?";
        	
            try {
        		
        	    PreparedStatement st1 = conn.prepareStatement(query);
    			st1.setLong(1, segnalazione.getRecensione());
    			ResultSet rs = st1.executeQuery();
    			
    			if (rs.next()) {
    				
    				String updateStr = "UPDATE segnalazione set num_segnalazioni = ? where recensione = ?";
    				
    				try {
    					PreparedStatement st2 = conn.prepareStatement(updateStr);
    				
    					st2.setLong(1, (segnalazione.getNum_segnalazioni()+1));
    					st2.setLong(2, (segnalazione.getRecensione()));
    					
    					st2.executeUpdate();
    					
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    			}
    			
    			else {
    				
    				String insertStr = "INSERT INTO segnalazione VALUES (?, ?, ?, ?)";
    				
    				try {
    					
    					PreparedStatement st3 = conn.prepareStatement(insertStr);
    					
    					st3.setLong(1, segnalazione.getRecensione());
    					st3.setLong(2, segnalazione.getNum_segnalazioni());
    					st3.setLong(3, segnalazione.getVoti_favorevoli_eliminazione());
    					st3.setLong(4, segnalazione.getVoti_sfavorevoli_eliminazione());
    					
    					st3.executeUpdate();
    					
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    			}
    			
        	}		
				
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
        
        
        
    
    public List<Segnalazione> findAll(){
        	
        List<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
    	String query = "select * from segnalazione";
    		
        try {
    		Statement st = conn.createStatement();
    	    ResultSet rs = st.executeQuery(query);
    			
    		while (rs.next()) {
    			Segnalazione segnalazione = new Segnalazione();
    				
    			segnalazione.setRecensione(rs.getLong("recensione"));
    			segnalazione.setNum_segnalazioni(rs.getLong("num_segnalazioni"));
    			segnalazione.setVoti_favorevoli_eliminazione(rs.getLong("voti_favorevoli_eliminazione"));
    			segnalazione.setVoti_sfavorevoli_eliminazione(rs.getLong("voti_sfavorevoli_eliminazione"));
    				
    			segnalazioni.add(segnalazione);	
    				
    		}
    			
    	}
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    		
    	return segnalazioni;

    }
    
    
    
    
    
    // METODI DA IMPLEMENTARE A SECONDA DI SOLUZIONE TROVATA
    // RIGUARDANTE IL TRACCIAMENTO DEI VOTI
        
    public void voteAgainst(Segnalazione segnalazione) {
        	
    }
    	
    public void voteFor(Segnalazione segnalazione) {
    		
    }
    	
    	
	
	    
	public void delete(Segnalazione segnalazione) {
	    	
	    String deleteSt = "delete from segnalazione where recensione = ?";
		try {
		    PreparedStatement st = conn.prepareStatement(deleteSt);
			st.setLong(1, segnalazione.getRecensione());
			st.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	    
}

