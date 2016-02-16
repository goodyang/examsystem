package com.goodyang.examsystem.action;

import org.apache.struts2.ServletActionContext;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SubjectParticularAction extends ActionSupport {
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
		Subject subject = subjectService.showSubjectParticular(subjectID);
		ServletActionContext.getRequest().setAttribute("subject", subject);
		return SUCCESS;
	}
}
