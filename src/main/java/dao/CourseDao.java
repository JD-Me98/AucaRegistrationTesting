package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.AcademicUnit;
import model.Course;
import model.CourseDefinition;
import model.Semester;
import model.Student;
import model.StudentRegistration;

public class CourseDao {

	public String createCourse(Course course) {
		Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        	tr = session.beginTransaction();
        	session.saveOrUpdate(course);
        	tr.commit();
            session.close();
            return "success";
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return("Error " + ex);
        }
    }
	public Course getCourseByCode(CourseDefinition courseDefinition) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Course course = (Course) session.createQuery("FROM Course WHERE courseDefinition = :code")
                .setParameter("code", courseDefinition)
                .uniqueResult();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<Course> getCourseByDepartmentAndSemester(AcademicUnit department, Semester semester) {

        List<Course> courses = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            String hql1 = "SELECT c FROM Course c WHERE c.semester = :sem AND c.academicUnit = :dep";
            Query<Course> query = session.createQuery(hql1, Course.class);
            query.setParameter("sem", semester);
            query.setParameter("dep", department);
            courses = query.list();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
	}
	
	public Set<Course> getCoursePerStudent(StudentRegistration student) {

        Set<Course> courses=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            if (student != null) {
                String hql1 = "SELECT c " +
                        "FROM Course c " +
                        "JOIN c.studentReg sr " +
                        "WHERE sr.studentId = :studentId";
                Query query = session.createQuery(hql1,Course.class);
                query.setParameter("studentId", student.getStudentId());
                courses = new HashSet<>(query.getResultList());
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
