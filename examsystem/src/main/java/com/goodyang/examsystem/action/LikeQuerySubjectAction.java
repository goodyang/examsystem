package com.goodyang.examsystem.action;

import java.util.ArrayList;
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
public class LikeQuerySubjectAction extends ActionSupport{
	private String subjectTitle;
	private int currentPage;
	private SubjectService subjectService = new SubjectServiceImpl();
	
	public SubjectService getSubjectService() {
		return subjectService;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Page page = new Page();
		page.setEveryPage(10);
		page.setCurrentPage(currentPage);
		PageResult pageResult = subjectService.likeQueryBySubjectTitle(subjectTitle, page);
		List<Subject> subjects = pageResult.getList();
		List<Subject> newSubjects = new ArrayList<Subject>();
		for(Subject subject : subjects) {
			String newTitle = subject.getSubjectTitle().replaceAll(subjectTitle, 
					"<font color='red'>"+subjectTitle+"</font>");
			subject.setSubjectTitle(newTitle);
			newSubjects.add(subject);
		}
		page = pageResult.getPage();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("subjects", newSubjects);
		request.setAttribute("page", page);
		return SUCCESS;
	}
}
