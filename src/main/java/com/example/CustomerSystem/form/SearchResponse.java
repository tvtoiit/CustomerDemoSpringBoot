package com.example.CustomerSystem.form;

import java.util.List;

import com.example.CustomerSystem.entity.MstCustomer;

public class SearchResponse {
	private List<MstCustomer> dataSearch;
	private String page;
	private boolean statusBtn = false;
	
	public List<MstCustomer> getDataSearch() {
		return dataSearch;
	}
	public void setDataSearch(List<MstCustomer> dataSearch) {
		this.dataSearch = dataSearch;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public boolean getStatusBtn() {
		return statusBtn;
	}
	public void setStatusBtn(boolean statusBtn) {
		this.statusBtn = statusBtn;
	}
}
