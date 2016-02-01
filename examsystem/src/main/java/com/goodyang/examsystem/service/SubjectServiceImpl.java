package com.goodyang.examsystem.service;

import java.util.List;

import com.goodyang.examsystem.dao.SubjectDAO;
import com.goodyang.examsystem.dao.SubjectDAOImpl;
import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.util.Page;
import com.goodyang.examsystem.util.PageResult;
import com.goodyang.examsystem.util.PageUtil;

public class SubjectServiceImpl implements SubjectService {

	private SubjectDAO  subjectDAO = new SubjectDAOImpl();
	
	@Override
	public boolean saveSubject(Subject subject) {
		String subjectTitle = subject.getSubjectTitle();
		if(subjectDAO.findSubjectByTitle(subjectTitle) == null) {
			subjectDAO.addSubject(subject);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PageResult querySubjectByPage(Page page) {
		page = PageUtil.createPage(page.getEveryPage(), 
				subjectDAO.findSubjectCount(), page.getCurrentPage());
		List<Subject> list = subjectDAO.findSubjectByPage(page);
		PageResult result = new PageResult(page, list);
		return result;
	}

	@Override
	public Subject showSubjectParticular(int subjectID) {
		return subjectDAO.findSubjectByID(subjectID);
	}

	@Override
	public void updateSubject(Subject subject) {
		subjectDAO.updateSubject(subject);
	}

	@Override
	public void deleteSubject(int subjectID) {
		subjectDAO.deleteSubject(subjectID);
	}

	@Override
	public PageResult likeQueryBySubjectTitle(String subjectTitle, Page page) {
		page = PageUtil.createPage(page.getEveryPage(), 
				subjectDAO.findLinkQueryCount(subjectTitle), page.getCurrentPage());
		List<Subject> list = subjectDAO.likeQueryByTitle(subjectTitle, page);
		PageResult result = new PageResult(page, list);
		return result;
	}

	@Override
	public List<Subject> randomFindSubject(int number) {
		return subjectDAO.randomFindsSubject(number);
	}

	@Override
	public int accountResult(List<Integer> subjectIDs,
			List<String> studentAnwsers) {
		int GeneralPoint = 0;
		for(int i=0;i < subjectIDs.size(); i++) {
			String rightAnwser = subjectDAO
					.findSubjectByID(subjectIDs.get(i)).getSubjectAnswer();
			if(rightAnwser.equals(studentAnwsers.get(i))) {
				GeneralPoint += 5;
			}
		}
		return GeneralPoint;
		
	}

}
