package it.books_world.persistenza.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.books_world.persistenza.dao.OrdineDao;
import it.books_world.persistenza.model.Utente;
import it.books_world.persistenza.model.Ordine;

public class OrdineDaoPostgres implements OrdineDao{
	Connection conn;

	private static final String nextSequence = "SELECT nextval('idordineseq') AS id";

	public OrdineDaoPostgres(Connection conn) {
		this.conn = conn;
	}


	private Long getNextID() {
		Long id = null;
		try {
			PreparedStatement statement = conn.prepareStatement(nextSequence);

			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}


	@Override
    public void save(Ordine ordine) {
		String insertStr = "INSERT INTO ordine VALUES (?, ?, ?, ?)";

		try {

			PreparedStatement st = conn.prepareStatement(insertStr);

			st.setLong(1, getNextID());
			st.setString(2, ordine.getUtente());

			long secs = ordine.getData().getTime();
			st.setDate(3, new java.sql.Date(secs));


			st.setString(4, ordine.getIsbn_libro());

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }



	@Override
	public List<Ordine> findAll(){
		List<Ordine> ordini = new ArrayList<Ordine>();
		String query = "select * from ordine";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Ordine ordine = new Ordine();

				ordine.setId(rs.getLong("id"));
				ordine.setUtente(rs.getString("utente"));
				ordine.setIsbn_libro(rs.getString("isbn_libro"));

				long secs = rs.getDate("data").getTime();
				ordine.setData(new java.util.Date(secs));

				ordini.add(ordine);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return ordini;

	}
	
	public boolean userOwnsBook(String username, String isbn){
		
		String query = "select * from ordine where utente = ? and isbn_libro = ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, isbn);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}




	@Override
	public List<Ordine> findByUser(Utente utente){

		List<Ordine> ordini = new ArrayList<Ordine>();
		String query = "select * from ordine where utente = ?";

		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, utente.getUsername());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Ordine ordine = new Ordine();

				ordine.setId(rs.getLong("id"));
				ordine.setUtente(rs.getString("utente"));
				ordine.setIsbn_libro(rs.getString("isbn_libro"));

				long secs = rs.getDate("data").getTime();
				ordine.setData(new java.util.Date(secs));

				ordini.add(ordine);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return ordini;

	}



	@Override
	public void delete(Ordine ordine){

		String deleteSt = "delete from ordine where id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(deleteSt);
			st.setLong(1, ordine.getId());
			st.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


}










