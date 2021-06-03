package com.excilys.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.binding.mapper.MapperDtoComputerServletService;
import com.excilys.cdb.binding.validation.ValidationDtoCompany;
import com.excilys.cdb.binding.validation.ValidationDtoComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.Service;
import java.io.*;
import java.util.*;

@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	Service service = Service.getInstance();

	private void addComputer(String name, String introduced, String discontinued, String company_id) {
		try {

			DtoCompanyServletService dtoCompany = new DtoCompanyServletService(Integer.parseInt(company_id));
			if (ValidationDtoCompany.getInstance().isValidDto(dtoCompany)) {

				DtoComputerServletService dtoComputer = new DtoComputerServletService(name, introduced, discontinued,
						dtoCompany);
				if (ValidationDtoComputer.getInstance().isValidDto(dtoComputer)) {

					service.addComputer(MapperDtoComputerServletService.dtoToComputer(dtoComputer));
				}
			}
		} catch (NumberFormatException e) {
			e.getStackTrace();
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