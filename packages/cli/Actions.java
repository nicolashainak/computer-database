package cli;

import java.time.LocalDate;
import java.util.Scanner;

import model.Computer;
import ui.Graphic;
import ui.Page;
import ui.VueCompany;
import ui.VueComputer;
import persistance.DaoCompany;
import persistance.DaoComputer;

public class Actions {

	Scanner scanner = new Scanner(System.in);

	public void boucle() throws Exception {
		Graphic.drawBase();
		waitAction();
	}

	public void waitAction() throws Exception {
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

	public void showComputer() throws Exception {
		Page page=new Page(DaoComputer.readDatabase(0,20));
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

	public void showCompany() throws Exception {
		boolean wait = true;
		int i = 0;
		int max = DaoCompany.nbPageCompany();
		System.out.println("Page " + i + " / " + max);
		VueCompany.affCompany(DaoCompany.readDatabase(i));
		Graphic.drawPage(i);
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
				VueCompany.affCompany(DaoCompany.readDatabase(i));
				Graphic.drawPage(i);
				break;
			case 2:
				if (i < max) {
					i++;
				}
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(DaoCompany.readDatabase(i));
				Graphic.drawPage(i);

				break;
			case 3:
				System.out.println("Wich page do you want to go ?");
				String entrie = scanner.nextLine();
				int goTo = Integer.parseInt(entrie);
				if (0 <= goTo && goTo <= max) {
					i = goTo;
				}
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(DaoCompany.readDatabase(i));
				Graphic.drawPage(i);
				break;
			case 4:
				wait = false;
				System.out.println("Exit to the database ");
				break;

			default:
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(DaoCompany.readDatabase(i));
				Graphic.drawPage(i);
				break;
			}

		}
		boucle();
	}

	public void searchComputer() throws Exception {
		System.out.println("ID of the computer you want ?");
		int n = Integer.parseInt(scanner.nextLine());
		VueComputer.affComputer(DaoComputer.searchComputer(n));
		boucle();
	}

	public void addComputer() throws Exception {
		String retour ="";
		Boolean pb =false;
		System.out.println("Name of the new computer");
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
		if ( y1*525600+m1*43800+d1*1440 > y2*525600+m2*43800+d2*1440 || y1 < 1970 || y2 < 1970) {
			System.out.println("Date problem, years must be supperior to 1970 and introduced date inferior to discontnued date");
			System.out.println("The computer has not be add to te base ");
		}else {
		Computer c = new Computer(name, LocalDate.of(y1, m1, d1), LocalDate.of(y2, m2, d2), company_id);
		DaoComputer.newComputer(c);
		}
		boucle();
	}

	public void updateComputer() throws Exception {
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
		Computer c = new Computer(name, LocalDate.of(y1, m1, d1), LocalDate.of(y2, m2, d2), company_id);
		DaoComputer.updateComputer(id, c);
		}
		boucle();
	}

	public void deleteComputer() throws Exception {
		System.out.println("Id of the computer you want to change ");
		int n = Integer.parseInt(scanner.nextLine());
		DaoComputer.deleteComputer(n);
		boucle();
	}

	public void stop() {
		System.out.println("Bye Bye My friend !");
		// this.always = false;
	}
	
	public Page affichage(Page page,int i,int limit) throws Exception {
		page=new Page(DaoComputer.readDatabase(page.getNbPage()+i,limit),page.getNbPage()+i,limit);
		System.out.println("Page " + i + " / " + page.getNbPageMax());
		VueComputer.affComputer(page.getComputerList());
		Graphic.drawPage(i);
		return page;
	}

}
