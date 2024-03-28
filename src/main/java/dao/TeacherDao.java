package dao;

import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Teacher;

public class TeacherDao {

	public String RegisterTeacher(Teacher teacher) {

        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        	tr = session.beginTransaction();
        	session.saveOrUpdate(teacher);
        	tr.commit();
			session.close();
			return "success";
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return "failed";
        }
	}
	public Teacher getTeacherByCode(String code){
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			Teacher teacher = (Teacher) session.createQuery("FROM Teacher tr WHERE tr.id = :code")
					.setParameter("code", code)
					.uniqueResult();
			return teacher;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
