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
	
	public List<String> getNumStudentiByPeriodo(int periodo){
		
		String sql = "SELECT c.`codins`, c.`crediti`, c.`nome`, c.`pd`, COUNT(*) as n "
				+ "FROM Corso c, iscrizione i "
				+ "WHERE c.`codins` = i.`codins` AND c.`pd`= ? "
				+ "GROUP BY c.`codins`, c.`crediti`, c.`nome`, c.`pd`";
		
		List<String> result = new ArrayList<String>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c + ", " + rs.getInt("n") + " studenti iscritti.");
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
	
	public List<String> getStudenteByCorso(String codIns){
		
		String sql = "SELECT s.`matricola`, s.`cognome`, s.`nome`" 
				+ "FROM studente s, corso c, iscrizione i "
				+ "WHERE s.`matricola` = i.`matricola` AND i.`codins` = c.`codins` AND c.`codins` = ?";
		
		List<String> result = new ArrayList<String>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getInt("matricola") + ", " + rs.getString("cognome")  + ", " + rs.getString("nome"));
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
	
	public List<Studente> getStudenteByCDS(String codIns){
		
		String sql = "SELECT s.`matricola`, s.`cognome`, s.`nome`, s.`CDS` " 
				+ "FROM studente s, corso c, iscrizione i "
				+ "WHERE s.`matricola` = i.`matricola` AND i.`codins` = c.`codins` AND c.`codins` = ?";
		
		List<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
				result.add(s);
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
