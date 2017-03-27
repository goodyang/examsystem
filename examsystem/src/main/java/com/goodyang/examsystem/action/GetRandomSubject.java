package com.goodyang.examsystem.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class GetRandomSubject extends ActionSupport{
	private SubjectService subjectService = new SubjectServiceImpl();
	@Override
	public String execute() throws Exception {
		List<Subject> list = subjectService.randomFindSubject(20);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("subjects", list);
		return this.SUCCESS;
	}
}
