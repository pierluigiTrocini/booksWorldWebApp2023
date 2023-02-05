package it.books_world.persistenza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.books_world.persistenza.dao.CarrelloDao;
import it.books_world.persistenza.dao.OrdineDao;
import it.books_world.persistenza.dao.RecensioneDao;
import it.books_world.persistenza.dao.SegnalazioneDao;
import it.books_world.persistenza.dao.UtenteDao;
import it.books_world.persistenza.dao.postgres.CarrelloDaoPostgres;
import it.books_world.persistenza.dao.postgres.OrdineDaoPostgres;
import it.books_world.persistenza.dao.postgres.RecensioneDaoPostgres;
import it.books_world.persistenza.dao.postgres.SegnalazioneDaoPostgres;
import it.books_world.persistenza.dao.postgres.UtenteDaoPostgres;

public class DBManager {
private static DBManager instance = null;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
	}

	Connection conn = null;

	public Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "pasPOS99");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public UtenteDao getUtenteDao() {
		return new UtenteDaoPostgres(getConnection());
	}

	public OrdineDao getOrdineDao() {
		return new OrdineDaoPostgres(getConnection());
	}

	public RecensioneDao getRecensioneDao() {
		return new RecensioneDaoPostgres(getConnection());
	}

	public CarrelloDao getCarrelloDao() {
		return new CarrelloDaoPostgres(getConnection());
	}

	public SegnalazioneDao getSegnalazioneDao() {
		return new SegnalazioneDaoPostgres(getConnection());
	}

}
