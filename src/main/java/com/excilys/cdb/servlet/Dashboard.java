package com.excilys.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.Service;

import java.io.*;
import java.util.*;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private Service service = Service.getInstance();
	// regarder http session Mettre page en attribut de ca .
	private Page page = new Page();
	private String orderBy = "";
	private Boolean reverse = false;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		page.setNbComputerRequest(service.getNbComputerTotal(page));

		String order = request.getParameter("order");
		String button = request.getParameter("button");
		String numeroPage = request.getParameter("num");

		if (numeroPage != null && !numeroPage.isEmpty()) {

			if (page.getNumPage() + Integer.parseInt(numeroPage) > 0
					&& page.getNumPage() + Integer.parseInt(numeroPage) < page.getNbPageMax() + 1) {

				page.setNumPage(page.getNumPage() + Integer.parseInt(numeroPage));
			}
		}

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
		List<Computer> computerList = new ArrayList();

		if (order != null) {
			if (order.equals(orderBy)) {
				reverse = !reverse;
			} else {
				reverse = false;
				this.orderBy = order;
			}
			computerList = service.orderBy(page, order, reverse);
		} else if (!"".equals(orderBy)) {
			computerList = service.orderBy(page, this.orderBy, reverse);

		} else {
			computerList = service.getListComputer(this.page);
		}

		request.setAttribute("computerList", computerList);
		request.setAttribute("page", page);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);

	}

	/*
	 * public void doPost(HttpServletRequest request, HttpServletResponse response)
	 * throws ServletException, IOException {
	 * 
	 * ArrayList<Computer> computerList = daoComputer.readDatabase(0, 10); int
	 * nbOrdi=daoComputer.nbComputer(); request.setAttribute("nbOrdi",nbOrdi );
	 * 
	 * this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/database.jsp"
	 * ).forward( request, response );
	 * 
	 * 
	 * }
	 */
}