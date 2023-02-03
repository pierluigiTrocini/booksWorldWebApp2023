package it.books_world.persistenza.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.model.Carrello;



public class CarrelloDaoPostgres implements CarrelloDao {

	Connection conn;

	public CarrelloDaoPostgres(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Carrello UserChart(String username) {
		Carrello carrello = new Carrello();
		HashMap<String, Integer> LibriInCarrello = new HashMap<String, Integer>();
		String query = "select * from carrello where utente= ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (carrello.getUsername()==null) {
					carrello.setUsername(rs.getString("utente"));
				}
				String ISBN=rs.getString("isbn_libro");

				// if(LibriInCarrello.containsKey(ISBN)) {
				// 	Integer Value=LibriInCarrello.get(ISBN);
				// 	// Value++;
				// 	LibriInCarrello.put(ISBN, Value);
				// }
				// else {
				// 	LibriInCarrello.put(ISBN, 1);
				// }

				LibriInCarrello.put(ISBN, rs.getInt("numero_dello_stesso_libro"));
			}
			carrello.setLibriInCarrello(LibriInCarrello);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return carrello;
	}

	@Override
	public void InsertorUpdate(String username, String ISBN) {
		if(UserHasBook(username, ISBN)==0) {
			String insertStr = "INSERT INTO carrello VALUES (?,?,?)";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(insertStr);
				st.setString(1,username );
				st.setString(2,ISBN );
				st.setInt(3, 1);
				st.executeUpdate();


			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else {
			String updateStr = "UPDATE carrello set numero_dello_stesso_libro = numero_dello_stesso_libro+1  where utente=? and isbn_libro=? ";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(updateStr);
				st.setString(1, username);
				st.setString(2, ISBN);
				st.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void DeleteorUpdate(String username, String ISBN) {

		if(UserHasBook(username, ISBN)==1) {
		    String delStr= "DELETE FROM carrello where utente=? and isbn_libro=?";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(delStr);
				st.setString(1,username );
				st.setString(2,ISBN );

				st.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else if(UserHasBook(username, ISBN)>1) {
			String updateStr = "UPDATE carrello set numero_dello_stesso_libro = numero_dello_stesso_libro-1  where utente=? and isbn_libro=? ";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(updateStr);
				st.setString(1, username);
				st.setString(2, ISBN);
				st.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public Integer UserHasBook(String username, String ISBN) {
		String query = "select * from carrello where utente = ? and isbn_libro=?";
		Integer NumeroLibri=0;
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,username );
			st.setString(2, ISBN);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				NumeroLibri=rs.getInt("numero_dello_stesso_libro");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return NumeroLibri;
	}

}
