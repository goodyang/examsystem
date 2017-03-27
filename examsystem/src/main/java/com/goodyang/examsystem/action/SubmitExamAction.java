package com.goodyang.examsystem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodyang.examsystem.po.Student;
import com.goodyang.examsystem.service.StudentService;
import com.goodyang.examsystem.service.StudentServiceImpl;
import com.goodyang.examsystem.service.SubjectService;
import com.goodyang.examsystem.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SubmitExamAction extends ActionSupport{
	private List<Integer> subjectID;
	private SubjectService subjectService = new SubjectServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<String> studentAnswers = new ArrayList<String>();
		for(int i = 0; i < 20; i++) {
			String answer = request.getParameter("subjectAnswer"+i);
			studentAnswers.add(answer);
		}
		int GeneralPoint = subjectService.accountResult(subjectID, studentAnswers);
		
		//设置成绩到学生信息中
		Map session = ActionContext.getContext().getSession();
		Student student = (Student)session.get("studentInfo");
		String studentID = student.getStudentID();
		studentService.setStudentResult(studentID, GeneralPoint);
		request.setAttribute("studentName", student.getStudentName());//保存学生姓名和总分数
		request.setAttribute("GeneralPoint", GeneralPoint);
		session.put("subjectIDs", subjectID);//将考试题目保存到session，方便后面显示答案使用
		return SUCCESS;
	}
	
	public void setSubjectID(List<Integer> subjectID) {
		this.subjectID = subjectID;
	}
	public List<Integer> getSubjectID() {
		return subjectID;
	}
}
