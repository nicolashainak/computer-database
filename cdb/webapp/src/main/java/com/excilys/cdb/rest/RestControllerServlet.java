//package com.excilys.cdb.rest;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.*;
//
//import javax.validation.Valid;
//
//import com.excilys.cdb.binding.dto.DtoComputerServletService;
//import com.excilys.cdb.binding.mapper.MapperDtoComputerServletService;
//import com.excilys.cdb.binding.validation.ValidationDtoComputer;
//import com.excilys.cdb.model.Computer;
//import com.excilys.cdb.model.Page;
//import com.excilys.cdb.service.MyService;
//import com.excilys.cdb.session.Session;
//
//@RestController
//@RequestMapping("/rest")
//public class RestControllerServlet {
//
//	private MapperDtoComputerServletService mapperDtoComputerServletService;
//	private MyService service;
//	private Session session;
//	private ValidationDtoComputer validateur;
//
//	public RestControllerServlet(MyService service, MapperDtoComputerServletService mapperDtoComputerServletService,ValidationDtoComputer validateur) {
//		this.service = service;
//		this.mapperDtoComputerServletService = mapperDtoComputerServletService;
//		this.validateur=validateur;
//	}
//
//	@GetMapping(value = "/count/{search}")
//	public long count(@PathVariable String search) {
//		return service.nbComputerSearch(search);
//	}
//
//	@GetMapping(value = "/count")
//	public long count() {
//		return service.nbComputerSearch("");
//	}
//
//	@GetMapping
//	public List<DtoComputerServletService> count(@RequestBody Page page) {
//		return mapperDtoComputerServletService.mapToListComputerDTO(service.find(page));
//	}
//
//	@GetMapping(value = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE })
//	public DtoComputerServletService find(@PathVariable int id) {
//		try {
//			return mapperDtoComputerServletService.mapToComputerDTO(service.find(id));
//		} catch (Exception e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
//		}
//	}
//
//	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void create(@RequestBody @Valid DtoComputerServletService computerDTOAdd, BindingResult bindingResult) {
//
//		validateur.validate(computerDTOAdd, bindingResult);
//		if (!bindingResult.hasErrors()) {
//		
//			service.create(mapperDtoComputerServletService.mapToComputer(computerDTOAdd));
//		
//			
//		} else {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Computer Not Valid" );
//		}
//	}
//
//	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void update(@RequestBody @Valid DtoComputerServletService computerDTOUpdate, BindingResult bindingResult) {
//
//		validateur.validate(computerDTOUpdate, bindingResult);
//		if (!bindingResult.hasErrors()) {
//			service.update(mapperDtoComputerServletService.mapToComputer(computerDTOUpdate));
//		} else {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Computer Not Valid" );
//		}
//
//	}
//
//	
//	@DeleteMapping(value = "/computer", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void delete(@RequestBody DtoComputerServletService computerDTO) {
//		service.delete(Integer.valueOf(computerDTO.getId()));
//	}
//
//	
//	
//	private void updateNbParPage(String button) {
//
//		if (button != null) {
//			Page page = (Page) session.getPage();
//			if ("button1".equals(button)) {
//				page.setNumPage(1);
//				page.setNbComputerParPage(10);
//			} else if ("button2".equals(button)) {
//				page.setNumPage(1);
//				page.setNbComputerParPage(50);
//			} else if ("button3".equals(button)) {
//				page.setNumPage(1);
//				page.setNbComputerParPage(100);
//			}
//			session.setPage(page);
//		}
//	}
//
//	private void updateNumPage(String numeroPage) {
//
//		if (numeroPage != null && !numeroPage.isEmpty()) {
//			Page page = session.getPage();
//
//			page.setNumPage(Integer.parseInt(numeroPage));
//
//			session.setPage(page);
//		}
//	}
//
//	private void updateOrder(String order) {
//
//		if (order != null) {
//			if (order.equals(session.getOrderBy())) {
//				session.setReverse(!(Boolean) session.getReverse());
//			} else {
//				session.setReverse(false);
//				session.setOrderBy(order);
//			}
//
//		}
//	}
//
//	private void updateSearch(String search) {
//
//		if ("".equals(search)) {
//			session.setIssearching(false);
//			session.setOrderBy("id");
//			session.setLastSearch("");
//			this.initPage();
//
//		} else if (search != null) {
//			session.setIssearching(true);
//			Page page = (Page) session.getPage();
//			page.setNumPage(1);
//			page.setNbComputerRequest(service.nbComputerSearch(search));
//			session.setLastSearch(search);
//			session.setPage(page);
//
//		} else if ((Boolean) session.getIssearching()) {
//			Page page = (Page) session.getPage();
//
//			page.setNbComputerRequest(service.nbComputerSearch((String) session.getLastSearch()));
//			session.setPage(page);
//		}
//
//	}
//
//	private void initPage() {
//		Page page = (Page) session.getPage();
//		page.setNumPage(1);
//		page.setNbComputerRequest(service.getNbComputerTotal(page));
//		session.setPage(page);
//	}
//
//	
//
//	private void updateAffichage() {
//		int[] tableau = (int[]) session.getTableauAffichage();
//		Page page = (Page) session.getPage();
//		for (int i = 0; i < 5; i++) {
//			tableau[i] = page.getNumPage() + i - 2 < 1 || page.getNumPage() + i - 2 > page.getNbPageMax() ? -1
//					: page.getNumPage() + i - 2;
//		}
//
//		session.setTableauAffichage(tableau);
//	}
//
//}