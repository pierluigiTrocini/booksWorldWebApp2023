package it.books_world.persistenza.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.books_world.persistenza.dao.UtenteDao;
import it.books_world.persistenza.model.Utente;

public class UtenteDaoPostgres implements  UtenteDao{

	Connection conn;

	public UtenteDaoPostgres(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Utente findByEmail(String email){
		Utente utente = null;
		String query = "select * from utente where email = ?";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				utente = new Utente();
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setPassword(rs.getString("password"));
				long secs = rs.getDate("data_di_nascita").getTime();
				utente.setData_di_nascita(new java.util.Date(secs));
				utente.setModeratore(rs.getBoolean("privilegi_moderatore"));
				utente.setEmail(rs.getString("email"));
			}
		}catch(SQLException exception){ exception.printStackTrace(); }

		return utente;
	}

	@Override
	public Utente FindByUsername(String username) {

		Utente utente = null;
		String query = "select * from utente where username = ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				utente = new Utente();
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setPassword(rs.getString("password"));
				long secs = rs.getDate("data_di_nascita").getTime();
				utente.setData_di_nascita(new java.util.Date(secs));
				utente.setModeratore(rs.getBoolean("privilegi_moderatore"));
				utente.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utente;
	}

	@Override
	public Boolean Insert(Utente utente) {
		if (FindByUsername(utente.getUsername()) == null) {
			String insertStr = "INSERT INTO utente VALUES (?, ?, ?, ?, ?, ?,?)";

			PreparedStatement st;
			try {
				st = conn.prepareStatement(insertStr);
				st.setString(1, utente.getUsername());
				st.setString(2, utente.getNome());
				st.setString(3, utente.getCognome());
				st.setString(4, utente.getPassword());
				long secs = utente.getData_di_nascita().getTime();
				st.setDate(5, new java.sql.Date(secs));

				st.setBoolean(6,utente.getModeratore() );
				st.setString(7,utente.getEmail() );

				st.executeUpdate();

				return true;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void UpdateModerator(Utente utente) {
		String updateStr = "UPDATE utente set privilegi_moderatore=? where username=?";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(updateStr);
			st.setBoolean(1, true);
			st.setString(2, utente.getUsername());
			st.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
