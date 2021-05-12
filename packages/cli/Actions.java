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

		boolean wait = true;
		int i = 0;
		int max = Page.nbPageComputer();
		affichage(i,max);
		while (wait) {
			String s = scanner.nextLine();
			int act = Integer.parseInt(s);
			System.out.println(act);
			switch (act) {
			case 1:
				if (i > 0) {
					i--;
				}
				affichage(i,max);
				break;
			case 2:
				if (i < max) {
					i++;
				}
				affichage(i,max);

				break;
			case 3:
				System.out.println("Wich page do you want to go ?");
				String entrie = scanner.nextLine();
				int goTo = Integer.parseInt(entrie);
				if (0 <= goTo && goTo <= max) {
					i = goTo;
				}
				affichage(i,max);
				break;
			case 4:
				wait = false;
				System.out.println("Exit to the database ");
				break;

			default:
				affichage(i,max);
				break;
			}

		}
		boucle();
	}

	public void showCompany() throws Exception {
		boolean wait = true;
		int i = 0;
		int max = Page.nbPageCompany();
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
		Computer c = new Computer(name, LocalDate.of(y1, m1, d1), LocalDate.of(y2, m2, d2), company_id);
		DaoComputer.newComputer(c);
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
		Computer c = new Computer(name, LocalDate.of(y1, m1, d1), LocalDate.of(y2, m2, d2), company_id);
		DaoComputer.updateComputer(id, c);
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
	
	public void affichage(int i, int max ) throws Exception {
		System.out.println("Page " + i + " / " + max);
		VueComputer.affComputer(DaoComputer.readDatabase(i));
		Graphic.drawPage(i);
		
	}

}
