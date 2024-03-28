package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.AcademicUnit;
import model.Student;

public class StudentDao {

    
	public String RegisterStudent(Student student) {

        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        	tr = session.beginTransaction();
        	session.saveOrUpdate(student);
        	tr.commit();
            session.close();
            return "success";
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return "failed";
        }
        
	}
	
	public Student getStudentByRegNo(String regNo) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Student student = (Student) session.createQuery("FROM Student WHERE regNo = :code")
                                .setParameter("code", regNo)
                                .uniqueResult();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

	}
}
