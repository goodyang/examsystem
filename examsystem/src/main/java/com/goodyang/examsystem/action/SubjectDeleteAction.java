package com.goodyang.examsystem.action;

import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SubjectDeleteAction extends ActionSupport{
	private int subjectID;
	private SubjectService subjectService = new SubjectServiceImpl();
	
	public int getSubjectID() {
		return subjectID;
	}
	
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	
	@Override
	public String execute() throws Exception {
		subjectService.deleteSubject(subjectID);
		return SUCCESS;
	}
}
