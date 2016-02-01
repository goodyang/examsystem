package com.goodyang.examsystem.service;

import java.util.List;

import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.util.Page;
import com.goodyang.examsystem.util.PageResult;

public interface SubjectService {
	public boolean saveSubject(Subject subject);
	
	public PageResult querySubjectByPage(Page page);
	
	public Subject showSubjectParticular(int subjectID);
	
	public void updateSubject(Subject subject);
	
	public void deleteSubject(int subjectID);
	
	public PageResult likeQueryBySubjectTitle(String subjectTitle, Page page);
	
	public List<Subject> randomFindSubject(int number);
	
	public int accountResult(List<Integer> subjectID, List<String> studentAnwsers);
}
