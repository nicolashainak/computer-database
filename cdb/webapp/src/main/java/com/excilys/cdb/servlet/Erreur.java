package com.excilys.cdb.servlet;

import javax.servlet.http.HttpServlet;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Erreur extends HttpServlet {
	
	@GetMapping("/403")
	public ModelAndView getTestData() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("403");
		return mv;
		
	}
	

}