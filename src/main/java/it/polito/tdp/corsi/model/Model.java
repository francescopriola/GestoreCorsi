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
	
	public List<String> getStudentiByPeriodo(int periodo){
		return this.corsoDao.getStudentiByPeriodo(periodo);
	}
	
}
