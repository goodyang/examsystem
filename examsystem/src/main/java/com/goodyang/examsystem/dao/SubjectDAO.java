package com.goodyang.examsystem.dao;

import java.util.List;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.util.Page;

public interface SubjectDAO {
	public void addSubject(Subject subject);
	public Subject findSubjectByTitle(String subjectTitle);
	public List<Subject> findSubjectByPage(Page page);
	public int findSubjectCount();
	public Subject findSubjectByID(int subjectID);
	public void updateSubject(Subject subject);
	public void deleteSubject(int subjectID);
	public List<Subject> likeQueryByTitle(String subjectTitle, Page page);
	public int findLinkQueryCount(String subjectTitle);
	public List<Subject> randomFindsSubject(int number);
}
