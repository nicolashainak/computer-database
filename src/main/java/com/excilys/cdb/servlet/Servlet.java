package com.excilys.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.* ;
import java.text.* ;
import java.util.* ;


@WebServlet ("/bonjour")
public class Servlet  extends HttpServlet {

	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
	

		/*
		 * get parameter recupp chaine en url 
		 */
		String paramAuteur = request.getParameter( "auteur" );
		String message = "Transmission de variables : OK ! " + paramAuteur;
		request.setAttribute( "test", message );
		this.getServletContext().getRequestDispatcher( "/test.jsp" ).forward( request, response );
		

		
	}

	 public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
	
		doGet(request, response) ;
	}
}	