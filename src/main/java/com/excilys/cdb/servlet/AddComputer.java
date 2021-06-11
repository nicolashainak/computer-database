package com.excilys.cdb.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.binding.mapper.MapperDtoComputerServletService;
import com.excilys.cdb.binding.validation.ValidationDtoComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.MyService;
import java.io.*;
import java.util.*;

@Controller
public class AddComputer extends HttpServlet {

	private MyService service;
	private ValidationDtoComputer validationDtoComputer;
	private MapperDtoComputerServletService mapperDtoComputerServletService;


	
	
	
	public AddComputer(MyService service, ValidationDtoComputer validationDtoComputer,
			MapperDtoComputerServletService mapperDtoComputerServletService) {
		super();
		this.service = service;
		this.validationDtoComputer = validationDtoComputer;
		this.mapperDtoComputerServletService = mapperDtoComputerServletService;
	}

	



	@GetMapping("/AddComputer")
	public ModelAndView getTestData() {
		List<Company> listCompany = service.getListCompany();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addComputer");
		mv.addObject("listCompany", listCompany);
		return mv;
		
	}

	@PostMapping("/AddComputer")
	public ModelAndView postTestData(@ModelAttribute("computer") @Validated DtoComputerServletService dtoComputer,
			BindingResult bindingResult ) {
		
		validationDtoComputer.validate(dtoComputer, bindingResult);
		if (! bindingResult.hasErrors() ) {

			try {
					this.service.addComputer(this.mapperDtoComputerServletService.dtoToComputer(dtoComputer));
					
			 }catch ( Exception e){
				  System.out.println("soucis");
			 }
		}
			return getTestData() ;
	
	}
}
	
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String name = request.getParameter("name");
//		String introduced = request.getParameter("introduced");
//		String discontinued = request.getParameter("discontinued");
//		String company = request.getParameter("company");
//		this.addComputer(name, introduced, discontinued, company);
//		doGet(request, response);
//	}
