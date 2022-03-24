package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.*;

public class Model {
	
	private CorsoDAO corsoDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo){
		return this.corsoDao.getCorsiByPeriodo(periodo);
	}
	
	public List<String> getNumStudentiByPeriodo(int periodo){
		return this.corsoDao.getNumStudentiByPeriodo(periodo);
	}
	
	public List<String> getStudenteByCorso(String codIns){
		return this.corsoDao.getStudenteByCorso(codIns);
	}
	
	public List<Studente> getStudenteByCDS(String codIns){
		return this.corsoDao.getStudenteByCDS(codIns);
	}
	
}
