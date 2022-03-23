package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/iscritticorsi?user=root&password=Ciccio.p00";
		try {
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Errore di connessione al database!");
			e.printStackTrace();
			return null;
		}
	}

}
