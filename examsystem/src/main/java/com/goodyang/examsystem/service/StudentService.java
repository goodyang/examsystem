package com.goodyang.examsystem.service;

import java.util.List;

import com.goodyang.examsystem.po.Student;

public interface StudentService {

	public boolean allowLogin(String studentID,String password);
	public Student getStudentInfo(String studentID);
	public void setStudentResult(String studentID,int result);
	public List<Student> getStudentByName(String studentName);
	public List<Student> getStudentByClass(String sclass);
}
