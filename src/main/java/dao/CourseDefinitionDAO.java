package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.AcademicUnit;
import model.CourseDefinition;

public class CourseDefinitionDAO {
	

	public String createCourse(CourseDefinition courseDefinition) {
        Transaction tr = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	tr = session.beginTransaction();
        	session.saveOrUpdate(courseDefinition);
        	tr.commit();
            session.close();
            return "success";
        }catch(Exception ex) {
            ex.printStackTrace();
            return ("Error " + ex);
        }
        
    }
	public CourseDefinition getCourseDefinitionById(String code) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            CourseDefinition courseDefinition = (CourseDefinition) session.createQuery("FROM CourseDefinition WHERE code = :code")
                                                .setParameter("code", code)
                                                .uniqueResult();
            return courseDefinition;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
