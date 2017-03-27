package com.goodyang.examsystem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowSubjectAnswer extends ActionSupport{
	private SubjectService subjectService = new SubjectServiceImpl();
	@Override
	public String execute() throws Exception {
		List<Subject> subjects = new ArrayList<Subject>();//保存学生考过的题目
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		List<Integer> subjectIDs = (List<Integer>) session.get("subjectIDs");
		for(Integer subjectID : subjectIDs) {
			Subject subject = subjectService.showSubjectParticular(subjectID);//通过试题编号查找试题
			subjects.add(subject);
		}
		request.setAttribute("subjects", subjects);
		return SUCCESS;
	}
}
