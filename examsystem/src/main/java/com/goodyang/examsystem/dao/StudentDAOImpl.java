package com.goodyang.examsystem.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.goodyang.examsystem.hibernate.HibernateSessionFactory;
import com.goodyang.examsystem.po.Student;

public class StudentDAOImpl implements StudentDAO {

	@Override
	public Student findByStudentID(String studentID) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Student student = (Student) session.get(Student.class, studentID);
		HibernateSessionFactory.closeSession();//关闭Session对象
		return student;
	}

	@Override
	public void updateStudent(Student student) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.update(student);//更新学生信息
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}

	@Override
	public List<Student> findByStudentName(String studentName) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Student as stu where stu.studentName=?");
		query.setString(0, studentName);
		List<Student> list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public List<Student> findByStudentClass(String sclass) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Student as stu where stu.sclass = ?");
		query.setString(0, sclass);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();		//关闭Session对象
		return list;
	}

}
