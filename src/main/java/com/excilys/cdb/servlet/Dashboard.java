package com.excilys.cdb.servlet;


import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.MyService;
import com.excilys.cdb.session.Session;



@Controller

public class Dashboard extends HttpServlet {

	private Session session;
	private MyService service;

	public Dashboard(Session session, MyService service) {
		super();
		this.session = session;
		this.service = service;
	}


	@GetMapping("/Dashboard")
	public ModelAndView getTestData(@RequestParam(required = false) String search,
			@RequestParam(required = false) String order, @RequestParam(required = false) String button,
			@RequestParam(required = false) String num) {
		this.initPage();
		this.updateNumPage(num);
		this.updateNbParPage(button);
		this.updateOrder(order);
		this.updateSearch(search);
		this.updateAffichage();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dashboard");
		mv.addObject("tableauAffichage", session.getTableauAffichage());
		mv.addObject("page", session.getPage());
		mv.addObject("computerList", service.getListComputer( session.getPage(),session.getLastSearch(),session.getOrderBy(),session.getReverse()));
		return mv;
		
	}

	private void updateNbParPage(String button) {

		if (button != null) {
			Page page = (Page) session.getPage();
			if ("button1".equals(button)) {
				page.setNumPage(1);
				page.setNbComputerParPage(10);
			} else if ("button2".equals(button)) {
				page.setNumPage(1);
				page.setNbComputerParPage(50);
			} else if ("button3".equals(button)) {
				page.setNumPage(1);
				page.setNbComputerParPage(100);
			}
			session.setPage(page);
		}
	}

	private void updateNumPage(String numeroPage) {

		if (numeroPage != null && !numeroPage.isEmpty()) {
			Page page = session.getPage();

			page.setNumPage(Integer.parseInt(numeroPage));

			session.setPage(page);
		}
	}

	private void updateOrder(String order) {

		if (order != null) {
			if (order.equals(session.getOrderBy())) {
				session.setReverse(!(Boolean) session.getReverse());
			} else {
				session.setReverse(false);
				session.setOrderBy(order);
			}

		}
	}

	private void updateSearch(String search) {

		if ("".equals(search)) {
			session.setIssearching(false);
			session.setOrderBy("computer.id");
			session.setLastSearch("");
			this.initPage();

		} else if (search != null) {
			session.setIssearching(true);
			Page page = (Page) session.getPage();
			page.setNumPage(1);
			page.setNbComputerRequest(service.nbComputerSearch(search));
			session.setLastSearch(search);
			session.setPage(page);

		} else if ((Boolean) session.getIssearching()) {
			Page page = (Page) session.getPage();

			page.setNbComputerRequest(service.nbComputerSearch((String) session.getLastSearch()));
			session.setPage(page);
		}

	}

	private void initPage() {
		Page page = (Page) session.getPage();
		page.setNumPage(1);
		page.setNbComputerRequest(service.getNbComputerTotal(page));
		session.setPage(page);
	}

	@PostMapping("/Dashboard")
	public ModelAndView postTestData(@RequestParam String selection){
		List<String> selectionP = Arrays.asList(selection.split(","));
		System.out.println(selectionP);
		try {
			int id_computer;
			for (String stringID : selectionP) {
				id_computer = Integer.parseInt(stringID);
				service.deleteComputer(id_computer);
			}
		} catch (NumberFormatException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return getTestData(null,null,null,null) ;
	}
	
	
	

	private void updateAffichage() {
		int[] tableau = (int[]) session.getTableauAffichage();
		Page page = (Page) session.getPage();
		for (int i = 0; i < 5; i++) {
			tableau[i] = page.getNumPage() + i - 2 < 1 || page.getNumPage() + i - 2 > page.getNbPageMax() ? -1
					: page.getNumPage() + i - 2;
		}

		session.setTableauAffichage(tableau);
	}

}