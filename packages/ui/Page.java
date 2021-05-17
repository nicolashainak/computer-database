package ui;

import java.util.ArrayList;

import model.Computer;
import persistance.DaoCompany;
import persistance.DaoComputer;

public class Page {
	private ArrayList<Computer> computerList;
	private int nbPageMax;
	private int nbPage;
	private int nbComputerParPage;
	public Page (ArrayList<Computer> computerList) throws Exception {
		this.computerList=computerList;
		this.nbPageMax=nbPageComputer();
		this.nbPage=0;
		this.nbComputerParPage=20;
	}
	public Page (ArrayList<Computer> computerList,int nbPage,int limit) throws Exception {
		this.computerList=computerList;
		this.nbPageMax=nbPageComputer();
		this.nbPage=nbPage;
		this.nbComputerParPage=limit;
	}
	
	public void setNbPage(int i ) {
		if (i<this.nbPageMax) {
			this.nbPage=i;
		}
	}
	public void setNbComputerParPage(int i) {
		this.nbComputerParPage=i;
	}
	
	public int getNbPage() {
		return this.nbPage;
	}
	public int getNbPageMax() {
		return this.nbPageMax;
	}
	public int getNbComputerParPage() {
		return this.nbComputerParPage;
	}
	public ArrayList<Computer> getComputerList() {
		return this.computerList;
	}
	
	public static int nbPageComputer () throws Exception {
		int nbComputer = DaoComputer.nbComputer();
		int nbPageComputer = (nbComputer - (nbComputer%20)) /20;
		return nbPageComputer;
	}
	
	
	
	
}
