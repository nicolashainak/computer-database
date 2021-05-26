package com.excilys.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.Service;
import java.io.* ;
import java.util.* ;


@WebServlet ("/AddComputer")
public class AddComputer  extends HttpServlet {
		Service service =Service.getInstance();

	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		 List<Company> listCompany = service.getListCompany();
		 request.setAttribute("listCompany",listCompany);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/addComputer.jsp" ).forward( request, response );
		

		
	}

	 public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		service.addComputer(name,introduced,discontinued,company);
		
		doGet(request, response) ;
	}
}	