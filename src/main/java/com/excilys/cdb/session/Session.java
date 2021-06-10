package com.excilys.cdb.session;


import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.excilys.cdb.model.Page;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {

	
	
	private Page page = new Page();
	private String orderBy = "computer.id";
	private Boolean reverse = false;
	private Boolean issearching = false;
	private String lastSearch = "";
	private int[] tableauAffichage = new int[5];
	
	
	public Session() {
		
	}
	
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Boolean getReverse() {
		return reverse;
	}
	public void setReverse(Boolean reverse) {
		this.reverse = reverse;
	}
	public Boolean getIssearching() {
		return issearching;
	}
	public void setIssearching(Boolean issearching) {
		this.issearching = issearching;
	}
	public String getLastSearch() {
		return lastSearch;
	}
	public void setLastSearch(String lastSearch) {
		this.lastSearch = lastSearch;
	}
	public int[] getTableauAffichage() {
		return tableauAffichage;
	}
	public void setTableauAffichage(int[] tableauAffichage) {
		this.tableauAffichage = tableauAffichage;
	}
	
	





}
