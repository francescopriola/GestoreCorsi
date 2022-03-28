package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.*;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo){
		return this.corsoDao.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso, Integer> getIscritti(int periodo){
		return this.corsoDao.getIscritti(periodo);
	}
	
	public List<Studente> getStudenteByCorso(String codIns){
		return this.studenteDao.getStudenteByCorso(codIns);
	}
	
	public List<Divisione> getDivisioneStudenti(String codIns){
		return this.studenteDao.getDivisioneStudenti(codIns);
	}
	
}
