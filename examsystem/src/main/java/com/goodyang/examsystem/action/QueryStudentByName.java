package com.goodyang.examsystem.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodyang.examsystem.po.Student;
import com.goodyang.examsystem.service.StudentService;
import com.goodyang.examsystem.service.StudentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class QueryStudentByName extends ActionSupport{
	private String studentName;
	private StudentService studentService = new StudentServiceImpl();
	
	public String getStudentName() {
		return studentName;
	}
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Student> list = studentService.getStudentByName(studentName);
		request.setAttribute("students", list);
		return this.SUCCESS;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
