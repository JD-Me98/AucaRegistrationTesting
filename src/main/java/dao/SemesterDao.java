package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.CourseDefinition;
import model.Semester;

public class SemesterDao {

	public String RegisterSemester(Semester semester) {

        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        	tr = session.beginTransaction();
        	session.saveOrUpdate(semester);
        	tr.commit();
            session.close();
            return "success";
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return("failed");
        }
	}
	public Semester getSemesterById(String code) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Semester semester = (Semester) session.createQuery("FROM Semester WHERE id = :code")
                                .setParameter("code", code)
                                .uniqueResult();
            session.close();
            return semester;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
