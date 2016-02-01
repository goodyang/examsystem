package com.goodyang.examsystem.action;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SubjectAddAction extends ActionSupport {
	private String subjectTitle;
	private String subjectOptionA;
	private String subjectOptionB;
	private String subjectOptionC;
	private String subjectOptionD;
	private String subjectAnswer;
	private String subjectParse;
	
	private SubjectService subjectService = new SubjectServiceImpl();
	
	@Override
	public String execute() throws Exception {
		Subject subject = new Subject();
		subject.setSubjectTitle(subjectTitle);
		subject.setSubjectOptionA(subjectOptionA);
		subject.setSubjectOptionB(subjectOptionB);
		subject.setSubjectOptionC(subjectOptionC);
		subject.setSubjectOptionD(subjectOptionD);
		subject.setSubjectAnswer(subjectAnswer);
		subject.setSubjectParse(subjectParse);
		if(subjectService.saveSubject(subject)) {
			return SUCCESS;
		}else {
			this.addActionError("该试题已经添加过了，请不要重复添加！");
			return INPUT;
		}
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectOptionA() {
		return subjectOptionA;
	}

	public void setSubjectOptionA(String subjectOptionA) {
		this.subjectOptionA = subjectOptionA;
	}

	public String getSubjectOptionB() {
		return subjectOptionB;
	}

	public void setSubjectOptionB(String subjectOptionB) {
		this.subjectOptionB = subjectOptionB;
	}

	public String getSubjectOptionC() {
		return subjectOptionC;
	}

	public void setSubjectOptionC(String subjectOptionC) {
		this.subjectOptionC = subjectOptionC;
	}

	public String getSubjectOptionD() {
		return subjectOptionD;
	}

	public void setSubjectOptionD(String subjectOptionD) {
		this.subjectOptionD = subjectOptionD;
	}

	public String getSubjectAnswer() {
		return subjectAnswer;
	}

	public void setSubjectAnswer(String subjectAnswer) {
		this.subjectAnswer = subjectAnswer;
	}

	public String getSubjectParse() {
		return subjectParse;
	}

	public void setSubjectParse(String subjectParse) {
		this.subjectParse = subjectParse;
	}

	public SubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
}
