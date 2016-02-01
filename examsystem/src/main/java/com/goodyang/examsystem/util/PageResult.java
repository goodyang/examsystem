package com.goodyang.examsystem.util;

import java.util.List;

public class PageResult {
	private Page page;
	private List list;
	
	public PageResult(Page page, List list) {
		this.page = page;
		this.list = list;
	}
	
	public Page getPage() {
		return page;
	}
	
	public List getList() {
		return list;
	}
	
	public void setList(List list) {
		this.list = list;
	}
	
	public void setPage(Page page) {
		this.page = page;
	}
}
