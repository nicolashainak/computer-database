package com.excilys.cdb.ui;

import java.util.ArrayList;

import com.excilys.cdb.model.Computer;

public class Page {
	private int numPage;
	private int nbComputerParPage;
	private int nbComputerRequest;

	public Page() {
		this.numPage = 1;
		this.nbComputerParPage = 10;
		this.nbComputerRequest = 0;
	}

	public void setNumPage(int i) {
		if (i < this.getNbPageMax()) {
			this.numPage = i;
		}
	}

	public void setNbComputerParPage(int i) {
		this.nbComputerParPage = i;
	}

	public int getNumPage() {
		return this.numPage;
	}

	public int getNbPageMax() {
		int nbPageComputer = (this.nbComputerRequest - (this.nbComputerRequest % this.nbComputerParPage))
				/ this.nbComputerParPage;
		return nbPageComputer;
	}

	public int getNbComputerRequest() {
		return nbComputerRequest;
	}

	public void setNbComputerRequest(int nbComputerRequest) {
		this.nbComputerRequest = nbComputerRequest;
	}

	public int getNbComputerParPage() {
		return this.nbComputerParPage;
	}

	@Override
	public String toString() {
		return "Page [numPage=" + numPage + ", nbComputerParPage=" + nbComputerParPage + ", nbComputerRequest="
				+ nbComputerRequest + "]";
	}
	

}
