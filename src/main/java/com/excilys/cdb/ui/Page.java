package com.excilys.cdb.ui;

import java.util.ArrayList;

import com.excilys.cdb.model.Computer;

public class Page {
	private ArrayList<Computer> computerList;
	private int nbPageMax;
	private int nbPage;
	private int nbComputerParPage;
	private int nbComputer;
	
	public Page (ArrayList<Computer> computerList,int nbC) {
		this.computerList=computerList;
		this.nbPage=0;
		this.nbComputerParPage=20;
		this.nbComputer=nbC;
		this.nbPageMax=nbPageComputer();
		
	}
	public Page (ArrayList<Computer> computerList,int nbPage,int limit,int nbC) {
		this.computerList=computerList;
		this.nbPage=nbPage;
		this.nbComputerParPage=limit;
		this.nbComputer=nbC;
		this.nbPageMax=nbPageComputer();
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
	
	public int nbPageComputer ()  {
		int nbPageComputer = (this.nbComputer - (this.nbComputer%this.nbComputerParPage)) /this.nbComputerParPage;
		return nbPageComputer;
	}
	
	
	
	
}
