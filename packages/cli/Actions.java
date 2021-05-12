package cli;

import java.time.LocalDate;
import java.util.Scanner;

import model.Computer;
import ui.VueCompany;
import ui.VueComputer;
import persistance.DaoCompany;
import persistance.DaoComputer;

public class Actions {

	private boolean always = true;
	Scanner scanner = new Scanner(System.in);

	public void boucle() throws Exception {

		//while (always) {
			Graphic.drawBase();
			waitAction();

		//}

	}

	public void waitAction() throws Exception {

		String s = scanner.nextLine();
		int act = Integer.parseInt(s);
		switch (act) {
		case 1:
			showComputer();
		case 2:
			showCompany();
		case 3:
			searchComputer();
		case 4:
			addComputer();
		case 5:
			updateComputer();
		case 6:
			deleteComputer();
		case 7:
			stop();

		}

	}

	public void showComputer() throws Exception {
		VueComputer.affComputer(DaoComputer.readDatabase());
		boucle();
	}

	public void showCompany() throws Exception {
		VueCompany.affCompany(DaoCompany.readDatabase());
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

	public void deleteComputer() throws Exception{
		System.out.println("Id of the computer you want to change ");
		int n = Integer.parseInt(scanner.nextLine());
		DaoComputer.deleteComputer(n);
		boucle();
	}

	public void stop() {
		System.out.println("Bye Bye My friend !");
		//this.always = false;
	}

}
