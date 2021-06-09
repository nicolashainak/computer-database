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
import com.excilys.cdb.service.MyService;
import java.io.*;
import java.util.*;

@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {

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

	private void addComputer(String name, String introduced, String discontinued, String company_id) {

		DtoComputerServletService dtoComputer = new DtoComputerServletService(name, introduced, discontinued,
				company_id);
		if (validationDtoComputer.isValidDto(dtoComputer)) {

			service.addComputer(mapperDtoComputerServletService.dtoToComputer(dtoComputer));
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompany = service.getListCompany();
		request.setAttribute("listCompany", listCompany);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		this.addComputer(name, introduced, discontinued, company);
		doGet(request, response);
	}
}