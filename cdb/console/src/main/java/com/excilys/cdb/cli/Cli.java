package com.excilys.cdb.cli;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.cli.Graphic;
import com.excilys.cdb.cli.VueCompany;
import com.excilys.cdb.cli.VueComputer;
import java.util.List;
import com.excilys.cdb.DaoCompany;
import com.excilys.cdb.DaoComputer;
import com.excilys.cdb.service.MyService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;


@Controller
public class Cli {




	Scanner scanner = new Scanner(System.in);
	MyService service;
	
	public Cli(MyService service) {
		this.service=service;
	}
	
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
			deleteCompany();
			break;
		case 8:	
			stop();
			break;
		default:
			break;
		}

	}

	public void showComputer()  {
		
		Page page=new Page();
		page.setNbComputerRequest(service.nbComputerSearch(""));

		boolean wait = true;
		page=affichage(page);
		while (wait) {
			String s = scanner.nextLine();
			int act = Integer.parseInt(s);
			System.out.println(act);
			switch (act) {
			case 1:
				if (page.getNumPage() > 1) {
					page.setNumPage(page.getNumPage()-1);
					
				}
				page=affichage(page);
				break;
			case 2:
				if (page.getNumPage()<(service.nbComputerSearch("")/10)) {
					page.setNumPage(page.getNumPage()+1);;
				}
				page=affichage(page);

				break;
			case 3:
				System.out.println("Wich page do you want to go ?");
				String entrie = scanner.nextLine();
				int goTo = Integer.parseInt(entrie);
				if (0 <= goTo && goTo <= (service.nbComputerSearch("")/10)) {
					page.setNumPage(goTo);
				}
				page=affichage(page);
				break;
			case 4:
				wait = false;
				System.out.println("Exit to the database ");
				break;

			default:
				page=affichage(page);
				break;
			}

		}
		boucle();
	}

	public void showCompany()  {
		boolean wait = true;
		int i = 1;
		int max = service.getNbCompany()/10;
		System.out.println("Page " + i + " / " + max);
		Graphic.drawPage(i,max);
		Sort currentSort;
		Pageable  currentPageable;
		while (wait) {
			String s = scanner.nextLine();
			int act = Integer.parseInt(s);
			System.out.println(act);
			switch (act) {
			case 1:
				if (i > 1) {
					i--;
				}
				currentSort= 	Sort.by(Order.asc("id"));
				currentPageable = PageRequest.of(i, 10, currentSort);
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(service.getListCompanyOffset(currentPageable));
				Graphic.drawPage(i,max);
				break;
			case 2:
				if (i < max) {
					i++;
				}
				currentSort= 	Sort.by(Order.asc("id"));
				currentPageable = PageRequest.of(i, 10, currentSort);
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(service.getListCompanyOffset(currentPageable));
				Graphic.drawPage(i,max);

				break;
			case 3:
				System.out.println("Wich page do you want to go ?");
				String entrie = scanner.nextLine();
				int goTo = Integer.parseInt(entrie);
				if (0 <= goTo && goTo <= max) {
					i = goTo;
				}
				currentSort = 	Sort.by(Order.asc("id"));
				currentPageable = PageRequest.of(i, 10, currentSort);
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(service.getListCompanyOffset(currentPageable));
				Graphic.drawPage(i,max);
				break;
			case 4:
				wait = false;
				System.out.println("Exit to the database ");
				break;

			default:
				currentSort = 	Sort.by(Order.asc("id"));
				currentPageable = PageRequest.of(i, 10, currentSort);
				System.out.println("Page " + i + " / " + max);
				VueCompany.affCompany(service.getListCompanyOffset(currentPageable));
				Graphic.drawPage(i,max);
				break;
			}

		}
		boucle();
	}

	public void searchComputer()  {
		System.out.println("ID of the computer you want ?");
		int n = Integer.parseInt(scanner.nextLine());
		List<Computer> listComputer = new ArrayList<Computer>();
		listComputer.add(service.searchComputer(n));
		VueComputer.affComputer(listComputer);
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
				Computer c = new Computer(name, ld1, ld2, (service.getCompanyById(company_id)));
				service.addComputer(c);
				System.out.println(c);
			}
		}else {
			Computer c = new Computer(name, ld1, ld2, (service.getCompanyById(company_id)));
			service.addComputer(c);
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
		Computer c = new Computer(name, LocalDate.of(y1, m1, d1), LocalDate.of(y2, m2, d2), (service.getCompanyById(company_id)));
		service.updateComputer(id, c);
		}
		boucle();
	}

	public void deleteComputer() {
		System.out.println("Id of the computer you want to delete ");
		int n = Integer.parseInt(scanner.nextLine());
		service.deleteComputer(n);
		boucle();
	}

	
		public void deleteCompany() {
			System.out.println("Id of the company you want to delete ");
			int n = Integer.parseInt(scanner.nextLine());
			service.deleteCompany(n);
			boucle();
		}
	public void stop() {
		System.out.println("Bye Bye My friend !");
		// this.always = false;
	}
	
	public Page affichage(Page page)  {
		Sort currentSort = 	Sort.by(Order.asc("name"));
		Pageable currentPageable = PageRequest.of(page.getNumPage(), page.getNbComputerParPage(), currentSort);
		System.out.println("Page " + page.getNumPage() + " / " + (service.nbComputerSearch("")/10));
		VueComputer.affComputer(service.getListComputer("% %",currentPageable));
		Graphic.drawPage(page.getNumPage(),page.getNbComputerParPage());
		return page;
	}

}
