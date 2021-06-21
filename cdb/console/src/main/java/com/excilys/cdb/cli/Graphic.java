package com.excilys.cdb.cli;

public class Graphic {

	public static void drawBase() {
		System.out.println("[1] Show Computer Database");
		System.out.println("[2] Show Company Database");
		System.out.println("[3] Show a Computer");
		System.out.println("[4] Add a Computer");
		System.out.println("[5] Change a Computer");
		System.out.println("[6] Delete a Computer ");
		System.out.println("[7] Delete a Company");
		System.out.println("[8] Exit");
	}
	
	public static void drawPage(int numeroPage,int nbPage )  {
		String s = "\n  ";
		if (numeroPage>0) {
			s=s+"[1]Prev \t";
		}
		if (numeroPage<nbPage) {
		s=s+"[2]Next \t";
		}
		s=s+"[3]Go To Page ... \t";
		s=s+"[4]EXIT ";
		System.out.println(s);
	}
	
}
