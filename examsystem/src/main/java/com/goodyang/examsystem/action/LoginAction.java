package com.goodyang.examsystem.action;

import java.util.Map;

import com.goodyang.examsystem.po.Student;
import com.goodyang.examsystem.service.StudentService;
import com.goodyang.examsystem.service.StudentServiceImpl;
import com.goodyang.examsystem.service.TeacherService;
import com.goodyang.examsystem.service.TeacherServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private String id; 			// 接受用户编号
	private String password;	// 接受用户密码
	private String role;		// 接受用户角色
	private StudentService studentService = 
		new StudentServiceImpl();//学生业务逻辑组件引用
	private TeacherService teacherService = 
		new TeacherServiceImpl();//教师业务逻辑组件引用
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String execute() throws Exception {
		if("student".equals(role)) {//如果以学生身份登录
			if(studentService.allowLogin(id, password)) {
				Student studentInfo = studentService.getStudentInfo(id);
				//保存学生记录到session范围
				Map session = ActionContext.getContext().getSession();
				session.put("studentInfo", studentInfo);
				return "studentSuccess";
			}else {
				addActionError("该学生编号不存在，或者密码不正确!");
				return this.INPUT;
			}
		}else {
			if(teacherService.allowLogin(id, password)) {
				return "teacherSuccess";
			}else {
				addActionError("该教师编号不存在，或者密码不正确!");
				return this.INPUT;
			}
		}
	}
	
}