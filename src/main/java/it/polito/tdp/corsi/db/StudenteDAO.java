package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
public List<Studente> getStudenteByCorso(String codIns){
		
		String sql = "SELECT s.`matricola`, s.`cognome`, s.`nome`, s.`CDS` " 
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.`matricola` = i.`matricola` AND i.`codins` = ?";
		
		List<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
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
	
	public List<Divisione> getDivisioneStudenti(String codIns){
		
		String sql = "SELECT s.CDS, Count(*) as n "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.CDS <> '' "
				+ "group by s.CDS";

		
		List<Divisione> result = new ArrayList<Divisione>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Divisione d = new Divisione(rs.getString("cds"), rs.getInt("n"));
				result.add(d);
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
