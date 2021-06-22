package com.excilys.cdb.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.binding.mapper.MapperDtoComputerServletService;
import com.excilys.cdb.binding.validation.ValidationDtoComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.MyService;

import java.io.*;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditComputer extends HttpServlet {

	private MyService service;
	private ValidationDtoComputer validationDtoComputer;
	private MapperDtoComputerServletService mapperDtoComputerServletService;



	public EditComputer(MyService service, ValidationDtoComputer validationDtoComputer,
			MapperDtoComputerServletService mapperDtoComputerServletService) {
		super();
		this.service = service;
		this.validationDtoComputer = validationDtoComputer;
		this.mapperDtoComputerServletService = mapperDtoComputerServletService;
	}

	

	public boolean isCorrectInt(String id) {
		try {
			Integer.parseInt(id);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	

	@GetMapping("/EditComputer")
	public ModelAndView getTestData(@RequestParam String id) {
		List<Company> listCompany = service.getListCompany();
		ModelAndView mv = new ModelAndView();
		if (isCorrectInt(id)) {
			Computer computer = service.searchComputer(Integer.parseInt(id));
			mv.setViewName("editComputer");
			mv.addObject("id",id);
			mv.addObject("computer", computer);
			mv.addObject("listCompany", listCompany);

		}
		
		return mv;
		
	}

		
		@PostMapping("/EditComputer")
		public ModelAndView postTestData(@RequestParam String id ,@ModelAttribute("computer") @Validated DtoComputerServletService dtoComputer,
				BindingResult bindingResult ) {
				if (isCorrectInt(id)) {
					
				
					validationDtoComputer.validate(dtoComputer, bindingResult);
					if (! bindingResult.hasErrors() ) {
		
						try {
								this.service.updateComputer(Integer.parseInt(id),this.mapperDtoComputerServletService.dtoToComputer(dtoComputer));
								
						 }catch ( Exception e){
							  System.out.println("soucis");
						 }
					}
						
				}
				return getTestData(id) ;
		}
	
	
	
}