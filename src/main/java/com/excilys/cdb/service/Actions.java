package com.excilys.cdb.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.ui.Graphic;
import com.excilys.cdb.ui.Page;
import com.excilys.cdb.ui.VueCompany;
import com.excilys.cdb.ui.VueComputer;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;

public class Actions {

	Scanner scanner = new Scanner(System.in);
	DaoCompany daoCompany = DaoCompany.getInstance();
	DaoComputer daoComputer = DaoComputer.getInstance();
	
	public void boucle(){
		Graphic.drawBase();
		waitAction();
	}

	public void waitAction()  {
		String s = scanner.nextLine();
		int act = Integer.parseInt(s);
		System.out.println(act);
		switch (act) {
		case 1:
			showComputer();
			break;
		case 2:
			showCompany();
			break;
		case 3:
			searchComputer();
			break;
		case 4:
			addComputer();
			break;
		case 5:
			updateComputer();
			break;
		case 6:
			deleteComputer();
			break;
		case 7:
			stop();
			break;
		default:
			break;
		}

	}

	public void showComputer()  {
		Page page=new Page(daoComputer.readDatabase(0,20),daoComputer.nbComputer());
		boolean wait = true;
		int i=0;
		int limit=20;
		page=affichage(page,i,limit);
		while (wait) {
			i=0;
			String s = scanner.nextLine();
			int act = Integer.parseInt(s);
			System.out.println(act);
			switch (act) {
			case 1:
				if (page.getNbPage() > 0) {
					i=-1;
					
				}
				page=affichage(page,i,limit);
				break;
			case 2:
				if (page.getNbPage()<page.getNbPageMax()) {
					i=1;
				}
				page=affichage(page,i,limit);

				break;
			case 3:
				System.out.println("Wich page do you want to go ?");
				String entrie = scanner.nextLine();
				int goTo = Integer.parseInt(entrie);
				if (0 <= goTo && goTo <= page.getNbPageMax()) {
					i = goTo-page.getNbPage();
				}
				page=affichage(page,i,limit);
				break;
			case 4:
				wait = false;
				System.out.println("Exit to the database ");
				break;

			default:
				page=affichage(page,i,limit);
				break;
			}

		}
		boucle();
	}

	public void showCompany()  {
		boolean wait = true;
		int i = 0;
		int max = daoCompany.nbPageCompany();
		System.out.println("Page " + i + " / " + max);
		VueCompany.affCompany(daoCompany.readDatabase(i));
		Graphic.drawPage(i,max);
		while (wait) {
			String s = scanner.nextLine();
			int act = Integer.parseInt(s);
			System.out.println(act);
			switch (act) {
			case 1:
				if (i > 0) {
					i--;
				}
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(daoCompany.readDatabase(i));
				Graphic.drawPage(i,max);
				break;
			case 2:
				if (i < max) {
					i++;
				}
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(daoCompany.readDatabase(i));
				Graphic.drawPage(i,max);

				break;
			case 3:
				System.out.println("Wich page do you want to go ?");
				String entrie = scanner.nextLine();
				int goTo = Integer.parseInt(entrie);
				if (0 <= goTo && goTo <= max) {
					i = goTo;
				}
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(daoCompany.readDatabase(i));
				Graphic.drawPage(i,max);
				break;
			case 4:
				wait = false;
				System.out.println("Exit to the database ");
				break;

			default:
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(daoCompany.readDatabase(i));
				Graphic.drawPage(i,max);
				break;
			}

		}
		boucle();
	}

	public void searchComputer()  {
		System.out.println("ID of the computer you want ?");
		int n = Integer.parseInt(scanner.nextLine());
		VueComputer.affComputer(daoComputer.searchComputer(n));
		boucle();
	}

	public void addComputer() {
		System.out.println("Name of the new computer");
		String name = scanner.nextLine();
		System.out.println(" Years introduced");
		Integer y1 = enterNb().orElse(null);
		System.out.println("Month introduced ");
		Integer m1 = enterNb().orElse(null);
		System.out.println("Day introduced ");
		Integer d1 = enterNb().orElse(null);
		System.out.println("Years discontinued ");
		Integer y2 = enterNb().orElse(null);
		System.out.println("Month discontinued");
		Integer m2 = enterNb().orElse(null);
		System.out.println("Day discontinued ");
		Integer d2 = enterNb().orElse(null);
		System.out.println("Id of the company  ");
		LocalDate ld1=createDate(y1,m1,d1).orElse(null);
		LocalDate ld2=createDate(y2,m2,d2).orElse(null);
		int company_id = Integer.parseInt(scanner.nextLine());
		if (ld1!=null && ld2!=null) {
			if ( y1*525600+m1*43800+d1*1440 > y2*525600+m2*43800+d2*1440 || y1 < 1970 || y2 < 1970) {
				System.out.println("Date problem, years must be supperior to 1970 and introduced date inferior to discontnued date");
				System.out.println("The computer has not be add to te base ");
			}else {
				System.out.println((company_id));
				Computer c = new Computer(name, ld1, ld2, (daoCompany.getCompany(company_id)));
				daoComputer.newComputer(c);
				System.out.println(c);
			}
		}else {
			Computer c = new Computer(name, ld1, ld2, (daoCompany.getCompany(company_id)));
			daoComputer.newComputer(c);
			System.out.println(c);
		}
		boucle();
	}
	
	public Optional<LocalDate> createDate(Integer y, Integer m, Integer d){
		LocalDate date=null;
		if( y != null && m!= null && d!=null) {
			try {
				LocalDate dateTest= LocalDate.of(y,m,d);
				date=dateTest;
			}catch(DateTimeException e) {
			
			}
		}
		return Optional.ofNullable(date);
		
	}
	
	public Optional <Integer> enterNb(){
		Integer result = null;
		String test = scanner.nextLine() ;
		try {
			result=Integer.parseInt(test);
		}catch(NumberFormatException e) {
			System.out.println("Date non correcte");
		}
		return Optional.ofNullable(result);
		
	}
	public void updateComputer() {
		System.out.println("Id of the computer you want to change ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Name of the computer after change");
		String name = scanner.nextLine();
		System.out.println(" Years introduced");
		int y1 = Integer.parseInt(scanner.nextLine());
		System.out.println("Month introduced ");
		int m1 = Integer.parseInt(scanner.nextLine());
		System.out.println("Day introduced ");
		int d1 = Integer.parseInt(scanner.nextLine());
		System.out.println("Years discontinued ");
		int y2 = Integer.parseInt(scanner.nextLine());
		System.out.println("Month discontinued");
		int m2 = Integer.parseInt(scanner.nextLine());
		System.out.println("Day discontinued ");
		int d2 = Integer.parseInt(scanner.nextLine());
		System.out.println("Id of the company  ");
		int company_id = Integer.parseInt(scanner.nextLine());
		if (y1*525600+m1*43800+d1*1440 > y2*525600+m2*43800+d2*1440|| y1 < 1970 || y2 < 1970) {
			System.out.println("Date problem, years must be supperior to 1970 and introduced date inferior to discontnued date");
			System.out.println("The computer has not be add to te base ");
		}else {
		Computer c = new Computer(name, LocalDate.of(y1, m1, d1), LocalDate.of(y2, m2, d2), (daoCompany.getCompany(company_id)));
		daoComputer.updateComputer(id, c);
		}
		boucle();
	}

	public void deleteComputer() {
		System.out.println("Id of the computer you want to change ");
		int n = Integer.parseInt(scanner.nextLine());
		daoComputer.deleteComputer(n);
		boucle();
	}

	public void stop() {
		System.out.println("Bye Bye My friend !");
		// this.always = false;
	}
	
	public Page affichage(Page page,int i,int limit)  {
		page=new Page(daoComputer.readDatabase( page.getNbPage()+i , limit) , page.getNbPage()+i, limit, daoComputer.nbComputer());
		System.out.println("Page " + page.getNbPage() + " / " + page.getNbPageMax());
		VueComputer.affComputer(page.getComputerList());
		Graphic.drawPage(i,page.nbPageComputer());
		return page;
	}

}
