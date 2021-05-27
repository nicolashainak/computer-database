package com.excilys.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.Service;

import java.io.* ;
import java.util.* ;


@WebServlet ("/DataBase")
public class DataBase  extends HttpServlet {
		private Service service =Service.getInstance();
		private Page page =new Page();
	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 	
			page.setNbComputerRequest(service.getNbComputerTotal(page));
			
			
			String button=request.getParameter("button");
			String numeroPage=request.getParameter("num");
			
			if (numeroPage != null && !numeroPage.isEmpty()) {
				
				if (page.getNumPage()+Integer.parseInt(numeroPage)>0 && page.getNumPage()+Integer.parseInt(numeroPage)< page.getNbPageMax()+1) {
					
					page.setNumPage(page.getNumPage()+Integer.parseInt(numeroPage));
				}
			}
		
			
			
			if ("button1".equals(button)) {
				page.setNumPage(1);
				page.setNbComputerParPage(10);
			}else if ("button2".equals(button)){
				page.setNumPage(1);
				page.setNbComputerParPage(50);
			}else if ("button3".equals(button)) {
				page.setNumPage(1);
				page.setNbComputerParPage(100);
			}
			
			List <Computer> computerList = service.getListComputer(this.page); 
		
		
			
			request.setAttribute("computerList",computerList );
			request.setAttribute("page",page );
			
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/database.jsp" ).forward( request, response );
		

		
	}

	/* public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		 	
			ArrayList<Computer> computerList = daoComputer.readDatabase(0, 10);
			int nbOrdi=daoComputer.nbComputer();
			request.setAttribute("nbOrdi",nbOrdi );
			
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/database.jsp" ).forward( request, response );
		

	}*/
}	