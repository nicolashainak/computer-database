package com.excilys.cdb.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.cdb.configuration.Configurationjdbc;

import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.binding.mapper.MapperDtoComputerServletService;
import com.excilys.cdb.binding.validation.ValidationDtoComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.MyService;

import java.io.*;
import java.util.*;

@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {

	private MyService service;
	private ValidationDtoComputer validationDtoComputer;
	private MapperDtoComputerServletService mapperDtoComputerServletService;

	@Override
	public void init() {
		try {
			super.init();
			ApplicationContext context = new AnnotationConfigApplicationContext(Configurationjdbc.class);
			service = context.getBean(MyService.class);
			validationDtoComputer = context.getBean(ValidationDtoComputer.class);
			mapperDtoComputerServletService = context.getBean(MapperDtoComputerServletService.class);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void updateComputer(String name, String introduced, String discontinued, String companyId, int id) {

		DtoComputerServletService dtoComputer = new DtoComputerServletService(name, introduced, discontinued,
				companyId);
		if (validationDtoComputer.isValidDto(dtoComputer)) {
			
			service.updateComputer(id, mapperDtoComputerServletService.dtoToComputer(dtoComputer));
		}

	}

	public boolean isCorrectInt(String id) {
		try {
			Integer.parseInt(id);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompany = service.getListCompany();
		request.setAttribute("listCompany", listCompany);
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (isCorrectInt(id)) {
			Computer computer = service.searchComputer(Integer.parseInt(id));
			request.setAttribute("computer", computer);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("company");
		this.updateComputer(name, introduced, discontinued, companyId, Integer.parseInt(id));

		// response.sendRedirect("dashboard")
		doGet(request, response);
	}
}