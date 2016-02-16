package com.goodyang.examsystem.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.goodyang.examsystem.util.Page;
import com.goodyang.examsystem.util.PageResult;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QuerySubjectAction extends ActionSupport{
	private int currentPage;
	private SubjectService subjectService = new SubjectServiceImpl();
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setEveryPage(10);
		PageResult pageResult = subjectService.querySubjectByPage(page);
		List<Subject> subjects = pageResult.getList();
		page = pageResult.getPage();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("subjects", subjects);
		request.setAttribute("page", page);
		return SUCCESS;
	}
	
}
