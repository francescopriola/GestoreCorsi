package it.polito.tdp.corsi.db;

import java.sql.*;
import java.util.*;
import it.polito.tdp.corsi.model.*;

public class CorsoDAO {

	public List<Corso> getCorsiByPeriodo(int periodo) {
	
		String sql = "select * "
				+ "from corso "
				+ "where pd = ?";
		//Quando copio la query da Sequel Pro ricordati di togliere gli \n a tutte le righe 
		//e aggiungere uno spazio in tutte (tranne l'ultima) in modo da non avere le parole attaccate
		
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;	
		}catch(SQLException e) {
			System.err.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Corso, Integer> getIscritti(int periodo){
		
		String sql = "SELECT c.`codins`, c.`crediti`, c.`nome`, c.`pd`, COUNT(*) as n "
				+ "FROM Corso c, iscrizione i "
				+ "WHERE c.`codins` = i.`codins` AND c.`pd`= ? "
				+ "GROUP BY c.`codins`, c.`crediti`, c.`nome`, c.`pd`";
		
		Map<Corso, Integer>  result = new HashMap<Corso, Integer>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);	//I parametri partono da 1 e non da 0
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.put(c, rs.getInt("n"));
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;	
		}catch(SQLException e) {
			System.err.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
	}
	
	}
