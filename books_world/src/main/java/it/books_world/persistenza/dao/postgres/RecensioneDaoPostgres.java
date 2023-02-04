package it.books_world.persistenza.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.books_world.persistenza.DBManager;
import it.books_world.persistenza.dao.RecensioneDao;
import it.books_world.persistenza.model.Recensione;
import it.books_world.persistenza.model.Utente;


public class RecensioneDaoPostgres implements RecensioneDao{

	Connection conn;

	private static final String sequence = "SELECT nextval('recensione_id_seq') AS id";

	public RecensioneDaoPostgres(Connection conn) {
		this.conn = conn;
	}

	private  Long getId(){
		Long id = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sequence);

			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public void Save(Recensione recensione) {
		if (recensione.getId() == null) {
			String insertStr = "INSERT INTO recensione VALUES (?, ?, ?, ?, ?, ?, ? , ?, ?)";

			PreparedStatement st;
			try {
				st = conn.prepareStatement(insertStr);

				recensione.setId(getId());

				st.setLong(1, recensione.getId());
				st.setString(2, recensione.getScrittaDa().getUsername());
				st.setBoolean(3, recensione.getSegnalabile());
				st.setString(4, recensione.getIBSN());
				st.setString(5, recensione.getTitolo());
				st.setString(6, recensione.getTesto());
				st.setInt(7, recensione.getNumeroStelle());
				st.setInt(8, recensione.getNumeroMiPiace());
				st.setInt(9, recensione.getNumeroNonMiPiace());

				st.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {System.out.println("Id deve essere null");}
	}

	@Override
	public boolean Delete(Recensione recensione) {
		String query = "DELETE FROM recensione WHERE id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setLong(1, recensione.getId());
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public List<Recensione> AllBookReviews(String ISBN) {
		List<Recensione> recensioni = new ArrayList<Recensione>();
		String query = "select * from recensione where isbn_libro = ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,ISBN );
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Recensione recensione = new Recensione();
				recensione.setId(rs.getLong("id"));

				Utente utente = DBManager.getInstance().
						getUtenteDao().FindByUsername(rs.getString("scrittore"));

				recensione.setScrittaDa(utente);
				recensione.setSegnalabile(rs.getBoolean("segnalabile"));
				recensione.setIBSN(rs.getString("isbn_libro"));
				recensione.setTitolo(rs.getString("titolo"));
				recensione.setTesto(rs.getString("testo"));
				recensione.setNumeroStelle(rs.getInt("num_stelle"));
				recensione.setNumeroMiPiace(rs.getInt("num_mi_piace"));
				recensione.setNumeroNonMiPiace(rs.getInt("num_non_mi_piace"));

				recensioni.add(recensione);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return recensioni;

	}


	@Override
	public void IncrementLikes(Recensione recensione) {
		String updateStr = "UPDATE recensione set num_mi_piace=num_mi_piace+1 where id=?";
		try {
			PreparedStatement st = conn.prepareStatement(updateStr);
			st.setLong(1,recensione.getId());
			st.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void IncrementDislikes(Recensione recensione) {
		String updateStr = "UPDATE recensione set num_non_mi_piace=num_non_mi_piace+1 where id=?";
		try {
			PreparedStatement st = conn.prepareStatement(updateStr);
			st.setLong(1,recensione.getId());

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Recensione FindByPrimaryKey(Long id) {
		Recensione rec = null;
		String query = "select * from recensione where id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				rec = new Recensione();
				rec.setId(rs.getLong("id"));
				Utente utente = DBManager.getInstance().getUtenteDao().FindByUsername(rs.getString("scrittore"));
				rec.setScrittaDa(utente);
				rec.setSegnalabile(rs.getBoolean("segnalabile"));
				rec.setIBSN(rs.getString("isbn_libro"));
				rec.setTitolo(rs.getString("titolo"));
				rec.setTesto(rs.getString("testo"));
				rec.setNumeroStelle(rs.getInt("num_stelle"));
				rec.setNumeroMiPiace(rs.getInt("num_mi_piace"));
				rec.setNumeroNonMiPiace(rs.getInt("num_non_mi_piace"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rec;
	}
	
	@Override
	public boolean WrittenReview(String username,String ISBN) {
		String query = "select * from recensione where isbn_libro = ? and scrittore=?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,ISBN);
			st.setString(2, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
		
	}

}
