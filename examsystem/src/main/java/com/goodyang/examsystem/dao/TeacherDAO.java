package com.goodyang.examsystem.dao;

import com.goodyang.examsystem.po.Teacher;

public interface TeacherDAO {
	public Teacher findByTeacherID(String teacherID);//查询方法，根据教师ID查询
}
