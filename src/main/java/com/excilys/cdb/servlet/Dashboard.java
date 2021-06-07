package com.excilys.cdb.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.App;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.MyService;

import java.io.*;
import java.util.*;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {

	// regarder http session Mettre page en attribut de ca .
	private static final String PAGE = "page";
	private static final String ORDERBY = "orderBy";
	private static final String REVERSE = "reverse";
	private static final String ISSEARCHING = "isSearching";
	private static final String LASTSEARCH = "lastSearch";
	private static final String COMPUTERLIST = "computerList";
	private static final String TABLEAUAFFICHAGE = "tableauAffichage";
	private HttpSession session;
	private MyService service;

	private void initialisationSession(HttpSession session) {
		session.setAttribute(PAGE, new Page());
		session.setAttribute(ORDERBY, "computer.id");
		session.setAttribute(REVERSE, false);
		session.setAttribute(ISSEARCHING, false);
		session.setAttribute(LASTSEARCH, "");
		session.setAttribute(COMPUTERLIST, new ArrayList());
		session.setAttribute(TABLEAUAFFICHAGE, new int[5]);

	}

	@Override
	public void init() {
		try {
			super.init();
			ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
			service = context.getBean(MyService.class);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.session = request.getSession();

		if (session.getAttribute(PAGE) == null) {
			this.initialisationSession(session);
			this.initPage();

		}

		String search = request.getParameter("search");
		String order = request.getParameter("order");
		String button = request.getParameter("button");
		String numeroPage = request.getParameter("num");

		this.updateNumPage(numeroPage);
		this.updateNbParPage(button);
		this.updateOrder(order);
		this.updateSearch(search);
		this.updateAffichage();
		request.setAttribute("tableauAffichage", session.getAttribute(TABLEAUAFFICHAGE));
		request.setAttribute("computerList", (List<Computer>) session.getAttribute(COMPUTERLIST));
		request.setAttribute("page", (Page) session.getAttribute(PAGE));

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);

	}

	private void updateNbParPage(String button) {

		if (button != null) {
			Page page = (Page) session.getAttribute(PAGE);
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
			session.setAttribute(PAGE, page);
		}
	}

	private void updateNumPage(String numeroPage) {

		if (numeroPage != null && !numeroPage.isEmpty()) {
			Page page = (Page) session.getAttribute(PAGE);

			page.setNumPage(Integer.parseInt(numeroPage));

			session.setAttribute(PAGE, page);
		}
	}

	private void updateOrder(String order) {

		if (order != null) {
			if (order.equals(session.getAttribute(ORDERBY))) {
				session.setAttribute(REVERSE, !(Boolean) session.getAttribute(REVERSE));
			} else {
				session.setAttribute(REVERSE, false);
				session.setAttribute(ORDERBY, order);
			}
			session.setAttribute(COMPUTERLIST,
					service.orderBy((Page) session.getAttribute(PAGE), order, (Boolean) session.getAttribute(REVERSE)));
		} else if (!"".equals(session.getAttribute(ORDERBY))) {
			session.setAttribute(COMPUTERLIST, service.orderBy((Page) session.getAttribute(PAGE),
					(String) session.getAttribute(ORDERBY), (Boolean) session.getAttribute(REVERSE)));

		} else {
			session.setAttribute(COMPUTERLIST, service.getListComputer((Page) session.getAttribute(PAGE)));
		}
	}

	private void updateSearch(String search) {

		if ("".equals(search)) {
			session.setAttribute(ISSEARCHING, false);
			session.setAttribute(ORDERBY, "computer.id");
			this.initPage();

		} else if (search != null) {
			session.setAttribute(ISSEARCHING, true);
			Page page = (Page) session.getAttribute(PAGE);
			page.setNumPage(1);
			session.setAttribute(COMPUTERLIST, service.search(page, search, (String) session.getAttribute(ORDERBY)));
			page.setNbComputerRequest(service.nbComputerSearch(search));
			session.setAttribute(LASTSEARCH, search);
			session.setAttribute(PAGE, page);

		} else if ((Boolean) session.getAttribute(ISSEARCHING)) {
			Page page = (Page) session.getAttribute(PAGE);
			session.setAttribute(COMPUTERLIST, service.search(page, (String) session.getAttribute(LASTSEARCH),
					(String) session.getAttribute(ORDERBY)));
			page.setNbComputerRequest(service.nbComputerSearch((String) session.getAttribute(LASTSEARCH)));
			session.setAttribute(PAGE, page);
		}

	}

	private void initPage() {
		Page page = (Page) session.getAttribute(PAGE);
		page.setNumPage(1);
		page.setNbComputerRequest(service.getNbComputerTotal(page));
		session.setAttribute(PAGE, page);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.session = request.getSession();
		List<String> selection = Arrays.asList(request.getParameter("selection").split(","));
		System.out.println(request.getParameter("selection"));
		try {
			System.out.println("try");
			int id_computer;
			for (String stringID : selection) {
				System.out.println(stringID);
				id_computer = Integer.parseInt(stringID);
				service.deleteComputer(id_computer);
			}
		} catch (NumberFormatException e) {
			System.out.println("catch");
		}
		doGet(request, response);
	}

	private void updateAffichage() {
		int[] tableau = (int[]) session.getAttribute(TABLEAUAFFICHAGE);
		Page page = (Page) session.getAttribute(PAGE);
		for (int i = 0; i < 5; i++) {
			tableau[i] = page.getNumPage() + i - 2 < 1 || page.getNumPage() + i - 2 > page.getNbPageMax() ? -1
					: page.getNumPage() + i - 2;
		}

		session.setAttribute(TABLEAUAFFICHAGE, tableau);
	}

}