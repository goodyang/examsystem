package com.goodyang.examsystem.service;

import java.util.List;

import com.goodyang.examsystem.dao.StudentDAO;
import com.goodyang.examsystem.dao.StudentDAOImpl;
import com.goodyang.examsystem.po.Student;

public class StudentServiceImpl implements StudentService{
	private StudentDAO studentDAO = new StudentDAOImpl();
	
	public boolean allowLogin(String studentID, String password) {
		
		Student student = studentDAO.findByStudentID(studentID);
		if(student == null) {
			return false;
		}else {
			if(password.equals(student.getPassword())) {
				return true;
			}else{
				return false;
			}
			
		}
	}

	public Student getStudentInfo(String studentID) {
		return studentDAO.findByStudentID(studentID);
	}

	public void setStudentResult(String studentID, int result) {
		Student student = studentDAO.findByStudentID(studentID);
		student.setResult(result);
		studentDAO.updateStudent(student);
	}

	public List<Student> getStudentByName(String studentName) {
		return studentDAO.findByStudentName(studentName);
	}

	public List<Student> getStudentByClass(String sclass) {
		return studentDAO.findByStudentClass(sclass);
	}
}
