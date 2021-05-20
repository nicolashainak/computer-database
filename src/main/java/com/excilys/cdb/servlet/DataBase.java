package com.excilys.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.service.Actions;

import java.io.* ;
import java.text.* ;
import java.util.* ;


@WebServlet ("/DataBase")
public class DataBase  extends HttpServlet {
		DaoComputer daoComputer = DaoComputer.getInstance();

	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		 	ArrayList<Computer> computerList = daoComputer.readDatabase(1, 20);
		 	request.setAttribute("computerList",computerList );
			DaoComputer daoComputer = DaoComputer.getInstance();
			int nbOrdi=daoComputer.nbComputer();
			request.setAttribute("nbOrdi",nbOrdi );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/database.jsp" ).forward( request, response );
		

		
	}

	 public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
	
		doGet(request, response) ;
	}
}	