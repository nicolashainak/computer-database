package ui;

import persistance.DaoCompany;
import persistance.DaoComputer;

public class Page {
	
	public static int nbPageComputer () throws Exception {
		int nbComputer = DaoComputer.nbComputer();
		int nbPageComputer = (nbComputer - (nbComputer%20)) /20;
		return nbPageComputer;
	}
	
	public static int nbPageCompany () throws Exception {
		int nbCompany = DaoCompany.nbCompany();
		int nbPageCompany = (nbCompany - (nbCompany%20)) /20;
		return nbPageCompany;
	}
	
	
}
