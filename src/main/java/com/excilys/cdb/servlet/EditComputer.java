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
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.service.Service;

import java.io.* ;
import java.text.* ;
import java.util.* ;


@WebServlet ("/EditComputer")
public class EditComputer  extends HttpServlet {
		DaoComputer daoComputer = DaoComputer.getInstance();
		
	
	private void updateComputer(String name, String introduced, String discontinued, String company_id) {
				try {	
			DtoCompanyServletService dtoCompany = new DtoCompanyServletService(Integer.parseInt(company_id));
		if (ValidationDtoCompany.getInstance().isValidDto(dtoCompany)) {
				DtoComputerServletService dtoComputer = new DtoComputerServletService(name, introduced, discontinued,
					dtoCompany);
			if (ValidationDtoComputer.getInstance().isValidDto(dtoComputer)) {
					
				Service.getInstance().addComputer(MapperDtoComputerServletService.dtoToComputer(dtoComputer));
			}
		}
	}catch(NumberFormatException e) {
			e.getStackTrace();
		}
	}
		
	public boolean isCorrectInt(String id) {
		try {
			Integer.parseInt(id);
			return true;
		}catch(NumberFormatException e) {
			return false ;
		}
		
	}
	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		 List<Company> listCompany = Service.getInstance().getListCompany();
		 request.setAttribute("listCompany", listCompany);
		 String id = request.getParameter("id");
		 request.setAttribute("id", id);
		 if (isCorrectInt(id)) {
			 Computer computer = Service.getInstance().searchComputer(Integer.parseInt(id));
			 request.setAttribute("computer", computer);
		 }else {
			 response.sendRedirect("dashboard");
		 }
		

			

		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/editComputer.jsp" ).forward( request, response );
		

		
	}

	 public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		this.updateComputer(name, introduced, discontinued, company);
		
		//response.sendRedirect("dashboard")
		doGet(request,response);
	}
}	